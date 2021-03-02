package com.demo.simple;

import com.demo.parameterized.AddOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
class SimpleAddOperatorTest {
    private AddOperator addOperator;

    @BeforeEach
    void setUp() {
        this.addOperator = new AddOperator();
    }

    @Test
    void testAdd() {
        Assertions.assertEquals(4, this.addOperator.add(1, 3));
    }

    @Test
    void testAddWithException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addOperator.add(null, null));
    }
}