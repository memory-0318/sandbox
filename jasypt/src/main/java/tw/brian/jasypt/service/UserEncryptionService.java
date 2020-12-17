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
import tw.brian.jasypt.entity.IUserEntity;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.UserRepository;

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
public class UserEncryptionService {
    private final EncryptionManager encryptionManager;
    private final UserRepository userRepository;

    @ShellMethod(key = { "iuser" }, value = "加密IUser資料")
    public void encryptIUser(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        log.info("Start encrypting IUSER table...");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long total = this.userRepository.count();
        long pages = total / pageSize + 1;

        log.info("Total entity: {}, Total Pages: {}, Page Size: {}", total, pages, pageSize);

        List<IUserEntity> userEntities = LongStream.range(0, pages)
            .parallel()
            .mapToObj(page -> {
                log.info("Page processing: {}", page);
                try {
                    return this.encryptUserFields((int) page, pageSize);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        log.info(" --- Start saving encrypted users...");
        this.userRepository.saveAll(userEntities);
        log.info(" --- Finished saving encrypted users...");

        stopWatch.stop();

        log.info("Finished encrypting IUSER table..., total duration: {}ms", stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    @ShellMethod(key = { "decrypt-iuser" }, value = "解密IUser資料")
    public void decryptIUser(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        log.info("Start decrypting IUSER table...");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long total = this.userRepository.count();
        long pages = total / pageSize + 1;
        List<IUserEntity> userEntities = LongStream.range(0, pages)
            .parallel()
            .mapToObj(page -> {
                log.info("Page processing: {}", page);
                return this.decryptUserFields((int) page, pageSize);
            })
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        this.userRepository.saveAll(userEntities);

        stopWatch.stop();

        log.info("Finished decrypting IUSER table..., total duration: {}ms", stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    @ShellMethod(key = { "test-iuser" }, value = "試加密IUser資料")
    public void testEncryptIUser() {
        List<IUserEntity> userEntities = this.userRepository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setUserName(this.encryptionManager.encrypt(entity.getUserName()))
                .setUserTel(this.encryptionManager.encrypt(entity.getUserTel()))
                .setUserPasswd(this.encryptionManager.encryptPassword(entity.getUserPasswd()))
                .build())
            .collect(Collectors.toList());

        userEntities.forEach(entity -> log
            .info("oid: {}, name: {} -> {}, tel: {} -> {}",
                entity.getUserOid(),
                entity.getUserName(), this.encryptionManager.decrypt(entity.getUserName()),
                entity.getUserTel(), this.encryptionManager.decrypt(entity.getUserTel())
            ));
    }

    private List<IUserEntity> encryptUserFields(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of((int) page, pageSize);

        Page<IUserEntity> userEntityPage = this.userRepository.findAll(pageable);
        return userEntityPage.getContent()
            .stream()
            .map(entity -> entity.toBuilder()
                .setUserName(this.encryptionManager.encrypt(entity.getUserName()))
                .setUserTel(this.encryptionManager.encrypt(entity.getUserTel()))
                .setUserPasswd(this.encryptionManager.encryptPassword(entity.getUserPasswd()))
                .build())
            .collect(Collectors.toList());
    }

    private List<IUserEntity> decryptUserFields(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of((int) page, pageSize);

        Page<IUserEntity> userEntityPage = this.userRepository.findAll(pageable);
        return userEntityPage.getContent()
            .stream()
            .map(userEntity -> userEntity.toBuilder()
                .setUserName(this.encryptionManager.decrypt(userEntity.getUserName()))
                .setUserTel(this.encryptionManager.decrypt(userEntity.getUserTel()))
                .build())
            .collect(Collectors.toList());
    }
}
