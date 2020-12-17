package tw.brian.jasypt.command.isd_user;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class IsdUserTestEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final ISDUserRepository repository;

    public IsdUserTestEncryptionCommand(
        EncryptionManager encryptionManager,
        ISDUserRepository repository) {
        super(CommandType.TEST_ENCRYPT_ISD_USER.getCommandName());
        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<ISDUserEntity> encryptedEntities = this.repository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setUserName(this.encryptionManager.encrypt(entity.getUserName()))
                .setUserTel(this.encryptionManager.encrypt(entity.getUserTel()))
                .build())
            .collect(Collectors.toList());

        encryptedEntities.forEach(entity -> log
            .info("oid: {}, ENCODE_USER_NAME: {} -> {}, ENCODE_USER_TEL: {} -> {}",
                entity.getSId(),
                entity.getUserName(), this.encryptionManager.decrypt(entity.getUserName()),
                entity.getUserTel(), this.encryptionManager.decrypt(entity.getUserTel())
            ));
    }
}
