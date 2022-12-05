package com.example.se_project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class OwnerViewController {
    @FXML
    Button createCustomerButton;
    @FXML
    Button editCustomerButton;
    @FXML
    Button deleteCustomerButton;
    @FXML
    Button searchCustomerButton;
    @FXML
    public void onCreateCustomerButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Create-customer-view.fxml"));
        Stage window = (Stage)createCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));

    }
    @FXML
    public void onEditCustomerButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Edit-customer-view.fxml"));
        Stage window = (Stage)editCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onDeleteCustomerButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Delete-customer-view.fxml"));
        Stage window = (Stage)deleteCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onSearchCustomerButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Search-customer-view.fxml"));
        Stage window = (Stage)searchCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}

