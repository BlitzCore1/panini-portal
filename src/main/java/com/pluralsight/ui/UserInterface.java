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


    }

    public void addDrink(Order order)
    {}

    public void addChips(Order order)
    {}

     public void checkout(Order order)
    {}
}
