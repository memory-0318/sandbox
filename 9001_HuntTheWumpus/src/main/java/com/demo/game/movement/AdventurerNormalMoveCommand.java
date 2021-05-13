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
    public AdventurerNormalMoveCommand(Creature creature, Cave destinationCave, GameMap gameMap) {
        super(creature, destinationCave, gameMap);
    }

    @Override
    public CommandResult<Creature> execute() {
        if (this.gameMap.isNeighborCave(this.creature.getCurrentCave(), this.destinationCave)) {
            throw new IllegalMovementException("冒險者只能在鄰近洞窟移動");
        }
        return new CommandResult<>(this.creature.toBuilder()
            .setCurrentCave(this.destinationCave)
            .build());
    }
}
