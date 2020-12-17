package tw.brian.jasypt.command.user_info;

import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.command.Command;
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
public class UserInfoAppDecryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;

    private final UserInfoAppRepository repository;

    public UserInfoAppDecryptionCommand(
        EncryptionManager encryptionManager,
        UserInfoAppRepository repository) {
        super(CommandType.DECRYPT_USER_INFO_APP.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<UserInfoAppEntity> entitiesToDecrypt = this.repository.findAll();
        List<UserInfoAppEntity> decryptedEntities = entitiesToDecrypt.stream()
            .map(entity -> entity.toBuilder()
                .setAccount(this.encryptionManager.decrypt(entity.getAccount()))
                .setAddress(this.encryptionManager.decrypt(entity.getAddress()))
                .setTelh(this.encryptionManager.decrypt(entity.getTelh()))
                .setPhone(this.encryptionManager.decrypt(entity.getPhone()))
                .setName(this.encryptionManager.decrypt(entity.getName()))
                .setIdentifyNumber(this.encryptionManager.decrypt(entity.getIdentifyNumber()))
                .build())
            .collect(Collectors.toList());
        this.repository.saveAll(decryptedEntities);
    }
}
