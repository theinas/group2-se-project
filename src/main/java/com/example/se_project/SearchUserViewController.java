//@Author: Robert Tedeschi

package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SearchUserViewController {
    private ToggleGroup toggle = new ToggleGroup();
    private List<Map<String, Object>> results;

    @FXML
    protected RadioButton byIdRadioButton;
    @FXML
    protected RadioButton byNameRadioButton;
    @FXML
    protected TextField searchTextField;
    @FXML
    protected Button searchButton;
    @FXML
    protected Button fullUsersListButton;
    @FXML
    protected Label idLabel;
    @FXML
    protected Label idValueLabel;
    @FXML
    protected Label fnameLabel;
    @FXML
    protected Label fnameValueLabel;
    @FXML
    protected Label lnameLabel;
    @FXML
    protected Label lnameValueLabel;
    @FXML
    protected Label roleLabel;
    @FXML
    protected Label roleValueLabel;
    @FXML
    protected Button backToMainButton;

    public void initialize()
    {
        byIdRadioButton.setToggleGroup(toggle);
        byNameRadioButton.setToggleGroup(toggle);
    }
    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    @FXML
    public void onListAllUsersButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List-all-users-view.fxml"));
        Stage window = (Stage) fullUsersListButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    @FXML
    public void onSearchButtonClick() throws SQLException {
        emptyLabels();

        if(isFilterSelected())
        {
            findUserInDB();
            if(!foundInDB())
            {
                notFoundAlert();
                searchTextField.setText("");

            }
            else
            {
                showUserInfo();
            }
        }
        else
        {
            noFilterAlert();
        }

    }
    public boolean foundInDB()
    {
        if (results.isEmpty())
            return false;
        else
            return true;
    }
    public void findUserInDB() throws SQLException
    {
        DBConnection connection = new DBConnection();
        results =  connection.getResults(getQuery());
        connection.closeConnection();

    }

    public String getQuery()
    {
        if (byIdRadioButton.isSelected())
        {
            return "select * from users where ID =" +Integer.parseInt(searchTextField.getText())+";";
        }
        else
        {
            return "select * from users where last_name ='"+ searchTextField.getText()+ "';";
        }
    }
    public void notFoundAlert()
    {
        Alert notFound = new Alert(Alert.AlertType.ERROR, "User not found! try again", ButtonType.CLOSE);
        notFound.show();

    }
    public void noFilterAlert()
    {
        Alert noFilter = new Alert(Alert.AlertType.WARNING, "No search by option selected. Select then try again", ButtonType.OK);
        noFilter.show();
    }
    public boolean isFilterSelected()
    {
        if (byNameRadioButton.isSelected() || byIdRadioButton.isSelected())
            return true;
        else
            return false;
    }
    public void showUserInfo()
    {
        idLabel.setText("User ID: ");
        idValueLabel.setText(results.get(0).get("ID").toString());

        fnameLabel.setText("First Name: ");
        fnameValueLabel.setText(results.get(0).get("first_name").toString());

        lnameLabel.setText("Last Name: ");
        lnameValueLabel.setText(results.get(0).get("last_name").toString());

        roleLabel.setText("Role: ");
        roleValueLabel.setText(results.get(0).get("role").toString());


    }
    public void emptyLabels()
    {
        idLabel.setText("");
        idValueLabel.setText("");

        fnameLabel.setText("");
        fnameValueLabel.setText("");

        lnameLabel.setText("");
        lnameValueLabel.setText("");

        roleLabel.setText("");
        roleValueLabel.setText("");
    }
}
