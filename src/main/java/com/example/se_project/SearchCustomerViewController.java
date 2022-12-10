//@Author: Inas Hamad

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

public class SearchCustomerViewController {
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
    protected Button fullCustomerListButton;
    @FXML
    protected Label idLabel;
    @FXML
    protected Label idValueLabel;
    @FXML
    protected Label companyLabel;
    @FXML
    protected Label companyValueLabel;
    @FXML
    protected Label streetLabel;
    @FXML
    protected Label streetValueLabel;
    @FXML
    protected Label cityLabel;
    @FXML
    protected Label cityValueLabel;
    @FXML
    protected Label stateLabel;
    @FXML
    protected Label stateValueLabel;
    @FXML
    protected Label balanceLabel;
    @FXML
    protected Label balanceValueLabel;
    @FXML
    protected Label lastPaidLabel;
    @FXML
    protected Label lastPaidValueLabel;
    @FXML
    protected Label lastOrderLabel;
    @FXML
    protected Label lastOrderValueLabel;
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
    public void onListAllCustomersButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List-all-users-view.fxml"));
        Stage window = (Stage) fullCustomerListButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    @FXML
    public void onSearchButtonClick() throws SQLException {
        emptyLabels();

        if(isFilterSelected())
        {
            findCustomerInDB();
            if(!foundInDB())
            {
                notFoundAlert();
                searchTextField.setText("");

            }
            else
            {
                showCustomerInfo();
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
    public void findCustomerInDB() throws SQLException
    {
        DBConnection connection = new DBConnection();
        results =  connection.getResults(getQuery());
        connection.closeConnection();

    }
    public void displayResults() throws SQLException {

    }
    public String getQuery()
    {
        if (byIdRadioButton.isSelected())
        {
            return "select * from customer where ID =" +Integer.parseInt(searchTextField.getText())+";";
        }
        else
        {
            return "select * from customer where company_name ='"+ searchTextField.getText()+ "';";
        }
    }
    public void notFoundAlert()
    {
        Alert notFound = new Alert(Alert.AlertType.ERROR, "Customer not found! try again", ButtonType.CLOSE);
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
    public void showCustomerInfo()
    {
        idLabel.setText("Customer ID: ");
        idValueLabel.setText(results.get(0).get("ID").toString());

        companyLabel.setText("Company Name: ");
        companyValueLabel.setText(results.get(0).get("company_name").toString());

        streetLabel.setText("Street address: ");
        streetValueLabel.setText(results.get(0).get("street_adress").toString());

        cityLabel.setText("City: ");
        cityValueLabel.setText(results.get(0).get("city").toString());

        stateLabel.setText("State: ");
        stateValueLabel.setText(results.get(0).get("state").toString());

        balanceLabel.setText("Balance: ");
        balanceValueLabel.setText("$" + results.get(0).get("balance").toString());

        lastPaidLabel.setText("Last Paid Amount: ");
        lastPaidValueLabel.setText("$"+ results.get(0).get("last_paid_amount").toString());

        if (results.get(0).get("last_order_date") != null)
        {
            lastOrderLabel.setText("Last order date: ");
            lastOrderValueLabel.setText(results.get(0).get("last_order_date").toString());
        }
    }
    public void emptyLabels()
    {
        idLabel.setText("");
        idValueLabel.setText("");

        companyLabel.setText("");
        companyValueLabel.setText("");

        streetLabel.setText("");
        streetValueLabel.setText("");

        cityLabel.setText("");
        cityValueLabel.setText("");

        stateLabel.setText("");
        stateValueLabel.setText("");

        balanceLabel.setText("");
        balanceValueLabel.setText("");

        lastPaidLabel.setText("");
        lastPaidValueLabel.setText("");

        lastOrderLabel.setText("");
        lastOrderValueLabel.setText("");
    }
}
