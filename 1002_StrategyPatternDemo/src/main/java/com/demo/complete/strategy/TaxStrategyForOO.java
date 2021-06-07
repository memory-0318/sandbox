package com.demo.complete.strategy;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/8
 */
public class TaxStrategyForOO implements TaxStrategy {
    @Override
    public Double calculateTax(Integer income) {
        return income * 0.05d;
    }
}
