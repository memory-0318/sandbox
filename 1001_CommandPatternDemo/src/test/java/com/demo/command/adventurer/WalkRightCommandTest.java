package com.demo.command.adventurer;

import com.demo.Adventurer;
import com.demo.Position;
import com.demo.command.adventurer.v1.WalkRightCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
class WalkRightCommandTest {
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
        new WalkRightCommand(adventurer, movementAmount).execute();

        Position truePosition = INIT_ADVENTURE.getPosition()
            .toBuilder()
            .setX(INIT_ADVENTURE.getPosition().getX() + movementAmount)
            .build();

        assertThat(adventurer.getPosition())
            .isEqualTo(truePosition);
    }
}