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
public class UserInfoAppEncryptionService {
    private final CommandInvoker commandInvoker;
    private final CommandFactory commandFactory;

    @ShellMethod(key = { "encrypt-user-info-app" }, value = "加密UserInfoApp資料")
    public void encryptUserInfoApp() {
        this.commandInvoker.execute(this.commandFactory.createUserInfoAppEncryptionCommand());
    }

    @ShellMethod(key = { "decrypt-user-info-app" }, value = "解密UserInfoNear資料")
    public void decryptUserInfoApp() {
        this.commandInvoker.execute(this.commandFactory.createUserInfoAppDecryptionCommand());
    }

    @ShellMethod(key = { "test-user-info-app" }, value = "測試加密UserInfoApp資料")
    public void testEncryptUserInfoApp() {
        this.commandInvoker.execute(this.commandFactory.createUserInfoAppTestEncryptionCommand());
    }
}
