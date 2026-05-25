package com.pluralsight.models;

public class Chips implements OrderItem
{
    private String type;

    @Override
    public double getPrice()
    {
        return 0;
    }

    @Override
    public String getReceiptLine()
    {
        return "";
    }
}
