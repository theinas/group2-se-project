//@Author: Inas Hamad

package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesPersonViewController {
    private List<String> customers = new ArrayList<>();

    @FXML
    protected ComboBox customersComboBox;
    @FXML
    protected Button createOrderButton;


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
        customersComboBox.getItems().addAll(customers);
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
