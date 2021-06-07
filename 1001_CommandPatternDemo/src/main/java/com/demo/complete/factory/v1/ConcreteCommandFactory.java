package com.demo.complete.factory.v1;

import com.demo.complete.Adventurer;
import com.demo.complete.command.Command;
import com.demo.complete.command.adventurer.v1.WalkDownCommand;
import com.demo.complete.command.adventurer.v1.WalkLeftCommand;
import com.demo.complete.command.adventurer.v1.WalkRightCommand;
import com.demo.complete.command.adventurer.v1.WalkUpCommand;
import com.demo.complete.factory.CommandFactory;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
public class ConcreteCommandFactory implements CommandFactory {
    @Override
    public Command createWalkDownCommand(Adventurer adventurer, Integer movementAmount) {
        return new WalkDownCommand(adventurer, movementAmount);
    }

    @Override
    public Command createWalkLeftCommand(Adventurer adventurer, Integer movementAmount) {
        return new WalkLeftCommand(adventurer, movementAmount);
    }

    @Override
    public Command createWalkRightCommand(Adventurer adventurer, Integer movementAmount) {
        return new WalkRightCommand(adventurer, movementAmount);
    }

    @Override
    public Command createWalkUpCommand(Adventurer adventurer, Integer movementAmount) {
        return new WalkUpCommand(adventurer, movementAmount);
    }
}
