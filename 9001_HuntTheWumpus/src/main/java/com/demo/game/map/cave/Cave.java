package com.demo.game.map.cave;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
@Data
@Setter(AccessLevel.NONE)
public class Cave {
    private final String label;

    public Cave(String label) {
        this.label = label;
    }
}
