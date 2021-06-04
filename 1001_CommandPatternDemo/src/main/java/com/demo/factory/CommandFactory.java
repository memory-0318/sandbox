package com.demo.factory;

import com.demo.Adventurer;
import com.demo.command.Command;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
public interface CommandFactory {
    Command createWalkDownCommand(Adventurer adventurer, Integer movementAmount);

    Command createWalkLeftCommand(Adventurer adventurer, Integer movementAmount);

    Command createWalkRightCommand(Adventurer adventurer, Integer movementAmount);

    Command createWalkUpCommand(Adventurer adventurer, Integer movementAmount);
}
