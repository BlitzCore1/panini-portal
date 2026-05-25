package com.pluralsight.models;

import java.util.ArrayList;

public class Order
{
    private ArrayList<OrderItem> items;

    public Order() {
        this.items = new ArrayList<OrderItem>();
    }

    public void addOrderItem(OrderItem orderItem)
    {
        items.add(orderItem);
    }

    public ArrayList<OrderItem> getItems()
    {
        return items;
    }

    public double getTotal()
    {
        double total = 0;
        for (OrderItem item : items)
        {
            total += item.getPrice();
        }
        return total;
    }

    public String getReceipt()
    {
        String receipt = "";
        for (OrderItem item : items)
        {
            receipt += item.getReceiptLine() + "\n";
        }
        receipt += "Total: $" + getTotal();
        return receipt;
    }

    public boolean isEmpty()
    {
        if (items.isEmpty())
        {
            System.out.println("Your order is empty.");
        }

        return items.isEmpty();
    }
}
