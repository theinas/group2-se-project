package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountViewController {

    @FXML
    Button createInvoiceBtn;

    public void createCustomerInvoiceBtnClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerInvoice.fxml"));
        Stage window = (Stage)createInvoiceBtn.getScene().getWindow();
        window.setScene(new Scene(root));

    }

}
