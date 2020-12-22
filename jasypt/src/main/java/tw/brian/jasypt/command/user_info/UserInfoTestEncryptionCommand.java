package tw.brian.jasypt.command.user_info;

import lombok.extern.slf4j.Slf4j;
import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.entity.UserInfoEntity;
import tw.brian.jasypt.repository.UserInfoRepository;
import tw.brian.jasypt.manager.EncryptionManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
@Slf4j
public class UserInfoTestEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final UserInfoRepository repository;

    public UserInfoTestEncryptionCommand(
        EncryptionManager encryptionManager,
        UserInfoRepository repository) {
        super(CommandType.TEST_ENCRYPT_USER_INFO_NEAR.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<UserInfoEntity> encryptedEntities = this.repository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setPasswd(this.encryptionManager.encryptPassword(entity.getPasswd()))
                .setName(this.encryptionManager.encrypt(entity.getName()))
                .setAddress(this.encryptionManager.encrypt(entity.getAddress()))
                .setTelh(this.encryptionManager.encrypt(entity.getTelh()))
                .setPhone(this.encryptionManager.encrypt(entity.getPhone()))
                .build())
            .collect(Collectors.toList());

        encryptedEntities.forEach(entity -> log
            .info("ACCOUNT: {}, NAME: {} -> {}, ADDRESS: {} -> {}, TELH: {} -> {}, PHONE: {} -> {}",
                entity.getAccount(),
                entity.getName(), this.encryptionManager.decrypt(entity.getName()),
                entity.getAddress(), this.encryptionManager.decrypt(entity.getAddress()),
                entity.getTelh(), this.encryptionManager.decrypt(entity.getTelh()),
                entity.getPhone(), this.encryptionManager.decrypt(entity.getPhone())
            ));
    }
}
