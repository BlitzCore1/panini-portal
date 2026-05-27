package com.pluralsight.util;

import com.pluralsight.models.Order;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager
{
    private final String filePath;

    public ReceiptFileManager() {
        this.filePath = "src/main/resources/receipts";
    }

    public void saveReceipt(Order order) {
        try
        {
            File folder = new File(filePath);
            if (!folder.exists()) {
                folder.mkdirs();  // will create directory if one doesn't exists
            }
        }
        finally {}

        String fileName = generateReceiptFileName();
        File file = new File(filePath, fileName);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file)))
        {
            writer.print(order.getReceipt()); // writes the receipt content
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String generateReceiptFileName() {
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

        return date.format(formatter) + ".txt";
    }
}
