package com.demo.command.adventurer.v2;

import com.demo.Adventurer;
import com.demo.command.adventurer.BaseAdventurerCommand;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
public class WalkRightCommand extends BaseAdventurerCommand {
    private final Integer movementAmount;

    public WalkRightCommand(Adventurer adventurer, Integer movementAmount) {
        super(adventurer);

        this.movementAmount = movementAmount;
    }

    @Override
    public void execute() {
        this.adventurer.moveRight(this.movementAmount);
    }
}
