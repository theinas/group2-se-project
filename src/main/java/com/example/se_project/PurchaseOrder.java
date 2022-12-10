package com.example.se_project;
//Joshua
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrder implements totalCost{
    protected String vendorBoughtFrom;
    protected ArrayList<PurchaseOrderItem> items = new ArrayList<>();
    protected Date needBy;

    public PurchaseOrder()
    {
        vendorBoughtFrom = null;
        items = null;
        needBy = null;
    }
    public PurchaseOrder( String vendor, Date needBy)
    {
        this.vendorBoughtFrom = vendor;
        this.needBy = needBy;
    }

    public void addItemToOrder(Item item, double quantity)
    {
        items.add(new PurchaseOrderItem(item, quantity));
    }
    public void addPurchaseOrderToDB() throws SQLException {
        DBConnection connection = new DBConnection();
        for (int i =0; i < items.size(); i++)
        {
            connection.addEntryToDB(query(getVendorIDFromDB(),
                    getItemIDFromDB(items.get(i).getItem()), items.get(i).getAmount()));
        }
    }

    public double totalCost()
    {

        double totalCost =0;
        for (int i=0; i<items.size(); i++)
        {
            double price = items.get(i).getItem().getPurchasePrice() * items.get(i).getAmount();
            double tax = price * 0.06;

            totalCost += price+tax;
        }
        return totalCost;
    }
    public double subTotal()
    {
        double subTotal = 0;
        for (int i =0; i <items.size(); i++)
        {
            subTotal += items.get(i).getItem().getSalePrice() * items.get(i).getAmount();
        }
        return subTotal;
    }
    public String query(int vendorID, int itemID, double quantity)
    {
        String query = "insert into purchase_order (vendor_id, item_id, need_by, quantity, subtotal, final_total) values ("+
                vendorID +","+itemID+",'"+ needBy.toString()+"','"+"',"+quantity+","+subTotal()+","+totalCost()+");";
        return query;
    }

    public int getVendorIDFromDB() throws SQLException
    {
        DBConnection connection = new DBConnection();
        int ID=  Integer.parseInt(connection.getResults("select ID from vendor where company_name ='"+vendorBoughtFrom+"');").get(0).get("ID").toString());
        connection.closeConnection();
        return ID;
    }
    public int getItemIDFromDB(Item item) throws SQLException
    {
        DBConnection connection = new DBConnection();
        int ID = Integer.parseInt(connection.getResults("select ID from item where name ='" + item.getItemName()+"');").get(0).get("ID").toString());
        connection.closeConnection();
        return ID;
    }
}
