package com.demo;

import com.demo.command.CommandLogDecorator;
import com.demo.command.adventurer.v1.WalkDownCommand;
import com.demo.command.adventurer.v1.WalkLeftCommand;
import com.demo.command.adventurer.v1.WalkUpCommand;
import com.demo.command.macro.AdventurerMovementMacro;
import com.demo.factory.CommandFactory;
import com.demo.factory.v1.ConcreteCommandFactory;

import java.util.Arrays;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/4
 */
public class Main {
    public static void main(String[] args) {
        scenario1();
        scenario2();
        scenario3();
        scenario4();
        scenario5();
    }

    // Basic usage of command pattern
    private static void scenario1() {
        System.out.println("Command Pattern基本使用");

        Player player1 = new Player();
        Adventurer adventurer1 = Adventurer.builder()
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

        Integer walkMovementAmount = 1;
        player1.executeAdventurerCommand(
            new CommandLogDecorator(new WalkUpCommand(adventurer1, walkMovementAmount))
        );
        player1.executeAdventurerCommand(
            new CommandLogDecorator(new WalkLeftCommand(adventurer1, walkMovementAmount))
        );
        player1.executeAdventurerCommand(
            new CommandLogDecorator(new WalkDownCommand(adventurer1, walkMovementAmount))
        );
        player1.executeAdventurerCommand(
            new CommandLogDecorator(new WalkDownCommand(adventurer1, walkMovementAmount))
        );
    }

    // Use decorator to decorate command
    private static void scenario2() {
        System.out.println("Command Pattern結合Decorator Pattern使用");

        Integer walkMovementAmount = 1;

        Player player = new Player();
        Adventurer adventurer = Adventurer.builder()
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
        AdventurerMovementMacro macro = new AdventurerMovementMacro(
            Arrays.asList(
                new CommandLogDecorator(new WalkUpCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(new WalkLeftCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(new WalkDownCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(new WalkDownCommand(adventurer, walkMovementAmount))
            )
        );
        player.executeAdventurerCommand(macro);
    }

    // Use macro to execute multiple commands at once
    private static void scenario3() {
        System.out.println("Command Pattern進階應用 - 指令巨集");

        Integer walkMovementAmount = 1;

        Player player = new Player();
        Adventurer adventurer = Adventurer.builder()
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

        AdventurerMovementMacro macro = new AdventurerMovementMacro(
            Arrays.asList(
                new CommandLogDecorator(new WalkUpCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(new WalkLeftCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(new WalkDownCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(new WalkDownCommand(adventurer, walkMovementAmount))
            )
        );
        player.executeAdventurerCommand(macro);
    }

    // Use abstract factory to create command (v1)
    private static void scenario4() {
        System.out.println("使用Abstract Factory產生Command (v1)");
        Integer walkMovementAmount = 1;

        Player player = new Player();
        Adventurer adventurer = Adventurer.builder()
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

        CommandFactory commandFactory = new ConcreteCommandFactory();

        AdventurerMovementMacro macro = new AdventurerMovementMacro(
            Arrays.asList(
                new CommandLogDecorator(commandFactory.createWalkUpCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(commandFactory.createWalkLeftCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(commandFactory.createWalkDownCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(commandFactory.createWalkDownCommand(adventurer, walkMovementAmount))
            )
        );
        player.executeAdventurerCommand(macro);
    }

    // Use abstract factory to create commands (v2)
    private static void scenario5() {
        System.out.println("使用Abstract Factory產生Command (v2)");
        Integer walkMovementAmount = 1;

        Player player = new Player();
        Adventurer adventurer = Adventurer.builder()
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

        CommandFactory commandFactory = new com.demo.factory.v2.ConcreteCommandFactory();

        AdventurerMovementMacro macro = new AdventurerMovementMacro(
            Arrays.asList(
                new CommandLogDecorator(commandFactory.createWalkUpCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(commandFactory.createWalkLeftCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(commandFactory.createWalkDownCommand(adventurer, walkMovementAmount)),
                new CommandLogDecorator(commandFactory.createWalkDownCommand(adventurer, walkMovementAmount))
            )
        );
        player.executeAdventurerCommand(macro);
    }
}
