package tw.brian.jasypt.command.ie_organ_personnel;

import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.CommandType;
import tw.brian.jasypt.entity.IEOrganPersonnelEntity;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.IEOrganPersonnelRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/23
 */
public class IEOrganPersonnelEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final IEOrganPersonnelRepository repository;

    public IEOrganPersonnelEncryptionCommand(
        EncryptionManager encryptionManager,
        IEOrganPersonnelRepository repository) {
        super(CommandType.ENCRYPT_IE_ORGAN_PERSONNEL.getCommandName());

        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<IEOrganPersonnelEntity> entitiesToEncrypt = this.repository.findAll();
        List<IEOrganPersonnelEntity> encryptedEntities = entitiesToEncrypt.stream()
            .map(entity -> entity.toBuilder()
                .setEncodePersonnelNm(this.encryptionManager.encrypt(entity.getEncodePersonnelNm()))
                .setEncodeTelNumber(this.encryptionManager.encrypt(entity.getEncodeTelNumber()))
                .build())
            .collect(Collectors.toList());

        this.repository.saveAll(encryptedEntities);
    }
}
