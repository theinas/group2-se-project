//@Author: Robert Tedeschi

package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteUserProfileController {
    private List<String> users = new ArrayList<>();

    @FXML
    protected Button deleteUserButton;
    @FXML
    protected Button backToMainButton;
    @FXML
    protected Button logoutButton;
    @FXML
    protected ComboBox usersComboBox;
    @FXML
    protected Label error;



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

    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onDeleteUserButtonClick() throws SQLException {
        if(usersComboBox.getValue()!= null)
        {
            deleteCustomerFromDB();
            error.setText(usersComboBox.getValue().toString() + " was deleted");

        }
        else
        {
            error.setText("Select a user to delete");
        }
    }

    public String queryBuilder()
    {
        return "delete from users where last_name='"+usersComboBox.getValue().toString()+"';";
    }
    public void deleteCustomerFromDB() throws SQLException
    {
        DBConnection connection = new DBConnection();
        connection.addEntryToDB(queryBuilder());
        connection.closeConnection();
    }

    @FXML
    public void onlogoutButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage window = (Stage)logoutButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
