package com.demo.complete.command.adventurer;

import com.demo.complete.Adventurer;
import com.demo.complete.item.mana.AbstractManaPotion;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
public class ManaPotionConsumptionCommand extends BaseAdventurerCommand {
    private final AbstractManaPotion manaPotion;

    public ManaPotionConsumptionCommand(Adventurer adventurer, AbstractManaPotion manaPotion) {
        super(adventurer);
        this.manaPotion = manaPotion;
    }

    @Override
    public void execute() {
        this.adventurer.recoveryMana(this.manaPotion.getAmount());
    }
}
