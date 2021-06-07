package com.demo.complete;

import com.demo.complete.strategy.TaxStrategy;
import com.demo.complete.strategy.TaxStrategyForXX;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/8
 */
public class TaxCalculator {
    private final TaxStrategy taxStrategy;

    public TaxCalculator(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public Double calculateTax(Integer income) {
        return this.taxStrategy.calculateTax(income);
    }

    public static void main(String[] args) {
        Integer income = 100;

        TaxCalculator taxCalculator = new TaxCalculator(new TaxStrategyForXX());
        Double tax = taxCalculator.calculateTax(income);

        System.out.printf("Income: $%5d, Tax: $%5d%n", income, tax.intValue());
    }
}
