package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EditCustomerViewController {
    private List<String> customers = new ArrayList<>();
    private InputValidator validator = new InputValidator();
    private String errorString;
    @FXML
    protected ComboBox customersComboBox;
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
    protected TextField balanceTextField;
    @FXML
    protected TextField lastPaidAmountTextField;
    @FXML
    protected DatePicker lastOrderDatePicker;
    @FXML
    protected Button updateCustomerButton;
    @FXML
    protected Label error;
    @FXML
    protected Button backToMainButton;

    public void initialize() throws SQLException {
        getCustomersFromDB();
        customersComboBox.getItems().addAll(customers);
    }

    @FXML
    public void onUpdateCustomerButtonClick() throws SQLException {
        errorString="";
        if (isValid()) {
            error.setText("Customer updated!");
            updateCustomerInfo();
        } else {
            error.setText(errorString);
        }
    }
    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    public void getCustomersFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select company_name from customer;");
        addCustomersToList(results);
        connection.closeConnection();

    }

    public void addCustomersToList(List<Map<String, Object>> results) {
        for (int i = 0; i < results.size(); i++) {
            customers.add(results.get(i).get("company_name").toString());
        }
    }

    @FXML
    public void onCustomerComboBoxValueChanged() throws SQLException {
        getSelectedCustomerInformation();
    }

    public void getSelectedCustomerInformation() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select * from customer where company_name = '" + customersComboBox.getValue().toString() + "';");
        connection.closeConnection();
        nameTextField.setText(results.get(0).get("company_name").toString());
        streetTextField.setText(results.get(0).get("street_adress").toString());
        cityTextField.setText(results.get(0).get("city").toString());
        stateComboBox.setValue((States.valueOf(results.get(0).get("state").toString())));
        phoneTextField.setText(results.get(0).get("phone").toString().replaceAll("[\\D]", ""));
        balanceTextField.setText(results.get(0).get("balance").toString());
        if (results.get(0).get("last_order_date") != null) {
            lastOrderDatePicker.setValue(formatDate(results.get(0).get("last_order_date").toString()));
        }

        lastPaidAmountTextField.setText(results.get(0).get("last_paid_amount").toString());


    }

    public LocalDate formatDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        formatter = formatter.withLocale(Locale.US);
        return LocalDate.parse(input, formatter);
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

    public boolean isDateValid(LocalDate date) {
        if (lastOrderDatePicker.getValue()!=null)
        {
            if (!validator.validateDate(date)) {
                errorString += "\nDate is not valid";
                return false;
            } else
                return true;
        }
        else
        {
            return true;
        }

    }

    public boolean isValid() {
        if (isNameValid(nameTextField.getText()) && isStreetValid(streetTextField.getText())
                && isCityValid(cityTextField.getText()) && isPhoneValid(phoneTextField.getText())
                && isDateValid(lastOrderDatePicker.getValue())) {
            return true;
        } else {
            return false;
        }
    }

    public void updateCustomerInfo() throws SQLException {
        DBConnection connection = new DBConnection();
        String query = buildUpdateQuery();
        connection.addEntryToDB(query);
        connection.closeConnection();
    }

    public String buildUpdateQuery() {
        if (lastOrderDatePicker.getValue() !=null)
        {
            String query = "update customer set company_name = '" + nameTextField.getText() + "', street_adress = '" + streetTextField.getText() +
                    "', city='" + cityTextField.getText() + "', state='" + stateComboBox.getValue().toString() + "', phone='" + phoneTextField.getText() + "', balance=" + Double.parseDouble(balanceTextField.getText()) +
                    ", last_paid_amount=" + Double.parseDouble(lastPaidAmountTextField.getText()) + ", last_order_date='" + lastOrderDatePicker.getValue() + "' where company_name= '" + customersComboBox.getValue().toString() + "';";
            return query;
        }
        else
        {
            String query = "update customer set company_name = '" + nameTextField.getText() + "', street_adress = '" + streetTextField.getText() +
                    "', city='" + cityTextField.getText() + "', state='" + stateComboBox.getValue().toString() + "', phone='" + phoneTextField.getText() + "', balance=" + Double.parseDouble(balanceTextField.getText()) +
                    ", last_paid_amount=" + Double.parseDouble(lastPaidAmountTextField.getText()) + " where company_name= '" + customersComboBox.getValue().toString() + "';";
            return query;
        }

    }
}
