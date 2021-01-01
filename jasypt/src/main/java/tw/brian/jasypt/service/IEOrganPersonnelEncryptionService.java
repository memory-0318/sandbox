package tw.brian.jasypt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.command.CommandFactory;
import tw.brian.jasypt.command.CommandInvoker;
import tw.brian.jasypt.command.listener.CommandListener;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/11/26
 */
@Service
@Transactional(rollbackFor = Exception.class)
@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class IEOrganPersonnelEncryptionService {
    private final CommandInvoker commandInvoker;
    private final CommandFactory commandFactory;

    @ShellMethod(key = { "ieOrganPersonnel" }, value = "加密IEOrganPersonnelEntity資料")
    public void encryptIEOrganPersonnelEntity(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        this.commandInvoker
            .execute(this.commandFactory.createIEOrganEncryptionCommand(), new ConcreteCommandListener());
    }

    @ShellMethod(key = { "test-ieOrganPersonnel" }, value = "試加密IEOrganPersonnel資料")
    public void testEncryptIEOrganPersonnelEntity() {
        this.commandInvoker.execute(this.commandFactory.createIEOrganTestEncryptionCommand());
    }

    @ShellMethod(key = { "decrypt-ieOrganPersonnel" }, value = "解密IEOrganPersonnel資料")
    public void decryptIEOrganPersonnelEntity(@ShellOption(value = { "-p" }, defaultValue = "1000") Integer pageSize) {
        this.commandInvoker.execute(this.commandFactory.createIEOrganDecryptionCommand());
    }

    private static class ConcreteCommandListener implements CommandListener {

        @Override
        public void beforeExecute(BaseCommand command) {
            log.info("Start encrypting table: IEORGANPERSONNEL...");
        }

        @Override
        public void afterExecute(BaseCommand command) {
            log.info("Finish encrypting table: IEORGANPERSONNEL");
        }
    }
}
