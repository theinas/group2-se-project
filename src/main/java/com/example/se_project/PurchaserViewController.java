package com.example.se_project;

/*
Developer: Tanni Dev

*/

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class PurchaserViewController {
    @FXML
    Button createVendorButton;
    @FXML
    Button editVendorButton;
    @FXML
    Button deleteVendorButton;

    @FXML
    public void createVendorClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Vendor_Customer.fxml"));
        Stage window = (Stage)createVendorButton.getScene().getWindow();
        window.setScene(new Scene(root));

    }
    @FXML
    public void updateVendorClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Vendor_Customer.fxml"));
        Stage window = (Stage)editVendorButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void deleteVendorClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Vendor_Customer.fxml"));
        Stage window = (Stage)deleteVendorButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}

