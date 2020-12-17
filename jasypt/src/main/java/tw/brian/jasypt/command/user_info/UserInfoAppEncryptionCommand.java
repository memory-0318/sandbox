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
 * @date: 2020/12/10
 */
public class UserInfoAppEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;

    private final UserInfoAppRepository repository;

    public UserInfoAppEncryptionCommand(
        final EncryptionManager encryptionManager,
        final UserInfoAppRepository repository) {
        super(CommandType.ENCRYPT_USER_INFO_APP.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<UserInfoAppEntity> entitiesToEncrypt = this.repository.findAll();
        List<UserInfoAppEntity> encryptedEntities = entitiesToEncrypt.stream()
            .map(entity -> entity.toBuilder()
                .setAccount(this.encryptionManager.encrypt(entity.getAccount()))
                .setAddress(this.encryptionManager.encrypt(entity.getAddress()))
                .setTelh(this.encryptionManager.encrypt(entity.getTelh()))
                .setPhone(this.encryptionManager.encrypt(entity.getPhone()))
                .setName(this.encryptionManager.encrypt(entity.getName()))
                .setIdentifyNumber(this.encryptionManager.encrypt(entity.getIdentifyNumber()))
                .build())
            .collect(Collectors.toList());
        this.repository.saveAll(encryptedEntities);
    }
}
