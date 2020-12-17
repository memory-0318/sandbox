package tw.brian.jasypt.command.user_info_near;

import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.command.Command;
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
public class UserInfoNearDecryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final UserInfoNearRepository repository;

    public UserInfoNearDecryptionCommand(
        EncryptionManager encryptionManager,
        UserInfoNearRepository repository) {
        super(CommandType.DECRYPT_USER_INFO_NEAR.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<UserInfoNearEntity> entitiesToEncrypt = this.repository.findAll();
        List<UserInfoNearEntity> encryptedEntities = entitiesToEncrypt.stream()
            .map(entity -> entity.toBuilder()
                .setAccount(this.encryptionManager.decrypt(entity.getAccount()))
                .setName(this.encryptionManager.decrypt(entity.getName()))
                .setAddress(this.encryptionManager.decrypt(entity.getAddress()))
                .setTelh(this.encryptionManager.decrypt(entity.getTelh()))
                .build())
            .collect(Collectors.toList());
        this.repository.saveAll(encryptedEntities);
    }
}
