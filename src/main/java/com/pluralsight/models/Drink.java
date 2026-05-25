package com.pluralsight.models;

public class Drink implements OrderItem
{
    private String size;
    private String flavor;

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
