package com.demo.parameterized;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public class AddOperator {
    public Integer add(Integer input1, Integer input2) {
        if (input1 == null) {
            throw new IllegalArgumentException("input1 must not be null");
        }
        if (input2 == null) {
            throw new IllegalArgumentException("input2 must not be null");
        }

        return input1 + input2;
    }
}
