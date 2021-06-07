package com.demo.complete;

import com.demo.complete.command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
public class Player {
    private List<Command> commandHistories = new ArrayList<>();

    public void executeAdventurerCommand(Command command) {
        this.commandHistories.add(command);

        command.execute();
    }

    public List<Command> listCommandHistories() {
        return new ArrayList<>(this.commandHistories);
    }
}
