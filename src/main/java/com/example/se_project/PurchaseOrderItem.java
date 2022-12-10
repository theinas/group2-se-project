package com.example.se_project;

public class PurchaseOrderItem {
    Item item;
    double amount;

    public PurchaseOrderItem(Item item, double amount)
    {
        this.item = item;
        this.amount = amount;
    }

    public double getSubTotal()
    {
        double sub = this.item.getPurchasePrice() * this.amount;
        return sub;
    }
    public Item getItem() {
        return item;
    }

    public double getAmount()
    {
        return amount;
    }
}
