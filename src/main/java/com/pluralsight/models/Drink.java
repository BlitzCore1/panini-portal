package com.pluralsight.models;

public class Drink implements OrderItem
{
    private String size;

    public Drink(String size)
    {
        this.size = size;
    }

    @Override
    public double getPrice()
    {
        return switch (this.size) {
            case "Small" -> 2.00;
            case "Medium" -> 2.50;
            case "Large" -> 3.00;
            default -> 0;
        };
    }

    @Override
    public String getReceiptLine()
    {
        return String.format("%s drink - $%.2f", size, getPrice());
    }

    public String getSize()
    {
        return size;
    }
}
