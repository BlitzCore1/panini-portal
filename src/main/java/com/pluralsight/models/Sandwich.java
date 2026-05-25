package com.pluralsight.models;

import java.util.ArrayList;

public class Sandwich
{
    private String size;
    private String bread;
    private boolean toasted;

    ArrayList<Topping> toppings = new ArrayList<Topping>();

    public Sandwich()
    {}

    public Sandwich(String size, String bread, boolean toasted, ArrayList<Topping> toppings) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
        this.toppings = toppings;
    }
}
