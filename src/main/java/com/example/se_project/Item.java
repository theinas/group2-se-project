package com.example.se_project;
//Joshua
import java.sql.SQLException;

//declare class item with all necessary members
public class Item {
    //required members of item class
    protected int itemID;
    protected String itemName;
    protected int vendorID;
    protected double salePrice;
    protected Categories category;
    protected Date expiration;
    protected double purchasePrice;
    protected Measurements measurement;
    protected double quantityOnHand;

    //method to add item to database
    public void addToDB() throws SQLException {
        String query = "INSERT INTO item(name, vendor_id, selling_price, category, expiration," +
                "purchase_price, measurement_unit, quantity_available) values('" + "','"+ itemName+ "','"+
                vendorID + "','"+ salePrice+ "','"+ category + "','" + expiration + "'.'" + purchasePrice +
                "','" + measurement + "','" + quantityOnHand + "');'";
        DBConnection connection = new DBConnection();
        connection.addEntryToDB(query);

        connection.closeConnection();

    }

    //constructor with all fields filled out
    Item(String itemName, int vendorID,double salePrice, Categories category, Date expiration,
         double purchasePrice, Measurements measurement, double quantityOnHand) throws SQLException
    {
        this.itemName = itemName;
        this.vendorID = vendorID;
        this.salePrice = salePrice;
        this.category = category;
        this.expiration = expiration;
        this.purchasePrice = purchasePrice;
        this.measurement = measurement;
        this.quantityOnHand = quantityOnHand;
        addToDB();
    }


    //Default Constructor
    public Item()
    {

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
        InputValidator v = new InputValidator();
        if(v.validateLength(itemName, 20))
        {
            this.itemName = itemName;
        }
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
