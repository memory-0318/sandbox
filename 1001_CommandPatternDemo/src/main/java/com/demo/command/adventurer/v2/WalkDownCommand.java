package com.demo.command.adventurer.v2;

import com.demo.Adventurer;
import com.demo.command.adventurer.BaseAdventurerCommand;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
public class WalkDownCommand extends BaseAdventurerCommand {
    private final Integer movementAmount;

    public WalkDownCommand(Adventurer adventurer, Integer movementAmount) {
        super(adventurer);

        this.movementAmount = movementAmount;
    }

    @Override
    public void execute() {
        this.adventurer.moveDown(this.movementAmount);
    }
}
