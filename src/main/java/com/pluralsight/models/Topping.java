package com.pluralsight.models;

public class Topping
{
    private String name;
    private ToppingCategory category;
    private boolean extra;

    public Topping(String name, ToppingCategory category, boolean extra) {
        this.name = name;
        this.category = category;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public ToppingCategory getCategory() {
        return category;
    }

    public boolean isExtra() {
        return extra;
    }

    public double getPrice(int sandwichSize) {
        double price = 0.00;

        if (category == ToppingCategory.MEAT)
        {
            price += getMeatPrice(sandwichSize);
            if (extra) {
                price += getExtraMeatPrice(sandwichSize);
            }
        }
        else if (category == ToppingCategory.CHEESE)
        {
            price += getCheesePrice(sandwichSize);
            if (extra) {
                price += getExtraCheesePrice(sandwichSize);
            }
        }
        return price;
    }

    public double getMeatPrice(int sandwichSize) {
        double price = 0.00;

        if (category == ToppingCategory.MEAT)
        {
            price = switch (sandwichSize)
            {
                case 4 ->    // 4-inch
                        1.00;
                case 8 ->   // 8-inch
                        2.00;
                case 12 ->    // 12-inch
                        3.00;
                default -> price;
            };
        }
        return price;
    }

    public double getExtraMeatPrice(int sandwichSize) {
        double price = 0.00;

        if (category == ToppingCategory.MEAT && extra)
        {
            price = switch (sandwichSize) {
                case 4 ->    // 4-inch
                        0.50;
                case 8 ->   // 8-inch
                        1.00;
                case 12 ->    // 12-inch
                        1.50;
                default -> price;
            };
        }
        return price;
    }

    public double getCheesePrice(int sandwichSize) {
        double price = 0.00;
        if (category == ToppingCategory.CHEESE)
        {
            price = switch (sandwichSize) {
                case 4 ->     // 4-inch
                        0.75;
                case 8 ->   // 8-inch
                        1.50;
                case 12 ->    // 12-inch
                        2.25;
                default -> price;
            };
        }
        return price;
    }

    public double getExtraCheesePrice(int sandwichSize) {
        double price = 0.00;
        if (category == ToppingCategory.CHEESE && extra)
        {
            price = switch (sandwichSize) {
                case 4 ->     // 4-inch
                        0.30;
                case 8 ->   // 8-inch
                        0.60;
                case 12 ->    // 12-inch
                        0.90;
                default -> price;
            };
        }
        return price;
    }

    public String getReceiptLine(){
        if (extra){
            return name + " (extra)";
        }
        return name;
    }
}
