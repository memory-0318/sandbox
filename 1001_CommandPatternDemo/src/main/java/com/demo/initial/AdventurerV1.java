package com.demo.initial;

import com.demo.complete.Position;
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
public class AdventurerV1 {
    private Integer health;
    private Integer mana;
    private Integer level;
    private Position position;

    public void moveUp() {
        this.position = this.position.toBuilder()
            .setY(this.position.getY() + 1)
            .build();
    }

    public void moveDown() {
        this.position = this.position.toBuilder()
            .setY(this.position.getY() - 1)
            .build();
    }

    public void moveRight() {
        this.position = this.position.toBuilder()
            .setX(this.position.getX() + 1)
            .build();
    }

    public void moveLeft() {
        this.position = this.position.toBuilder()
            .setX(this.position.getX() - 1)
            .build();
    }

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
}
