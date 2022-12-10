//@author Robert Tedeschi

package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateUserProfileController {
    private String errorString = "";
    private List<String> users = new ArrayList<>();

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
    protected ComboBox usersComboBox;
    @FXML
    protected Label error;
    @FXML
    protected Button logoutButton;

    public void initialize() throws SQLException {
        getUsersFromDB();
        usersComboBox.getItems().addAll(users);

    }

    public void getUsersFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select last_name from users;");
        addUsersToList(results);
        connection.closeConnection();

    }

    public void addUsersToList(List<Map<String, Object>> results) {
        for (int i = 0; i < results.size(); i++) {
            users.add(results.get(i).get("last_name").toString());
        }
    }
    @FXML
    public void UpdateUserButtonOnClick() throws SQLException {
        errorString="";
        if (isValid()) {
            error.setText("User updated!");
            updateUserInfo();
        } else {
            error.setText(errorString);
        }
    }

    public void updateUserInfo() throws SQLException {
        DBConnection connection = new DBConnection();
        String query = buildUpdateQuery();
        connection.addEntryToDB(query);
        connection.closeConnection();
    }

    public String buildUpdateQuery() {
        String query = "update users set last_name = '" +lNameTextField.getText()+ "', first_name = '" +
                fNameTextField.getText()+ "', password = '" +passTextField.getText()+ "' where last_name = '" +
                lNameTextField.getText()+ "';";
        return query;
    }

    @FXML
    public void onUserComboBoxValueChanged() throws SQLException {
        getSelectedUserInformation();
    }

    public void getSelectedUserInformation() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select * from users where last_name = '" +
                usersComboBox.getValue().toString() + "';");
        connection.closeConnection();
        fNameTextField.setText(results.get(0).get("first_name").toString());
        lNameTextField.setText(results.get(0).get("last_name").toString());
        passTextField.setText(results.get(0).get("password").toString());

    }





    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public boolean isfNameValid(String name) {
        if (validator.validateIsAlphaAndSpaces(name) && validator.validateLength(name, 15)) {
            return true;
        } else {
            errorString += "\nFirst name is not valid";
            return false;
        }
    }

    public boolean islNameValid(String name) {
        if (validator.validateIsAlphaAndSpaces(name) && validator.validateLength(name, 15)) {
            return true;
        } else {
            errorString += "\nLast name is not valid";
            return false;
        }
    }

    public boolean ispassValid(String pass) {
        if (validator.validateLength(pass, 16) && validator.validateMinLength(pass, 8)) {
            return true;
        } else {
            errorString += "\nPassword is not valid";
            return false;
        }
    }

    public boolean isValid() {
        if (islNameValid(lNameTextField.getText()) && isfNameValid(fNameTextField.getText())
                && ispassValid(passTextField.getText())) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void onlogoutButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage window = (Stage)logoutButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
