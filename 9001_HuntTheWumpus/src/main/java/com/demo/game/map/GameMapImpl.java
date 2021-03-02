package com.demo.game.map;

import com.demo.game.map.cave.Cave;
import com.demo.game.map.cave.CaveConnection;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public class GameMapImpl implements GameMap {
    private Multimap<Cave, CaveConnection> caveToCaveConnectionsMap = LinkedHashMultimap.create();

    public GameMapImpl(Set<CaveConnection> caveConnections) {
        this.setupGameMap(caveConnections);
    }

    @Override
    public Set<Cave> listAllNeighborCaves(Cave cave) {
        Set<Cave> results = this.caveToCaveConnectionsMap.get(cave)
            .stream()
            .map(neighborCaveConnection -> Arrays
                .asList(neighborCaveConnection.getFrom(), neighborCaveConnection.getTo()))
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());

        results.remove(cave);

        return results;
    }

    @Override
    public boolean isNeighborCave(Cave cave1, Cave cave2) {
        CaveConnection queryCaveConnection1 = CaveConnection.builder()
            .setFrom(cave1)
            .setTo(cave2)
            .build();
        CaveConnection queryCaveConnection2 = CaveConnection.builder()
            .setFrom(cave2)
            .setTo(cave1)
            .build();
        Collection<CaveConnection> caveConnections = this.caveToCaveConnectionsMap.get(cave1);

        return caveConnections.stream()
            .anyMatch(caveConnection -> Objects.equals(caveConnection, queryCaveConnection1)
                || Objects.equals(caveConnection, queryCaveConnection2));
    }

    protected void setupGameMap(Set<CaveConnection> caveConnections) {
        for (CaveConnection caveConnection : caveConnections) {
            Cave caveFromNode = caveConnection.getFrom();
            Cave caveToNode = caveConnection.getTo();

            this.caveToCaveConnectionsMap.put(caveFromNode, caveConnection);
            this.caveToCaveConnectionsMap.put(caveToNode, caveConnection);
        }
    }
}
