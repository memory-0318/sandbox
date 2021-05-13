package com.demo.game.map;

import com.demo.game.map.cave.Cave;

import java.util.Set;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public interface GameMap {
    Set<Cave> listAllNeighborCaves(Cave cave);

    Set<Cave> listAllCaves();

    boolean isNeighborCave(Cave cave1, Cave cave2);
}
