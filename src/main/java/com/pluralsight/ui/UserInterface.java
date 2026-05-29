package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.util.ReceiptFileManager;

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
                    if (checkout(order))
                    {
                        ordering = false;
                    }
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

        addToppingsByCategory(
                sandwich,
                ToppingCategory.SIDE,
                "Sides",
                new String[]{"au jus", "pepper", "oregano", "salt"}
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
            System.out.println("0) Cancel");
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
                    break;

                default:
                    System.out.println("Invalid bread choice. Please try again.");
            }
        }
    }

    public void addDrink(Order order)
    {
        System.out.println();
        System.out.println("===== Add Drink =====");
        System.out.println("Select a drink cup size:");
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");
        System.out.println("0) Cancel");
        System.out.print("Choose an option: ");

        String choice = userInput.nextLine().trim();

        String size = null;

        switch (choice) {
            case "1":   // adds small drink to the order
                size = "Small";
                break;
            case "2":   // adds medium drink to the order
                size = "Medium";
                break;
            case "3":   // adds large drink to the order
                size = "Large";
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid drink size choice. Please try again.");
                return;

        }

        Drink drink = new Drink(size);
        order.addOrderItem(drink);
        System.out.println(size + " Drink added to order.");
    }

    public void addChips(Order order)
    {
        System.out.println();
        System.out.println("===== Add Chips =====");
        System.out.println("Select a chips flavor:");
        System.out.println("1) Original");
        System.out.println("2) BBQ");
        System.out.println("3) Sour Cream & Onion");
        System.out.println("0) Cancel");
        System.out.print("Choose an option: ");

        String choice = userInput.nextLine().trim();

        String type = null;

        switch (choice) {
            case "1":   // adds original chips to the order
                type = "Original";
                break;
            case "2":   // adds BBQ chips to the order
                type = "BBQ";
                break;
            case "3":   // adds sour cream & onion chips to the order
                type = "Sour Cream & Onion";
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid chips flavor choice. Please try again.");
                return;

        }

        Chips chips = new Chips(type);
        order.addOrderItem(chips);
        System.out.println(type + " Chips added to order.");

    }

     public boolean checkout(Order order)
    {
        if(order.isEmpty())
        {
            System.out.println();
            System.out.println("Your order is empty. Please add items before checking out!");
            return false;
        }

        System.out.println();
        System.out.println("================================");
        System.out.println("           CHECKOUT");
        System.out.println("================================");
        System.out.println(order.getReceipt());

        System.out.println();
        System.out.println("1) Confirm and Pay");
        System.out.println("2) Cancel Order");
        System.out.println("0) Return to order screen");
        System.out.print("Choose an option: ");

        String choice = userInput.nextLine().trim();

        switch (choice) {
            case "1":
                ReceiptFileManager currentOrder = new ReceiptFileManager();
                currentOrder.saveReceipt(order);

                System.out.println("Thank you for your order! Your receipt has been saved.");
                return true;

            case "2":
                System.out.println("Order cancelled. Returning to home screen.");
                return true;

            case "0":
                System.out.println("Checkout cancelled. Returning to order screen.");
                return false;

            default:
                System.out.println("Invalid selection. Returning to order screen.");
                return false;

        }
    }
}
