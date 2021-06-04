package com.demo.command.adventurer;

import com.demo.Adventurer;
import com.demo.command.Command;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
public abstract class BaseAdventurerCommand implements Command {
    protected final Adventurer adventurer;

    public BaseAdventurerCommand(Adventurer adventurer) {
        this.adventurer = adventurer;
    }
}
