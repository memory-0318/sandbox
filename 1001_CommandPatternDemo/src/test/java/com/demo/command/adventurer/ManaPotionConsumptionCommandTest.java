package com.demo.command.adventurer;

import com.demo.Adventurer;
import com.demo.item.mana.AbstractManaPotion;
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
class ManaPotionConsumptionCommandTest {
    private static final Adventurer INIT_ADVENTURE = Adventurer.builder()
        .setMana(10)
        .build();
    private ManaPotionConsumptionCommand command;

    @ParameterizedTest
    @MethodSource("provideManaPotionAmount")
    void testExecute(AbstractManaPotion manaPotion) {
        Adventurer adventurer = INIT_ADVENTURE.toBuilder().build();
        this.command = new ManaPotionConsumptionCommand(adventurer, manaPotion);

        this.command.execute();

        assertThat(adventurer.getMana())
            .isEqualTo(INIT_ADVENTURE.getMana() + manaPotion.getAmount());
    }

    private static Stream<Arguments> provideManaPotionAmount() {
        return Stream.of(
            Arguments.of(new AbstractManaPotion("", 100) {}),
            Arguments.of(new AbstractManaPotion("", 0) {}),
            Arguments.of(new AbstractManaPotion("", -100) {})
        );
    }
}