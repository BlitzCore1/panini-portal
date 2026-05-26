package com.pluralsight.models;

public enum SandwichSize
{
    FOUR_INCH(4, 5.50),
    EIGHT_INCH(8, 7.00),
    TWELVE_INCH(12, 8.50);

    private int sandwichSize;
    private double basePrice;

    SandwichSize(int sandwichSize, double basePrice) {
        this.sandwichSize = sandwichSize;
        this.basePrice = basePrice;
    }

    public int getSandwichSize() {
        return sandwichSize;
    }
    public double getBasePrice() {
        return basePrice;
    }
}
