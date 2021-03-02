package com.demo.game.map.cave;

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
public class CaveConnection {
    private final Cave from;
    private final Cave to;

    public CaveConnection(Cave from, Cave to) {
        this.from = from;
        this.to = to;
    }
}
