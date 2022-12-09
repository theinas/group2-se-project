package com.example.se_project;

public class CustomerOrderItem {
    Item item;
    double quantity;

    public CustomerOrderItem(Item item, double quantity)
    {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public double getQuantity()
    {
        return quantity;
    }
}
