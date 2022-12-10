//@Author: Robert Tedeschi

package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminViewController {
    @FXML
    protected Button deleteUserButton;
    @FXML
    protected Button createUserButton;
    @FXML
    protected Button updateUserButton;
    @FXML
    protected Button logoutButton;
    @FXML
    Button searchUserButton;

    @FXML
    public void onCreateUserProfileClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Create-nonauser-profile.fxml"));
        Stage window = (Stage)createUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onUpdateUserProfileClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Update-nonauser-profile.fxml"));
        Stage window = (Stage)createUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void onDeleteUserButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Delete-nonauser-profile.fxml"));
        Stage window = (Stage)createUserButton.getScene().getWindow();
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
