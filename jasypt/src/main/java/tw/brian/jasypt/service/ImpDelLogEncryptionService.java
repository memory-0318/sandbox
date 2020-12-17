package tw.brian.jasypt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.brian.jasypt.entity.IImpDelLogEntity;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.ImpDelLogRepository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/11/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class ImpDelLogEncryptionService {
    private final EncryptionManager encryptionManager;
    private final ImpDelLogRepository impDelLogRepository;

    @ShellMethod(key = { "impDelLog" }, value = "加密ImpDelLog資料")
    public void encryptImpDelLog(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        log.info("Start encrypting IIMPDELLOG table...");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long total = this.impDelLogRepository.count();
        long pages = total / pageSize + 1;

        log.info("Total entity: {}, Total Pages: {}, Page Size: {}", total, pages, pageSize);

        List<IImpDelLogEntity> impDelLogEntities = LongStream.range(0, pages)
            .parallel()
            .mapToObj(page -> {
                log.info("Page processing: {}", page);
                try {
                    return this.encryptImpDelLogFields((int) page, pageSize);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        log.info(" --- Start saving encrypted users...");
        this.impDelLogRepository.saveAll(impDelLogEntities);
        log.info(" --- Finished saving encrypted users...");

        stopWatch.stop();

        log.info("Finished encrypting IIMPDELLOG table..., total duration: {}ms",
            stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    @ShellMethod(key = { "decrypt-impdellog" }, value = "解密ImpDelLog資料")
    public void decryptImpDelLog(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        log.info("Start decrypting IIMPDELLOG table...");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long total = this.impDelLogRepository.count();
        long pages = total / pageSize + 1;
        List<IImpDelLogEntity> impDelLogEntities = LongStream.range(0, pages)
            .parallel()
            .mapToObj(page -> {
                log.info("Page processing: {}", page);
                return this.decryptImpDelLogFields((int) page, pageSize);
            })
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        this.impDelLogRepository.saveAll(impDelLogEntities);

        stopWatch.stop();

        log.info("Finished decrypting IIMPDELLOG table..., total duration: {}ms",
            stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    @ShellMethod(key = { "test-impdellog" }, value = "試加密ImpDelLog資料")
    public void testEncryptImpDelLog() {
        List<IImpDelLogEntity> impDelLogEntities = this.impDelLogRepository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setEncodePassOpName(this.encryptionManager.encrypt(entity.getEncodePassOpName()))
                .build())
            .collect(Collectors.toList());

        impDelLogEntities.forEach(entity -> log
            .info("oid: {}, encode_pass_op_name: {} -> {}",
                entity.getImpDelOid(),
                new String(entity.getEncodePassOpName()), new String(this.encryptionManager.decrypt(entity.getEncodePassOpName()))
            ));
    }

    private List<IImpDelLogEntity> encryptImpDelLogFields(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of((int) page, pageSize);

        Page<IImpDelLogEntity> impDelLogEntityPage = this.impDelLogRepository.findAll(pageable);
        return impDelLogEntityPage.getContent()
            .stream()
            .map(entity -> entity.toBuilder()
                .setEncodePassOpName(this.encryptionManager.encrypt(entity.getEncodePassOpName()))
                .build())
            .collect(Collectors.toList());
    }

    private List<IImpDelLogEntity> decryptImpDelLogFields(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of((int) page, pageSize);

        Page<IImpDelLogEntity> impDelLogEntities = this.impDelLogRepository.findAll(pageable);
        return impDelLogEntities.getContent()
            .stream()
            .map(userEntity -> userEntity.toBuilder()
                .setEncodePassOpName(this.encryptionManager.decrypt(userEntity.getEncodePassOpName()))
                .build())
            .collect(Collectors.toList());
    }
}
