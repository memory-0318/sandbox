package tw.brian.jasypt.command.user_info_app;

import lombok.extern.slf4j.Slf4j;
import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.entity.UserInfoAppEntity;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.UserInfoAppRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
@Slf4j
public class UserInfoAppTestEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final UserInfoAppRepository repository;

    public UserInfoAppTestEncryptionCommand(
        EncryptionManager encryptionManager,
        UserInfoAppRepository repository) {
        super(CommandType.TEST_ENCRYPT_USER_INFO_APP.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<UserInfoAppEntity> encryptedEntities = this.repository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setAccount(this.encryptionManager.encrypt(entity.getAccount()))
                .setAddress(this.encryptionManager.encrypt(entity.getAddress()))
                .setTelh(this.encryptionManager.encrypt(entity.getTelh()))
                .setPhone(this.encryptionManager.encrypt(entity.getPhone()))
                .setName(this.encryptionManager.encrypt(entity.getName()))
                .setIdentifyNumber(this.encryptionManager.encrypt(entity.getIdentifyNumber()))
                .build())
            .collect(Collectors.toList());

        encryptedEntities.forEach(entity -> log
            .info("ACCOUNT: {} -> {}, ADDRESS: {} -> {}, TELH: {} -> {}, PHONE: {} -> {}, NAME: {} -> {}, IDENTIFY_NUMBER: {} -> {}",
                entity.getAccount(), this.encryptionManager.decrypt(entity.getAccount()),
                entity.getAddress(), this.encryptionManager.decrypt(entity.getAddress()),
                entity.getTelh(), this.encryptionManager.decrypt(entity.getTelh()),
                entity.getPhone(), this.encryptionManager.decrypt(entity.getPhone()),
                entity.getName(), this.encryptionManager.decrypt(entity.getName()),
                entity.getIdentifyNumber(), this.encryptionManager.decrypt(entity.getIdentifyNumber())
            ));
    }
}
