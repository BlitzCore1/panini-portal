package com.pluralsight.models;

import java.util.ArrayList;

public class Order
{
    private ArrayList<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
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
        StringBuilder receipt = new StringBuilder();

        for (OrderItem item : items)
        {
            receipt.append(item.getReceiptLine()).append("\n");
        }
        receipt.append("Total: $").append(String.format("%.2f", getTotal()));
        return receipt.toString();
    }

    public boolean isEmpty()
    {
        items.isEmpty();
        return items.isEmpty();
    }
}
