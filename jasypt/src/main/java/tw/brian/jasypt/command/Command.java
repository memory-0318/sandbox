package tw.brian.jasypt.command;

import java.io.IOException;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/10
 */
public interface Command {
    void execute() throws IOException;
}
