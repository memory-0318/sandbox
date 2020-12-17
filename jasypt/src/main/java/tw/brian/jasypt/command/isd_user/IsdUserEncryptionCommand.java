package tw.brian.jasypt.command.isd_user;

import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.command.Command;
import tw.brian.jasypt.entity.ISDUserEntity;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.ISDUserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
public class IsdUserEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final ISDUserRepository repository;

    public IsdUserEncryptionCommand(
        EncryptionManager encryptionManager,
        ISDUserRepository repository) {
        super(CommandType.ENCRYPT_USER_INFO_NEAR.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<ISDUserEntity> entitiesToEncrypt = this.repository.findAll();
        List<ISDUserEntity> encryptedEntities = entitiesToEncrypt.stream()
            .map(entity -> entity.toBuilder()
                .setUserName(this.encryptionManager.encrypt(entity.getUserName()))
                .setUserTel(this.encryptionManager.encrypt(entity.getUserTel()))
                .build())
            .collect(Collectors.toList());
        this.repository.saveAll(encryptedEntities);
    }
}
