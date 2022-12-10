package com.example.se_project;
//Joshua
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.se_project.LoginController.FINAL_ROLE;

public class DeleteItemViewController {
    //need to query for items, orders, and invoices using item ID's, then convert valid IDs back item names
    private List<String> deletableItems = new ArrayList<>();
    private List<Integer> allItems = new ArrayList<>();
    private List<Integer> itemsInOrdersAndInvoices = new ArrayList<>();
    Map<Integer, String> convertItems = new HashMap<>();
    List<Map<String, Object>> itemDBresults;
    List<Map<String, Object>> purchaseOrderDBresults;
    List<Map<String, Object>> invoiceDBresults;

    @FXML
    protected ComboBox itemsComboBox;
    @FXML
    protected Button deleteItemButton;
    @FXML
    protected Label message;
    @FXML
    protected Button backToMainButton;

    @FXML
    public void onDeleteItemButtonClick() throws SQLException {
        if(itemsComboBox.getValue()!= null)
        {
            deleteItemFromDB();
            message.setText(itemsComboBox.getValue().toString() + " was deleted");

        }
        else
        {
            message.setText("Select an item to delete");
        }
    }
    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        if(FINAL_ROLE.equals("OWNER")){
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));}
        else if (FINAL_ROLE.equals("PURCHASER")){
            Parent root = FXMLLoader.load(getClass().getResource("Purchaser-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));}
        else if (FINAL_ROLE.equals("INVENTORY_MANAGER")){
            Parent root = FXMLLoader.load(getClass().getResource("InventoryManager-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));}
        else{
            Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));}
        }
    public void initialize() throws SQLException {
        getItemsOrdersInvoicesFromDB();
        itemsComboBox.getItems().addAll(deletableItems);
    }

    public void getItemsOrdersInvoicesFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        itemDBresults = connection.getResults("select id from item;");
        purchaseOrderDBresults = connection.getResults("select item_id from purchase_order");
        invoiceDBresults = connection.getResults("select item_id from customer_order");
        addItemsOrdersInvoicesToList(itemDBresults, purchaseOrderDBresults, invoiceDBresults);
        createDeletableList();
        connection.closeConnection();

    }
    public void addItemsOrdersInvoicesToList(List<Map<String, Object>> results1,
                                             List<Map<String, Object>> results2,
                                             List<Map<String, Object>> results3) {
        for (int i = 0; i < results1.size(); i++) {
            allItems.add((Integer)results1.get(i).get("ID"));
            convertItems.put((Integer)results1.get(i).get("ID"), results1.get(i).get("name").toString());
        }
        for (int i = 0; i < results2.size(); i++) {
            itemsInOrdersAndInvoices.add((Integer)results1.get(i).get("ID"));
        }
        for (int i = 0; i < results3.size(); i++) {
            itemsInOrdersAndInvoices.add((Integer)results1.get(i).get("ID"));
        }
    }
    public void createDeletableList() {
        ArrayList<Integer> tempList = new ArrayList<>();
        boolean flag = false;
        for(int i = 0; i < allItems.size(); i++)
        {
            if(!itemsInOrdersAndInvoices.contains((allItems.get(i))))
                tempList.add(allItems.get(i));
        }
        for(int i = 0; i < tempList.size(); i++)
        {
            deletableItems.add(convertItems.get(tempList.get(i)));
        }
    }
    public String query()
    {
        return "delete from item where name='"+itemsComboBox.getValue().toString()+"';";
    }
    public void deleteItemFromDB() throws SQLException
    {
        DBConnection connection = new DBConnection();
        connection.addEntryToDB(query());
        connection.closeConnection();
    }



}
