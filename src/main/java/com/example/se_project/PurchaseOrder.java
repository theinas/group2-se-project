package com.example.se_project;

import java.util.ArrayList;

//Joshua
public class PurchaseOrder {
    int purchaseID;
    ArrayList<Item> orderedItems = new ArrayList<>();
    Date needBy;
    int totalItems = 0;
    double quantity;
    double subtotal1, subtotal2, subtotal3, subtotal4, subtotal5 = 0;
    double total;

    void updateBalance(double total)
    {
        //need to code method to update the balance for the vendor
        //that a purchase order is for
    };
    void calculateTotal()
        {
        double temp = subtotal1 + subtotal2 + subtotal3 + subtotal4 + subtotal5;
        double tax = temp * 0.06;
        total = temp + tax;
        }
}