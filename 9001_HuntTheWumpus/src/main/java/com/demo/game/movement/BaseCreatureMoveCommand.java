package com.demo.game.movement;

import com.demo.game.creature.Creature;
import com.demo.game.map.GameMap;
import com.demo.game.map.cave.Cave;

import java.util.Optional;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public abstract class BaseCreatureMoveCommand {
    protected final Creature creature;
    protected final Cave destinationCave;
    protected final GameMap gameMap;
    protected CreatureMoveResult creatureMoveResult;

    public BaseCreatureMoveCommand(
        Creature creature,
        Cave destinationCave,
        GameMap gameMap,
        CreatureMoveResult creatureMoveResult) {
        this.creature = creature;
        this.destinationCave = destinationCave;
        this.gameMap = gameMap;
        this.creatureMoveResult = creatureMoveResult;
    }

    public abstract void execute();

    public Optional<CreatureMoveResult> getCreatureMoveResult() {
        return Optional.ofNullable(this.creatureMoveResult);
    }
}
