package tw.brian.jasypt.command.ie_organ_personnel;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class IEOrganPersonnelTestEncryptionCommand extends BaseCommand {
    private final EncryptionManager encryptionManager;
    private final IEOrganPersonnelRepository repository;

    public IEOrganPersonnelTestEncryptionCommand(
        EncryptionManager encryptionManager,
        IEOrganPersonnelRepository repository) {
        super(CommandType.ENCRYPT_IE_ORGAN_PERSONNEL.getCommandName());

        this.encryptionManager = encryptionManager;
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<IEOrganPersonnelEntity> encryptedEntities = this.repository.findTop100By()
            .parallelStream()
            .map(entity -> entity.toBuilder()
                .setEncodePersonnelNm(this.encryptionManager.encrypt(entity.getEncodePersonnelNm()))
                .setEncodeTelNumber(this.encryptionManager.encrypt(entity.getEncodeTelNumber()))
                .build())
            .collect(Collectors.toList());

        encryptedEntities.forEach(entity -> log
            .info("PID: {}, ENCODE_PERSONNEL_NM: {} -> {}, ENCODE_PERSONNEL_TEL: {} -> {}",
                entity.getPid(),
                new String(entity.getEncodePersonnelNm()),
                new String(this.encryptionManager.decrypt(entity.getEncodePersonnelNm())),
                new String(entity.getEncodeTelNumber()),
                new String(this.encryptionManager.decrypt(entity.getEncodeTelNumber()))));
    }
}
