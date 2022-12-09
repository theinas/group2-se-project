package com.example.se_project;


import javafx.event.ActionEvent;
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

//@author Robert Tedeschi

public class CreateUserProfileController{
    private String errorString = "";
    private String lName;
    private String fName;
    private String password;
    private UserRoles role;

    private InputValidator validator = new InputValidator();


    @FXML
    protected TextField fNameTextField;
    @FXML
    protected TextField lNameTextField;
    @FXML
    protected TextField passTextField;
    @FXML
    protected Button backToMainButton;
    @FXML
    protected ComboBox<UserRoles> roleComboBox;
    @FXML
    protected Label error;


    public void initialize() {
        roleComboBox.getItems().addAll(UserRoles.values());
    }

    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void saveValues()
    {
        fName = fNameTextField.getText();
        lName = lNameTextField.getText();
        password = passTextField.getText();
        role = roleComboBox.getValue();
    }





    public void createUserButtonOnClick()throws SQLException {
        errorString = "";

        saveValues();
        if (isValid())
        {
            UserFactory.createUser(role, lName, fName, password);
            updateErrorLabel(fName + lName + " was added Successfully");
        }
        else
        {
            updateErrorLabel(errorString);
        }
    }

    public boolean isfNameValid(String name) {
        if (validator.validateIsAlphaAndSpaces(fName) && validator.validateLength(fName, 15)) {
            return true;
        } else {
            errorString += "\nFirst name is not valid";
            return false;
        }
    }
    public boolean islNameValid(String name) {
        if (validator.validateIsAlphaAndSpaces(lName) && validator.validateLength(lName, 15)) {
            return true;
        } else {
            errorString += "\nLast name is not valid";
            return false;
        }
    }
    public boolean ispassValid(String name) {
        if (validator.validateLength(fName, 16) && validator.validateMinLength(password, 8)) {
            return true;
        } else {
            errorString += "\nPassword is not valid";
            return false;
        }
    }

    public boolean isValid() {
        if (islNameValid(lName) && isfNameValid(fName) && ispassValid(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void updateErrorLabel(String r) {
       error.setText(r);
   }


}
