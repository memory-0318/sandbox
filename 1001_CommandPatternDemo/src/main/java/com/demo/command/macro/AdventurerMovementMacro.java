package com.demo.command.macro;

import com.demo.command.Command;

import java.util.List;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
public class AdventurerMovementMacro implements Command {
    private final List<Command> commands;

    public AdventurerMovementMacro(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        this.commands.forEach(Command::execute);
    }
}
