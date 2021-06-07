package com.demo.complete.command.adventurer.v1;

import com.demo.complete.Adventurer;
import com.demo.complete.command.adventurer.BaseAdventurerCommand;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
public class WalkLeftCommand extends BaseAdventurerCommand {
    private final Integer movementAmount;

    public WalkLeftCommand(Adventurer adventurer, Integer movementAmount) {
        super(adventurer);

        this.movementAmount = movementAmount;
    }

    @Override
    public void execute() {
        // simulate slow operation...
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // do nothing
        }
        this.adventurer.moveLeft(this.movementAmount);
    }
}
