package com.demo.complete.command;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
public class CommandLogDecorator implements Command {
    private Command command;

    public CommandLogDecorator(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        System.out.printf(" ===> Execute command: %s%n", this.command.getClass().getSimpleName());
        this.command.execute();
    }
}
