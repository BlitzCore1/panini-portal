package com.pluralsight.ui;

import com.pluralsight.models.*;

import java.util.Scanner;

public class UserInterface
{
    Scanner userInput = new Scanner(System.in);

    public void display()
    {
        boolean running = true;

        while(running)
        {
            displayHomescreen();

            String choice = userInput.nextLine().trim();

            switch(choice){
                case "1":
                    Order order = new Order();
                    displayOrder(order);
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    public void displayHomescreen()
    {
        System.out.println();
        System.out.println("================================");
        System.out.println(" HOME SCREEN");
        System.out.println("================================");
        System.out.println("1) Start New Order ");
        System.out.println("0) Exit Application ");
    }

    public void displayOrder(Order order)
    {
        boolean ordering = true;

        while (ordering)
        {
            System.out.println();
            System.out.println("================================");
            System.out.println(" ORDER SCREEN");
            System.out.println("================================");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option: ");

            String choice = userInput.nextLine().trim();

            switch(choice){
                case "1":
                    addSandwich(order);
                    break;

                case "2":
                    addDrink(order);
                    break;

                case "3":
                    addChips(order);
                    break;

                case "4":
                    checkout(order);
                    break;

                case "0":
                    ordering = false;
                    break;
            }

        }
    }

    public void addSandwich(Order order)
    {
        System.out.println();
        System.out.println("===== Add Sandwich =====");

        BreadType bread = promptBreadType();
        SandwichSize size = promptSandwichSize();
        boolean toasted = promptYesNo("Would you like the sandwich toasted? (y/n): ");

        Sandwich sandwich = new Sandwich(size, bread, toasted);

        addToppingsByCategory(
                sandwich,
                ToppingCategory.MEAT,
                "Meat",
                new String[]{"steak", "ham", "salami", "roast beef", "chicken", "bacon"}
        );

        addToppingsByCategory(
                sandwich,
                ToppingCategory.CHEESE,
                "Cheese",
                new String[]{"american", "provolone", "cheddar", "swiss"}
        );

        addToppingsByCategory(
                sandwich,
                ToppingCategory.REGULAR,
                "Regular Toppings",
                new String[]{"lettuce", "peppers", "onions", "tomatoes", "jalapenos", "cucumbers", "pickles", "guacamole", "mushrooms"}
        );

        addToppingsByCategory(
                sandwich,
                ToppingCategory.SAUCE,
                "Sauces",
                new String[]{"mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette"}
        );

        order.addOrderItem(sandwich);

        System.out.println("Sandwich added to order.");
    }

    private void addToppingsByCategory(Sandwich sandwich, ToppingCategory toppingCategory, String catLabel, String[] options) {
        System.out.println("Would you like to add " + catLabel + "? (y/n): ");
        boolean addToppings = promptYesNo("");

        if (addToppings) {
            System.out.println("Available " + catLabel + ":");
            for (int i = 0; i < options.length; i++) {         // creates a numbered list of options for the user
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Enter the numbers of the " + catLabel.toLowerCase() + " you want to add, separated by commas: ");
            String input = userInput.nextLine().trim();
            String[] selectedOptions = input.split(",");

            for (String option : selectedOptions) {
                try {

                    int index = Integer.parseInt(option.trim()) - 1;
                    if (index >= 0 && index < options.length)
                    {
                        boolean extra = promptYesNo("Make \"" + options[index] + "\" extra? (y/n): ");
                        sandwich.addTopping(new Topping(options[index], toppingCategory,extra));
                    }
                    else {
                        System.out.println("Invalid selection: " + option);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + option);
                }
            }
        }
    }

    private boolean promptYesNo(String message) {
        while (true) {
            System.out.print(message);
            String input = userInput.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;

            } else if (input.equals("n") || input.equals("no")) {
                return false;
                
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    private SandwichSize promptSandwichSize() {
        while (true) {
            System.out.println();
            System.out.println("Select sandwich size:");
            System.out.println("1) 4 inch");
            System.out.println("2) 8 inch");
            System.out.println("3) 12 inch");
            System.out.print("Choose an option: ");

            String choice = userInput.nextLine().trim();

            switch (choice) {
                case "1":
                    return SandwichSize.FOUR_INCH;
                case "2":
                    return SandwichSize.EIGHT_INCH;
                case "3":
                    return SandwichSize.TWELVE_INCH;
                default:
                    System.out.println("Invalid size choice. Please try again.");
            }
        }
    }

    private BreadType promptBreadType() {
        while (true) {
            System.out.println();
            System.out.println("Select your bread:");
            System.out.println("1) White");
            System.out.println("2) Wheat");
            System.out.println("3) Rye");
            System.out.println("4) Wrap");
            System.out.print("Choose an option: ");

            String choice = userInput.nextLine().trim();

            switch (choice) {
                case "1":
                    return BreadType.WHITE;
                case "2":
                    return BreadType.WHEAT;
                case "3":
                    return BreadType.RYE;
                case "4":
                    return BreadType.WRAP;
                case "0":

                default:
                    System.out.println("Invalid bread choice. Please try again.");
            }
        }
    }

    public void addDrink(Order order)
    {}

    public void addChips(Order order)
    {}

     public void checkout(Order order)
    {}
}
