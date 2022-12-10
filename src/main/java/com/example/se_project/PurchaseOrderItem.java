package com.example.se_project;

public class PurchaseOrderItem {
    Item item;
    double amount;

    public PurchaseOrderItem(Item item, double amount)
    {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public double getAmount()
    {
        return amount;
    }
}
