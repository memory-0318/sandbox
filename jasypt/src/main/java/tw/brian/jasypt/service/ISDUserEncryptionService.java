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
public class ISDUserEncryptionService {
    private final CommandInvoker commandInvoker;
    private final CommandFactory commandFactory;

    @ShellMethod(key = { "encrypt-isd-user" }, value = "加密ISDUser資料")
    public void encryptIsdUser() {
        this.commandInvoker.execute(this.commandFactory.createIsdUserEncryptionCommand());
    }

    @ShellMethod(key = { "decrypt-isd-user" }, value = "解密ISDUser資料")
    public void decryptIdsUser() {
        this.commandInvoker.execute(this.commandFactory.createIsdUserDecryptionCommand());
    }

    @ShellMethod(key = { "test-isd-user" }, value = "測試加密ISDUser資料")
    public void testEncryptIsdUser() {
        this.commandInvoker.execute(this.commandFactory.createIsdUserTestEncryptionCommand());
    }
}
