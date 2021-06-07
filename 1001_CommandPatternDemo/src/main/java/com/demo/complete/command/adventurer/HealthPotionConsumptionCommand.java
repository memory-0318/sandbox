package com.demo.complete.command.adventurer;

import com.demo.complete.Adventurer;
import com.demo.complete.item.health.AbstractHealthPotion;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
public class HealthPotionConsumptionCommand extends BaseAdventurerCommand {
    private final AbstractHealthPotion healthPotion;

    public HealthPotionConsumptionCommand(Adventurer adventurer, AbstractHealthPotion healthPotion) {
        super(adventurer);
        this.healthPotion = healthPotion;
    }

    @Override
    public void execute() {
        this.adventurer.recoveryHealth(this.healthPotion.getAmount());
    }
}
