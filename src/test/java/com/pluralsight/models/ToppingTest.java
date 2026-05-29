package com.pluralsight.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToppingTest {

    @Test
    void getPrice_shouldReturnZero_whenToppingIsRegular()
    {
        // arrange
        Topping topping = new Topping("onions", ToppingCategory.REGULAR, false);
        int sandwichSize = 8; // 8-inch sandwich
        double expectedPrice = 0.00;

        // act
        double actualPrice = topping.getPrice(sandwichSize);

        // assert
        assertEquals(expectedPrice, actualPrice,
                "because regular toppings should be free regardless of sandwich size");
    }

    @Test
    void getMeatPrice_shouldReturnThree_whenToppingIsMeat()
    {
        // arrange
        Topping topping = new Topping("bacon", ToppingCategory.MEAT, false);
        int sandwichSize = 3; // 12-inch sandwich
        double expectedPrice = 3.00;

        // act
        double actualPrice = topping.getPrice(sandwichSize);

        // assert
        assertEquals(expectedPrice, actualPrice,
                "because meat toppings should cost $3 on 12-inch sandwich");
    }

    @Test
    void getExtraMeatPrice_shouldReturnThree_whenToppingIsMeatExtra()
    {
        // arrange
        Topping topping = new Topping("bacon", ToppingCategory.MEAT, true);
        int sandwichSize = 8 ; // 8-inch sandwich
        double expectedPrice = 3.00; // $2 for the meat + $1 for the extra meat = $3 total

        // act
        double actualPrice = topping.getPrice(sandwichSize);

        // assert
        assertEquals(expectedPrice, actualPrice,
                "because extra meat toppings should cost $1 extra on 8-inch sandwich");
    }

    @Test
    void getCheesePrice_shouldReturnOnePointFive_whenToppingIsCheese()
    {
        // arrange
        Topping topping = new Topping("cheddar", ToppingCategory.CHEESE, false);
        int sandwichSize = 8; // 8-inch sandwich
        double expectedPrice = 1.50;

        // act
        double actualPrice = topping.getPrice(sandwichSize);

        // assert
        assertEquals(expectedPrice, actualPrice,
                "because cheese toppings should cost $1.50 on 8-inch sandwich");
    }

    @Test
    void getExtraCheesePrice_shouldReturnTwoPointOne_whenToppingIsCheeseExtra() {
        // arrange
        Topping topping = new Topping("cheddar", ToppingCategory.CHEESE, true);
        int sandwichSize = 8; // 8-inch sandwich
        double expectedPrice = 2.10; // $1.50 for the cheese + $0.60 for the extra cheese = $2.10 total

        // act
        double actualPrice = topping.getPrice(sandwichSize);

        // assert
        assertEquals(expectedPrice, actualPrice,
                "because extra cheese toppings should cost $0.60 extra on 8-inch sandwich");
    }

    @Test
    void getReceiptLine_shouldIncludeExtra_whenToppingIsExtra() {
        // arrange
        Topping topping = new Topping("bacon", ToppingCategory.MEAT, true);
        String expectedReceiptLine = "bacon (extra)";

        // act
        String actualReceiptLine = topping.getReceiptLine();

        // assert
        assertEquals(expectedReceiptLine, actualReceiptLine,
                "because the receipt line should indicate that the bacon is an extra portion");
    }
}