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
import tw.brian.jasypt.entity.IEOrganPersonnelEntity;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.IEOrganPersonnelRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/11/26
 */
@Service
@Transactional(rollbackFor = Exception.class)
@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class IEOrganPersonnelEncryptionService {
    private final EncryptionManager encryptionManager;
    private final IEOrganPersonnelRepository ieOrganPersonnelRepository;

    @ShellMethod(key = { "ieOrganPersonnel" }, value = "加密IEOrganPersonnelEntity資料")
    public void encryptIEOrganPersonnelEntity(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        log.info("Start encrypting IEOrganPersonnelEntity table...");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long total = this.ieOrganPersonnelRepository.count();
        long pages = total / pageSize + 1;

        log.info("Total entity: {}, Total Pages: {}, Page Size: {}", total, pages, pageSize);

        List<IEOrganPersonnelEntity> organPersonnelEntities = new ArrayList<>();
        try (LongStream longStream = LongStream.range(0, pages)) {
            organPersonnelEntities = longStream.parallel()
                .mapToObj(page -> {
                    log.info("Page processing: {}", page);
                    try {
                        return this.encryptIEOrganPersonnelFields((int) page, pageSize);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

            log.info(" --- Start saving encrypted organPersonnelEntities...");
            this.ieOrganPersonnelRepository.saveAll(organPersonnelEntities);
            log.info(" --- Finished saving encrypted organPersonnelEntities...");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        stopWatch.stop();

        log.info("Finished encrypting IEOrganPersonnel table..., total duration: {}ms",
            stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    @ShellMethod(key = { "decrypt-ieOrganPersonnel" }, value = "解密IEOrganPersonnel資料")
    public void decryptIEOrganPersonnelEntity(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        log.info("Start decrypting IEOrganPersonnel table...");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long total = this.ieOrganPersonnelRepository.count();
        long pages = total / pageSize + 1;
        List<IEOrganPersonnelEntity> organPersonnelEntities = new ArrayList<>();
        try (LongStream longStream = LongStream.range(0, pages)) {
            organPersonnelEntities = longStream.parallel()
                .mapToObj(page -> {
                    log.info("Page processing: {}", page);
                    return this.decryptIEOrganPersonnelFields((int) page, pageSize);
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

            log.info(" --- Start saving decrypted organPersonnelEntities...");
            this.ieOrganPersonnelRepository.saveAll(organPersonnelEntities);
            log.info(" --- Finished saving decrypted organPersonnelEntities...");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        stopWatch.stop();

        log.info("Finished decrypting IEOrganPersonnel table..., total duration: {}ms",
            stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    @ShellMethod(key = { "test-ieOrganPersonnel" }, value = "試加密IEOrganPersonnel資料")
    public void testEncryptIEOrganPersonnelEntity() {
        List<IEOrganPersonnelEntity> ieOrganPersonnelEntities = this.ieOrganPersonnelRepository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setEncodePersonnelNm(this.encryptionManager.encrypt(entity.getEncodePersonnelNm()))
                .setEncodeTelNumber(this.encryptionManager.encrypt(entity.getEncodeTelNumber()))
                .build())
            .collect(Collectors.toList());

        ieOrganPersonnelEntities.forEach(entity -> log
            .info("pid: {}, encodePersonnelNm: {} -> {}, encodeTelNumber: {} -> {}",
                entity.getPid(),
                new String(entity.getEncodePersonnelNm()),
                new String(this.encryptionManager.decrypt(entity.getEncodePersonnelNm())),
                new String(entity.getEncodeTelNumber()),
                new String(this.encryptionManager.decrypt(entity.getEncodeTelNumber()))
            ));
    }

    private List<IEOrganPersonnelEntity> encryptIEOrganPersonnelFields(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of((int) page, pageSize);

        Page<IEOrganPersonnelEntity> userEntityPage = this.ieOrganPersonnelRepository.findAll(pageable);
        return userEntityPage.getContent()
            .stream()
            .map(entity -> entity.toBuilder()
                .setEncodePersonnelNm(this.encryptionManager.encrypt(entity.getEncodePersonnelNm()))
                .setEncodeTelNumber(this.encryptionManager.encrypt(entity.getEncodeTelNumber()))
                .build())
            .collect(Collectors.toList());
    }

    private List<IEOrganPersonnelEntity> decryptIEOrganPersonnelFields(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of((int) page, pageSize);

        Page<IEOrganPersonnelEntity> userEntityPage = this.ieOrganPersonnelRepository.findAll(pageable);
        return userEntityPage.getContent()
            .stream()
            .map(userEntity -> userEntity.toBuilder()
                .setEncodePersonnelNm(this.encryptionManager.decrypt(userEntity.getEncodePersonnelNm()))
                .setEncodeTelNumber(this.encryptionManager.decrypt(userEntity.getEncodeTelNumber()))
                .build())
            .collect(Collectors.toList());
    }
}
