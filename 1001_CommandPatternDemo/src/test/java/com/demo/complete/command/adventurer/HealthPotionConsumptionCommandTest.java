package com.demo.complete.command.adventurer;

import com.demo.complete.Adventurer;
import com.demo.complete.item.health.AbstractHealthPotion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
class HealthPotionConsumptionCommandTest {
    private static final Adventurer INIT_ADVENTURE = Adventurer.builder()
        .setHealth(10)
        .build();
    private HealthPotionConsumptionCommand command;

    @ParameterizedTest
    @MethodSource("provideHealthPotionAmount")
    void testExecute(AbstractHealthPotion healthPotion) {
        Adventurer adventurer = INIT_ADVENTURE.toBuilder().build();
        this.command = new HealthPotionConsumptionCommand(adventurer, healthPotion);

        this.command.execute();

        assertThat(adventurer.getHealth())
            .isEqualTo(INIT_ADVENTURE.getHealth() + healthPotion.getAmount());
    }

    private static Stream<Arguments> provideHealthPotionAmount() {
        return Stream.of(
            Arguments.of(new AbstractHealthPotion("", 100) {}),
            Arguments.of(new AbstractHealthPotion("", 0) {}),
            Arguments.of(new AbstractHealthPotion("", -100) {})
        );
    }
}