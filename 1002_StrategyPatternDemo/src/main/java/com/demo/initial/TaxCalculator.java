package com.demo.initial;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/8
 */
public class TaxCalculator {
    public Double calculateTax(Integer income) {
        return income * 0.05d;
    }

    public static void main(String[] args) {
        Integer income = 100;

        TaxCalculator taxCalculator = new TaxCalculator();
        Double tax = taxCalculator.calculateTax(income);

        System.out.printf("Income: $%5d, Tax: $%5d%n", income, tax.intValue());
    }
}
