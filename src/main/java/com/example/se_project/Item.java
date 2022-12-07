package com.example.se_project;

import java.sql.SQLException;

//declare class item with all necessary members
public class Item {
    //enums for categories and measurements
    public enum Categories {fruits, nuts, dairy, meat, snacks, soda, juice, bakery};
    public enum Measurements {pounds, gallons, dozen, ounces, fluidOunces};
    //required members of item class
    public int itemID;
    public String itemName;
    public int vendorID;
    public double salePrice;
    Categories category;
    public Date expiration;
    public double purchasePrice;
    Measurements measurement;
    public double quantityOnHand;

    //method to add item to database
    public void addToDB() throws SQLException {
        String query = "INSERT INTO item(itemID, itemName, vendorID, salePrice, category, expiration," +
                "purchasePrice, measurement, quantityOnHand) values('"+ itemID + "','"+ itemName+ "','"+
                vendorID + "','"+ salePrice+ "','"+ category + "','" + expiration + "'.'" + purchasePrice +
                "','" + measurement + "','" + quantityOnHand + "');'";
        DBConnection connection = new DBConnection();
        connection.addEntryToDB(query);

        connection.closeConnection();

    }

    //constructor with all fields filled out
    Item(int itemID, int vendorID,double salePrice, Categories category, Date expiration,
         double purchasePrice, Measurements measurement, double quantityOnHand) throws SQLException
    {
        this.itemID = itemID;
        this.vendorID = vendorID;
        this.salePrice = salePrice;
        this.category = category;
        this.expiration = expiration;
        this.purchasePrice = purchasePrice;
        this.measurement = measurement;
        this.quantityOnHand = quantityOnHand;
        addToDB();
    }

    //constructor with only item name
    Item(String itemName) throws SQLException
    {
        this.itemName = itemName;
        addToDB();
    }

    //constructor with only item name and vendorID
    Item(String itemName, int vendorID) throws SQLException
    {
        this.itemName = itemName;
        this.vendorID = vendorID;
        addToDB();
    }

    //get and set methods for all members
    //itemID
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public int getItemID() {
        return itemID;
    }

    //item name
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemName() {
        return itemName;
    }

    //vendorID
    public void setVendorID(int vendorID)
    {
        this.vendorID = vendorID;
    }
    public int getVendorID()
    {
        return vendorID;
    }

    //sale price
    public void setSalePrice(double salePrice)
    {
        this.salePrice = salePrice;
    }
    public double getSalePrice()
    {
        return salePrice;
    }

    //category
    public void setCategory(Categories category)
    {
        this.category = category;
    }
    public Categories getCategory()
    {
        return category;
    }

    //date
    public void setExpiration(Date expirationDate)
    {
        this.expiration = expirationDate;
    }
    public Date getExpiration()
    {
        return expiration;
    }

    //purchase price
    public void setPurchasePrice(double purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }
    public double getPurchasePrice()
    {
        return purchasePrice;
    }

    //measurement
    public void setMeasurement(Measurements measurement)
    {
        this.measurement = measurement;
    }
    public Measurements getMeasurement()
    {
        return measurement;
    }

    //quantity
    public void setQuantityOnHand(double quantityOnHand)
    {
        this.quantityOnHand = quantityOnHand;
    }
    public double getQuantityOnHand()
    {
        return quantityOnHand;
    }
}