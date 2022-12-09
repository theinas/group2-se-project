package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddItemViewController {
    //declare members to mimic item class
    private String errorMessage = "";
    private int itemID;
    private String name;
    private int vendorID;
    private double salePrice;
    private Categories category;
    private Date expiration;
    private double purchasePrice;
    private Measurements unit;
    private double quantityOnHand;
    private InputValidator isValid = new InputValidator();
    //create text fields for all necessary inputs
    @FXML
    protected TextField itemIDTextField;
    @FXML
    protected TextField nameTextField;
    @FXML
    protected TextField vendorIDTextField;
    @FXML
    protected TextField salePriceTextField;
    @FXML
    protected ComboBox<Categories> categoryComboBox;
    @FXML
    protected TextField expirationTextField;
    @FXML
    protected TextField purchasePriceTextField;
    @FXML
    protected ComboBox<Measurements> unitComboBox;
    @FXML
    protected TextField quantityOnHandTextField;
    //create buttons to create item and return to home screen
    @FXML
    protected Button createItemButton;
    @FXML
    protected Label displayError;
    @FXML
    protected Button backToMainButton;

    //fill combo boxes with enum values
    public void initialize() {
        categoryComboBox.getItems().addAll(Categories.values());
        unitComboBox.getItems().addAll(Measurements.values());
    }

    @FXML
    public void createItemButtonOnClick() throws SQLException {
        errorMessage = "";
        saveValues();
        if (isValid())
        {
            Item newItem = new Item(itemID, name, vendorID, salePrice, category, expiration, purchasePrice,
                    unit, quantityOnHand);
            updateErrorLabel(name + " was added Successfully");
        }
        else
        {
            updateErrorLabel(errorMessage);
        }
    }

    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public boolean isNameValid(String name) {
        if (validator.validateIsAlphaAndSpaces(name) && validator.validateLength(name, 20)) {
            return true;
        } else {
            errorString += "\nCompany name is not valid";
            return false;
        }
    }

    public boolean isCityValid(String name) {
        if (validator.validateIsAlphaAndSpaces(name) && validator.validateLength(name, 20)) {
            return true;
        } else {
            errorString += "\nCity name is not valid";
            return false;
        }
    }

    public boolean isStreetValid(String street) {
        if (validator.validateLength(street, 20))
            return true;
        else {
            errorString += "\n name is not valid";
            return false;
        }

    }

    public boolean isPhoneValid(String number) {
        if (validator.validatePhoneNumber(number)) {
            return true;
        } else {
            errorString += "\nPhone number is not valid";
            return false;
        }
    }

    public boolean isValid() {
        if (isNameValid(name) && isStreetValid(street) && isCityValid(city) && isPhoneValid(phone)) {
            return true;
        } else {
            return false;
        }
    }


    public void updateErrorLabel(String r) {
        error.setText(r);
    }
    public void saveValues()
    {
        name = nameTextField.getText();
        street = streetTextField.getText();
        city = cityTextField.getText();
        state = stateComboBox.getValue();
        phone = phoneTextField.getText().toString();
    }
}