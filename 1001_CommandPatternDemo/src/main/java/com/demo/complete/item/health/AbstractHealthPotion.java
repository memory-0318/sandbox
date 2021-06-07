package com.demo.complete.item.health;

import lombok.EqualsAndHashCode;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/3
 */
@EqualsAndHashCode
public abstract class AbstractHealthPotion {
    protected final String name;
    protected final Integer amount;

    public AbstractHealthPotion(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
