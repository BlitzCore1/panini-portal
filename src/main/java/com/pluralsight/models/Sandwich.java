package com.pluralsight.models;

import java.util.ArrayList;

public class Sandwich implements OrderItem
{
    private SandwichSize size;
    private BreadType bread;
    private boolean toasted;

    private ArrayList<Topping> toppings;

    public Sandwich(SandwichSize size, BreadType bread, boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBread() {
        return bread;
    }

    public boolean isToasted() {
        return toasted;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping)
    {
        toppings.add(topping);
    }

    @Override
    public double getPrice() {
        double total = size.getBasePrice();

        for (Topping topping : toppings)
        {
            total += topping.getPrice(size.getSandwichSize());
        }
        return total;
    }

    @Override
    public String getReceiptLine() {
        StringBuilder receipt = new StringBuilder();

        receipt.append(size.getSandwichSize())
                .append("\" ")
                .append(bread)
                .append(" sandwich");

        if (toasted){
        receipt.append(" (toasted)");
        }

        receipt.append(": $")
                .append(String.format("%.2f", getPrice()));

        for (Topping topping : toppings)
        {
            receipt.append("\n  - ")
                    .append(topping.getReceiptLine())
                    .append(": $")
                    .append(String.format("%.2f", topping.getPrice(size.getSandwichSize())));
        }
        return receipt.toString();
    }
}
