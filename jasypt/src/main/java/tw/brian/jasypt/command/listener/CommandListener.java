package tw.brian.jasypt.command.listener;

import tw.brian.jasypt.BaseCommand;
import tw.brian.jasypt.command.Command;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
public interface CommandListener {
    void beforeExecute(BaseCommand command);

    void afterExecute(BaseCommand command);
}
