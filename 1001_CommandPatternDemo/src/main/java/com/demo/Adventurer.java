package com.demo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class Adventurer {
    private Integer health;
    private Integer mana;
    private Integer level;
    private Position position;

    public void moveUp(Integer amount) {
        this.position = this.position.toBuilder()
            .setY(this.position.getY() + amount)
            .build();
    }

    public void moveDown(Integer amount) {
        this.position = this.position.toBuilder()
            .setY(this.position.getY() - amount)
            .build();
    }

    public void moveRight(Integer amount) {
        this.position = this.position.toBuilder()
            .setX(this.position.getX() + amount)
            .build();
    }

    public void moveLeft(Integer amount) {
        this.position = this.position.toBuilder()
            .setX(this.position.getX() - amount)
            .build();
    }

    public void levelUp(Integer amount) {
        this.level += amount;
    }

    public void levelDown(Integer amount) {
        this.level -= amount;
    }

    public void recoveryHealth(Integer amount) {
        this.health += amount;
    }

    public void consumeHealth(Integer amount) {
        this.health -= amount;
    }

    public void recoveryMana(Integer amount) {
        this.mana += amount;
    }

    public void consumeMana(Integer amount) {
        this.mana -= amount;
    }
}
