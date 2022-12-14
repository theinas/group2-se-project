package com.example.se_project;
//Joshua
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import static com.example.se_project.LoginController.FINAL_ROLE;


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
    Button backToMainButton;

    @FXML
    public void onBackToMainButtonClick() throws IOException {
        if (FINAL_ROLE.equals("OWNER")) {
            Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }}

    @FXML
    public void onAddItemButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Add-item-view.fxml"));
        Stage window = (Stage)AddItemButton.getScene().getWindow();
        window.setScene(new Scene(root));

    }
    @FXML
    public void onEditItemButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Edit-item-view.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("Search-for-item-view.fxml"));
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

