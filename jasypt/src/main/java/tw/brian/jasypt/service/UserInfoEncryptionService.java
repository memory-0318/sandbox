package tw.brian.jasypt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.brian.jasypt.command.CommandFactory;
import tw.brian.jasypt.command.CommandInvoker;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
@Service
@Transactional(rollbackFor = Exception.class)
@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class UserInfoEncryptionService {
    private final CommandInvoker commandInvoker;
    private final CommandFactory commandFactory;

    @ShellMethod(key = { "encrypt-user-info" }, value = "加密UserInfoNear資料")
    public void encryptUserInfoNear() {
        this.commandInvoker.execute(this.commandFactory.createUserInfoNearEncryptionCommand());
    }

    @ShellMethod(key = { "decrypt-user-info" }, value = "解密UserInfoNear資料")
    public void decryptUserInfoNear() {
        this.commandInvoker.execute(this.commandFactory.createUserInfoNearDecryptionCommand());
    }

    @ShellMethod(key = { "test-user-info" }, value = "測試加密UserInfoNear資料")
    public void testEncryptUserInfoNear() {
        this.commandInvoker.execute(this.commandFactory.createUserInfoNearTestEncryptionCommand());
    }
}
