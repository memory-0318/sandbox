package com.demo.game.movement;

import com.demo.game.creature.Creature;
import com.demo.game.map.GameMap;
import com.demo.game.map.cave.Cave;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public class GiantBatMoveCommand extends BaseCreatureMoveCommand {
    public GiantBatMoveCommand(Creature creature, Cave destinationCave, GameMap gameMap) {
        super(creature, destinationCave, gameMap);
    }

    @Override
    public CommandResult<Creature> execute() {
        Set<Cave> allCaves = this.gameMap.listAllCaves();
        Cave randomSelectedCave = new ArrayList<>(allCaves).get(new Random().nextInt(allCaves.size()));

        return new CommandResult<>(this.creature.toBuilder()
            .setCurrentCave(randomSelectedCave)
            .build());
    }
}
