//@Author: Inas Hamad

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
    Button editCustomerButton;
    @FXML
    Button deleteCustomerButton;
    @FXML
    Button searchCustomerButton;
    @FXML
    Button createCustomerButton;
    @FXML
    Button createUserButton;
    @FXML
    Button updateUserButton;
    @FXML
    Button deleteUserButton;
    @FXML
    Button logoutButton;
    @FXML
    Button searchUserButton;

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

    @FXML
    public void onCreateUserProfileClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Create-user-profile.fxml"));
        Stage window = (Stage)createUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onUpdateUserProfileClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Update-user-profile.fxml"));
        Stage window = (Stage)updateUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onDeleteUserButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Delete-user-profile.fxml"));
        Stage window = (Stage)deleteUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }



    @FXML
    public void onlogoutButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage window = (Stage)logoutButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onsearchUserButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Search-user-view.fxml"));
        Stage window = (Stage)searchUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }


}

