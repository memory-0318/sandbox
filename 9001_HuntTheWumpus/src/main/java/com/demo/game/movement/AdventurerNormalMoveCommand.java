package com.demo.game.movement;

import com.demo.game.creature.Creature;
import com.demo.game.map.GameMap;
import com.demo.game.map.cave.Cave;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public class AdventurerNormalMoveCommand extends BaseCreatureMoveCommand {

    public AdventurerNormalMoveCommand(
        Creature creature,
        Cave destinationCave,
        GameMap gameMap, CreatureMoveResult creatureMoveResult) {
        super(creature, destinationCave, gameMap, creatureMoveResult);
    }

    @Override
    public void execute() {

    }
}
