package com.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
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
class AdventurerTest {
    private static final Adventurer INIT_ADVENTURER = Adventurer.builder()
        .setHealth(10)
        .setMana(10)
        .setLevel(5)
        .setPosition(
            Position.builder()
                .setX(50)
                .setY(20)
                .build()
        )
        .build();

    @DisplayName("Movement test suite")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    final class MovementTestSuite {
        @ParameterizedTest
        @MethodSource("provideMovementAmountArguments")
        void testMoveUp(Integer movementAmount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder().build();
            adventurer.moveUp(movementAmount);

            Position truePosition = INIT_ADVENTURER.getPosition().toBuilder()
                .setY(INIT_ADVENTURER.getPosition().getY() + movementAmount)
                .build();
            assertThat(adventurer.getPosition())
                .isEqualTo(truePosition);
        }

        @ParameterizedTest
        @MethodSource("provideMovementAmountArguments")
        void testMoveDown(Integer movementAmount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder().build();
            adventurer.moveDown(movementAmount);

            Position truePosition = INIT_ADVENTURER.getPosition().toBuilder()
                .setY(INIT_ADVENTURER.getPosition().getY() - movementAmount)
                .build();
            assertThat(adventurer.getPosition())
                .isEqualTo(truePosition);
        }

        @ParameterizedTest
        @MethodSource("provideMovementAmountArguments")
        void testMoveRight(Integer movementAmount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder().build();
            adventurer.moveRight(movementAmount);

            Position truePosition = INIT_ADVENTURER.getPosition().toBuilder()
                .setX(INIT_ADVENTURER.getPosition().getX() + movementAmount)
                .build();
            assertThat(adventurer.getPosition())
                .isEqualTo(truePosition);
        }

        @ParameterizedTest
        @MethodSource("provideMovementAmountArguments")
        void testMoveLeft(Integer movementAmount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder().build();
            adventurer.moveLeft(movementAmount);

            Position truePosition = INIT_ADVENTURER.getPosition().toBuilder()
                .setX(INIT_ADVENTURER.getPosition().getX() - movementAmount)
                .build();
            assertThat(adventurer.getPosition())
                .isEqualTo(truePosition);
        }

        private Stream<Arguments> provideMovementAmountArguments() {
            return Stream.of(
                Arguments.of(10),
                Arguments.of(0),
                Arguments.of(-100)
            );
        }
    }

    @DisplayName("Level test suite")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    final class LevelTestSuite {
        @ParameterizedTest
        @MethodSource("provideLevelAmountArguments")
        void testLevelUp(Integer levelAmount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder()
                .build();
            adventurer.levelUp(levelAmount);

            Integer trueLevel = INIT_ADVENTURER.getLevel() + levelAmount;
            assertThat(adventurer.getLevel()).isEqualTo(trueLevel);
        }

        @ParameterizedTest
        @MethodSource("provideLevelAmountArguments")
        void testLevelDown(Integer levelAmount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder()
                .build();
            adventurer.levelDown(levelAmount);

            Integer trueLevel = INIT_ADVENTURER.getLevel() - levelAmount;
            assertThat(adventurer.getLevel()).isEqualTo(trueLevel);
        }

        private Stream<Arguments> provideLevelAmountArguments() {
            return Stream.of(
                Arguments.of(-10),
                Arguments.of(0),
                Arguments.of(10)
            );
        }
    }

    @DisplayName("Health operation test suite")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    final class HealthOperationTestSuite {
        @ParameterizedTest
        @MethodSource("provideAmountArguments")
        void testRecoveryHealth(Integer amount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder()
                .build();
            adventurer.recoveryHealth(amount);

            Integer trueHealth = INIT_ADVENTURER.getHealth() + amount;
            assertThat(adventurer.getHealth())
                .isEqualTo(trueHealth);
        }

        @ParameterizedTest
        @MethodSource("provideAmountArguments")
        void testConsumeHealth(Integer amount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder()
                .build();
            adventurer.consumeHealth(amount);

            Integer trueHealth = INIT_ADVENTURER.getHealth() - amount;
            assertThat(adventurer.getHealth())
                .isEqualTo(trueHealth);
        }

        @ParameterizedTest
        @MethodSource("provideAmountArguments")
        void testRecoveryMana(Integer amount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder()
                .build();
            adventurer.recoveryMana(amount);

            Integer trueMana = INIT_ADVENTURER.getMana() + amount;
            assertThat(adventurer.getMana())
                .isEqualTo(trueMana);
        }

        @ParameterizedTest
        @MethodSource("provideAmountArguments")
        void testConsumeMana(Integer amount) {
            Adventurer adventurer = INIT_ADVENTURER.toBuilder()
                .build();
            adventurer.consumeMana(amount);

            Integer trueMana = INIT_ADVENTURER.getMana() - amount;
            assertThat(adventurer.getMana())
                .isEqualTo(trueMana);
        }

        private Stream<Arguments> provideAmountArguments() {
            return Stream.of(
                Arguments.of(-10),
                Arguments.of(0),
                Arguments.of(10)
            );
        }
    }
}