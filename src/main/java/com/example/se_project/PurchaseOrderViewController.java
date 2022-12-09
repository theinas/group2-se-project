package com.example.se_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PurchaseOrderViewController extends PurchaseOrder {

    @FXML
    Button searchForVendorButton;
    @FXML
    ComboBox<Item> displayAllItemsComboBox;
    @FXML
    ComboBox<Vendor_Customer> displayAllVendorsComboBox;
    @FXML
    Button createPurchaseOrderButton;
    @FXML
    Button backToMainButton;

}
