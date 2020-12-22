package tw.brian.jasypt.command.user_info;

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
public class UserInfoDecryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final UserInfoRepository repository;

    public UserInfoDecryptionCommand(
        EncryptionManager encryptionManager,
        UserInfoRepository repository) {
        super(CommandType.DECRYPT_USER_INFO_NEAR.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<UserInfoEntity> entitiesToEncrypt = this.repository.findAll();
        List<UserInfoEntity> encryptedEntities = entitiesToEncrypt.stream()
            .map(entity -> entity.toBuilder()
                .setName(this.encryptionManager.decrypt(entity.getName()))
                .setAddress(this.encryptionManager.decrypt(entity.getAddress()))
                .setTelh(this.encryptionManager.decrypt(entity.getTelh()))
                .setPhone(this.encryptionManager.decrypt(entity.getPhone()))
                .build())
            .collect(Collectors.toList());
        this.repository.saveAll(encryptedEntities);
    }
}
