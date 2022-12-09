package com.example.se_project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class InventoryManagerViewController {
    @FXML
    Button AddItemButton;
    @FXML
    Button EditItemButton;
    @FXML
    Button DeleteItemButton;
    @FXML
    Button SearchForItemButton;
    @FXML
    Button DisplayAllItemsButton;

    @FXML
    public void onAddItemButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Add-item-view.fxml"));
        Stage window = (Stage)AddItemButton.getScene().getWindow();
        window.setScene(new Scene(root));

    }
    @FXML
    public void onEditItemButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Edit-customer-view.fxml"));
        Stage window = (Stage)EditItemButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onDeleteItemButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Delete-item-view.fxml"));
        Stage window = (Stage)DeleteItemButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onSearchForItemButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Search-customer-view.fxml"));
        Stage window = (Stage)SearchForItemButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onDisplayAllItemsButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Display-all-items-view.fxml"));
        Stage window = (Stage)DisplayAllItemsButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}

