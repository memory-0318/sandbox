package com.demo.game.movement;

import com.demo.game.creature.Creature;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public class CreatureMoveResult {
    private Creature creature;

    public CreatureMoveResult(Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }
}
