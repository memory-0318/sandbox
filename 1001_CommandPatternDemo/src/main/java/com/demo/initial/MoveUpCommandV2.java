package com.demo.initial;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/7
 */
public class MoveUpCommandV2 implements Command {
    private final AdventurerV1 adventurer;

    public MoveUpCommandV2(AdventurerV1 adventurer) {this.adventurer = adventurer;}

    @Override
    public void execute() {
        this.adventurer.moveUp(2);
    }
}
