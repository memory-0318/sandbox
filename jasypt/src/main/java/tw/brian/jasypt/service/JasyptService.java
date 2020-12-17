package tw.brian.jasypt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import tw.brian.jasypt.manager.EncryptionManager;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/11/26
 */
@Service
@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class JasyptService {
    private final EncryptionManager encryptionManager;

    @ShellMethod("解密輸入字串")
    public String decrypt(@ShellOption("--input") String input) {
        return this.encryptionManager.decrypt(input);
    }

    @ShellMethod("加密輸入字串")
    public String encrypt(@ShellOption("--input") String input) {
        return this.encryptionManager.encrypt(input);
    }
}
