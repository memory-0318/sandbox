package tw.brian.jasypt.command.user_info_near;

import lombok.extern.slf4j.Slf4j;
import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.entity.UserInfoNearEntity;
import tw.brian.jasypt.entity.UserInfoNearRepository;
import tw.brian.jasypt.manager.EncryptionManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
@Slf4j
public class UserInfoNearTestEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final UserInfoNearRepository repository;

    public UserInfoNearTestEncryptionCommand(
        EncryptionManager encryptionManager,
        UserInfoNearRepository repository) {
        super(CommandType.TEST_ENCRYPT_USER_INFO_NEAR.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<UserInfoNearEntity> encryptedEntities = this.repository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setAccount(this.encryptionManager.encrypt(entity.getAccount()))
                .setPasswd(this.encryptionManager.encryptPassword(entity.getPasswd()))
                .setName(this.encryptionManager.encrypt(entity.getName()))
                .setAddress(this.encryptionManager.encrypt(entity.getAddress()))
                .setTelh(this.encryptionManager.encrypt(entity.getTelh()))
                .setHistoryPassword(this.encryptionManager.encrypt(entity.getHistoryPassword()))
                .build())
            .collect(Collectors.toList());

        encryptedEntities.forEach(entity -> log
            .info("ACCOUNT: {} -> {}, NAME: {} -> {}, ADDRESS: {} -> {}, TELH: {} -> {}",
                entity.getAccount(), this.encryptionManager.decrypt(entity.getAccount()),
                entity.getName(), this.encryptionManager.decrypt(entity.getName()),
                entity.getAddress(), this.encryptionManager.decrypt(entity.getAddress()),
                entity.getTelh(), this.encryptionManager.decrypt(entity.getTelh())
            ));
    }
}
