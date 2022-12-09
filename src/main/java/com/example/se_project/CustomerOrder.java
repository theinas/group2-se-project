package com.example.se_project;

import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrder implements totalCost{
   private String company;
   private ArrayList<CustomerOrderItem> items = new ArrayList<>();
   Date needBy;
   Date orderDate;


   public CustomerOrder( String customer, Date needBy, Date orderDate)
   {
      this.company = customer;
      this.needBy = needBy;
      this.orderDate = orderDate;
   }
   public void addItemToOrder(Item item, double quantity)
   {
      items.add(new CustomerOrderItem(item, quantity));
   }
   public void addCustomerOrderToDB() throws SQLException {
      DBConnection connection = new DBConnection();
      for (int i =0; i < items.size(); i++)
      {
         connection.addEntryToDB(queryBuilder(getCustomerIDFromDB(), getItemIDFromDB(items.get(i).getItem()), items.get(i).getQuantity()));
      }
   }

   public double totalCost()
   {

      double totalCost =0;
      for (int i=0; i<items.size(); i++)
      {
         double price = items.get(i).getItem().getSalePrice() * items.get(i).getQuantity();
         double tax = price * MICHIGAN_TAX_RATE;

        totalCost += price+tax;
      }
      return totalCost;
   }
   public double subTotal()
   {
      double subTotal =0;
      for (int i =0; i <items.size(); i++)
      {
         subTotal += items.get(i).getItem().getSalePrice() * items.get(i).getQuantity();
      }
      return subTotal;
   }
   public String queryBuilder(int customerID, int itemID, double quantity)
   {
      String query = "insert into customer_order (customer_id, item_id, need_by, order_date, quantity, subtotal, final_total) values ("+
      customerID +","+itemID+",'"+ needBy.toString()+"','"+orderDate.toString()+"',"+quantity+","+subTotal()+","+totalCost()+");";
      return query;
   }

   public int getCustomerIDFromDB() throws SQLException
   {
      DBConnection connection = new DBConnection();
      int ID=  Integer.parseInt(connection.getResults("select ID from customer where company_name ='"+company+"');").get(0).get("ID").toString());
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
