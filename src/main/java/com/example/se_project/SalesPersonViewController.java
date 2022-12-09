//@Author: Inas Hamad

package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesPersonViewController {
    private List<String> customers = new ArrayList<>();
    private List<String> items = new ArrayList<>();

    @FXML
    protected ComboBox customersComboBox;
    @FXML
    protected Button createOrderButton;
    @FXML
    protected CheckBox item1;
    @FXML
    protected ComboBox item1ComboBox;

    @FXML
    protected TextField quantity1TextField;

    @FXML
    protected CheckBox item2;
    @FXML
    protected ComboBox item2ComboBox;

    @FXML
    protected TextField quantity2TextField;

    @FXML
    protected CheckBox item3;
    @FXML
    protected ComboBox item3ComboBox;

    @FXML
    protected TextField quantity3TextField;

    @FXML
    protected CheckBox item4;
    @FXML
    protected ComboBox item4ComboBox;

    @FXML
    protected TextField quantity4TextField;

    @FXML
    protected CheckBox item5;
    @FXML
    protected ComboBox item5ComboBox;

    @FXML
    protected TextField quantity5TextField;

    @FXML
    protected DatePicker needByDatePicker;
    @FXML
    protected DatePicker orderDatePicker;


    @FXML
    public void onCustomerComboBoxValueChanged() throws SQLException {

    }
    @FXML
    public void onCreateOrderButtonClick() throws IOException
    {
//        if (customersComboBox.getValue() !=null)
//        {
//            forwardToCreatePage();
//        }
//        else
//        {
//            customerNotSelectedAlert();
//        }
    }
    public void initialize() throws SQLException {
        getCustomersFromDB();
        getItemsFromDB();
        customersComboBox.getItems().addAll(customers);
        item1ComboBox.getItems().addAll(items);
        item2ComboBox.getItems().addAll(items);
        item3ComboBox.getItems().addAll(items);
        item4ComboBox.getItems().addAll(items);
        item5ComboBox.getItems().addAll(items);


    }
//    public void forwardToCreatePage() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("Create-customer-order-view.fxml"));
//        Stage window = (Stage) createOrderButton.getScene().getWindow();
//        window.setScene(new Scene(root));
//    }
    public void getCustomersFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select company_name from customer;");
        addCustomersToList(results);
        connection.closeConnection();
    }
    public void getItemsFromDB() throws SQLException{
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select name from item;");
        addItemsToList(results);
        connection.closeConnection();

    }
    public void addItemsToList(List<Map<String,Object>> results){
        for (int i=0; i<results.size();i++)
        {
            items.add(results.get(i).get("name").toString());
        }
    }
    public void addCustomersToList(List<Map<String, Object>> results) {
        for (int i = 0; i < results.size(); i++) {
            customers.add(results.get(i).get("company_name").toString());
        }
    }
    public void customerNotSelectedAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Customer not selected. Select customer to proceed with order.", ButtonType.OK);
        alert.show();
    }
}
