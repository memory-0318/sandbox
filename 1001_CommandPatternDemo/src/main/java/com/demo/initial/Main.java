package com.demo.initial;

import com.demo.complete.Position;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/7
 */
public class Main {
    public static void main(String[] args) {
        scenario1();
        //        scenario2();
        //        scenario3();
    }

    private static void scenario1() {
        AdventurerV1 adventurer = AdventurerV1.builder()
            .setHealth(10)
            .setMana(10)
            .setLevel(1)
            .setPosition(
                com.demo.complete.Position.builder()
                    .setX(0)
                    .setY(0)
                    .build()
            )
            .build();

        System.out.printf("Initial status: %s%n", adventurer);
        adventurer.moveUp();
        System.out.printf("Result status: %s%n", adventurer);
    }

    private static void scenario2() {
        AdventurerV1 adventurer = AdventurerV1.builder()
            .setHealth(10)
            .setMana(10)
            .setLevel(1)
            .setPosition(
                Position.builder()
                    .setX(0)
                    .setY(0)
                    .build()
            )
            .build();

        System.out.printf("Initial status: %s%n", adventurer);
        Player player = new Player();
        player.executeAdventurerCommand(new MoveUpCommandV1(adventurer));
        System.out.printf("Result status: %s%n", adventurer);
    }

    private static void scenario3() {
        AdventurerV1 adventurer = AdventurerV1.builder()
            .setHealth(10)
            .setMana(10)
            .setLevel(1)
            .setPosition(
                Position.builder()
                    .setX(0)
                    .setY(0)
                    .build()
            )
            .build();

        System.out.printf("Initial status: %s%n", adventurer);
        Player player = new Player();
        player.executeAdventurerCommand(new MoveUpCommandV2(adventurer));
        System.out.printf("Result status: %s%n", adventurer);
    }
}
