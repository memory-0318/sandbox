package tw.brian.jasypt.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.command.isd_user.IsdUserDecryptionCommand;
import tw.brian.jasypt.command.isd_user.IsdUserEncryptionCommand;
import tw.brian.jasypt.command.isd_user.IsdUserTestEncryptionCommand;
import tw.brian.jasypt.command.user_info.UserInfoAppDecryptionCommand;
import tw.brian.jasypt.command.user_info.UserInfoAppEncryptionCommand;
import tw.brian.jasypt.command.user_info.UserInfoAppTestEncryptionCommand;
import tw.brian.jasypt.command.user_info_near.UserInfoNearDecryptionCommand;
import tw.brian.jasypt.command.user_info_near.UserInfoNearEncryptionCommand;
import tw.brian.jasypt.command.user_info_near.UserInfoNearTestEncryptionCommand;
import tw.brian.jasypt.entity.UserInfoNearRepository;
import tw.brian.jasypt.manager.EncryptionManager;
import tw.brian.jasypt.repository.ISDUserRepository;
import tw.brian.jasypt.repository.UserInfoAppRepository;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
@Component
@RequiredArgsConstructor
public class CommandFactory {
    private final EncryptionManager encryptionManager;
    private final ISDUserRepository isdUserRepository;
    private final UserInfoAppRepository userInfoAppRepository;
    private final UserInfoNearRepository userInfoNearRepository;

    public BaseCommand createIsdUserEncryptionCommand() {
        return new IsdUserEncryptionCommand(this.encryptionManager, this.isdUserRepository);
    }

    public BaseCommand createIsdUserDecryptionCommand() {
        return new IsdUserDecryptionCommand(this.encryptionManager, this.isdUserRepository);
    }

    public BaseCommand createIsdUserTestEncryptionCommand() {
        return new IsdUserTestEncryptionCommand(this.encryptionManager, this.isdUserRepository);
    }

    public BaseCommand createUserInfoNearEncryptionCommand() {
        return new UserInfoNearEncryptionCommand(this.encryptionManager, this.userInfoNearRepository);
    }

    public BaseCommand createUserInfoNearDecryptionCommand() {
        return new UserInfoNearDecryptionCommand(this.encryptionManager, this.userInfoNearRepository);
    }

    public BaseCommand createUserInfoNearTestEncryptionCommand() {
        return new UserInfoNearTestEncryptionCommand(this.encryptionManager, this.userInfoNearRepository);
    }

    public BaseCommand createUserInfoAppEncryptionCommand() {
        return new UserInfoAppEncryptionCommand(this.encryptionManager, this.userInfoAppRepository);
    }

    public BaseCommand createUserInfoAppDecryptionCommand() {
        return new UserInfoAppDecryptionCommand(this.encryptionManager, this.userInfoAppRepository);
    }

    public BaseCommand createUserInfoAppTestEncryptionCommand() {
        return new UserInfoAppTestEncryptionCommand(this.encryptionManager, this.userInfoAppRepository);
    }
}
