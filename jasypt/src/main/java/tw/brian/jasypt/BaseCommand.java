package tw.brian.jasypt;

import tw.brian.jasypt.command.Command;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/11
 */
public abstract class BaseCommand implements Command {
    private final String commandName;

    public BaseCommand(String commandName) {this.commandName = commandName;}

    public String getCommandName() {
        return commandName;
    }
}
