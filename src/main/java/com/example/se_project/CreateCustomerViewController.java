//@Author: Inas Hamad

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

public class CreateCustomerViewController {
    private String errorString = "";
    private String name;
    private String street;
    private String city;
    private States state;
    private String phone;
    private InputValidator validator = new InputValidator();
    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField streetTextField;
    @FXML
    protected TextField cityTextField;
    @FXML
    protected ComboBox<States> stateComboBox;
    @FXML
    protected TextField phoneTextField;
    @FXML
    protected Button createCustomerButton;
    @FXML
    protected Label error;
    @FXML
    protected Button backToMainButton;

    public void initialize() {
        stateComboBox.getItems().addAll(States.values());
    }

    @FXML
    public void createCustomerButtonOnClick() throws SQLException {
        errorString = "";
        saveValues();
        if (isValid())
        {
            Customer customer = new Customer(name, street, city, state.toString(), phone);
            updateErrorLabel(name + " was added Successfully");
        }
        else
        {
            updateErrorLabel(errorString);
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