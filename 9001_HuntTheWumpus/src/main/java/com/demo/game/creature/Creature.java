package com.demo.game.creature;

import com.demo.game.map.cave.Cave;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
@Data
@Setter(AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class Creature {
    private CreatureType creatureType;
    private Cave currentCave;

    public Creature(CreatureType creatureType) {
        this.creatureType = creatureType;
    }

    public Creature(CreatureType creatureType, Cave currentCave) {
        this.creatureType = creatureType;
        this.currentCave = currentCave;
    }
}
