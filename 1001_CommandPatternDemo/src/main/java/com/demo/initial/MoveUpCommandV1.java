package com.demo.initial;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/7
 */
public class MoveUpCommandV1 implements Command {
    private final AdventurerV1 adventurer;

    public MoveUpCommandV1(AdventurerV1 adventurer) {this.adventurer = adventurer;}

    @Override
    public void execute() {
        this.adventurer.moveUp();
    }
}
