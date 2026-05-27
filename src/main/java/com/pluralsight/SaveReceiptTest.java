package com.pluralsight;

import com.pluralsight.models.*;
import com.pluralsight.util.ReceiptFileManager;

public class SaveReceiptTest {
    public static void main(String[] args) {
        Order order = new Order();
        order.addOrderItem(new Drink("Large"));
        order.addOrderItem(new Chips("BBQ"));
        Sandwich s = new Sandwich(SandwichSize.EIGHT_INCH, BreadType.RYE, true);
        s.addTopping(new Topping("steak", ToppingCategory.MEAT, true));
        order.addOrderItem(s);

        ReceiptFileManager manager = new ReceiptFileManager();
        manager.saveReceipt(order);

        System.out.println("Called saveReceipt(order). Check: src/main/resources/receipts for a new .txt file");
    }
}