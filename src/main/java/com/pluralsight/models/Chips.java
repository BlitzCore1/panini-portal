package com.pluralsight.models;

public class Chips implements OrderItem
{
    private String type;

    public Chips(String type)
    {
        this.type = type;
    }

    @Override
    public double getPrice()
    {
        return 1.50;
    }

    @Override
    public String getReceiptLine()
    {
        return String.format("%s chips - $%.2f", type, getPrice());
    }

    public String getType()
    {
        return type;
    }
}
