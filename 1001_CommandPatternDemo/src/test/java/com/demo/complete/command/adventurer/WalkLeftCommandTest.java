package com.demo.complete.command.adventurer;

import com.demo.complete.Adventurer;
import com.demo.complete.Position;
import com.demo.complete.command.adventurer.v1.WalkLeftCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
class WalkLeftCommandTest {
    private static final Adventurer INIT_ADVENTURE = Adventurer.builder()
        .setPosition(
            Position.builder()
                .setX(0)
                .setY(0)
                .build()
        )
        .build();

    @Test
    void testExecute() {
        Integer movementAmount = 1;

        Adventurer adventurer = INIT_ADVENTURE.toBuilder().build();
        new WalkLeftCommand(adventurer, movementAmount).execute();

        Position truePosition = INIT_ADVENTURE.getPosition()
            .toBuilder()
            .setX(INIT_ADVENTURE.getPosition().getX() - movementAmount)
            .build();

        assertThat(adventurer.getPosition())
            .isEqualTo(truePosition);
    }
}