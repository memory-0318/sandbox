package com.demo.complete.strategy;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/8
 */
public interface TaxStrategy {
    Double calculateTax(Integer income);
}
