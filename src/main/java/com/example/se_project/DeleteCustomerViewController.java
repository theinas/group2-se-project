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

public class DeleteCustomerViewController {
    private List<String> customers = new ArrayList<>();
    List<Map<String, Object>> results;

    @FXML
    protected ComboBox customersComboBox;
    @FXML
    protected Button deleteCustomerButton;
    @FXML
    protected Label message;
    @FXML
    protected Button backToMainButton;

    @FXML
    public void onDeleteCustomerButtonClick() throws SQLException {
        if(customersComboBox.getValue()!= null)
        {
                deleteCustomerFromDB();
                message.setText(customersComboBox.getValue().toString() + " was deleted");

        }
        else
        {
            message.setText("Select a customer to delete");
        }
    }
    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void initialize() throws SQLException {
        getCustomersFromDB();
        customersComboBox.getItems().addAll(customers);
    }

    public void getCustomersFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        results = connection.getResults("select company_name from customer;");
        addCustomersToList(results);
        connection.closeConnection();

    }
    public void addCustomersToList(List<Map<String, Object>> results) {
        for (int i = 0; i < results.size(); i++) {
            customers.add(results.get(i).get("company_name").toString());
        }
    }
    public String queryBuilder()
    {
        return "delete from customer where company_name='"+customersComboBox.getValue().toString()+"';";
    }
    public void deleteCustomerFromDB() throws SQLException
    {
        DBConnection connection = new DBConnection();
        connection.addEntryToDB(queryBuilder());
        connection.closeConnection();
    }



}
