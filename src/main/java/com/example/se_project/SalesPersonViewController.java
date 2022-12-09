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
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesPersonViewController {
    private List<Map<String, Object>> itemResults;
    private List<String> customers = new ArrayList<>();
    private List<String> items = new ArrayList<>();
    private InputValidator inputValidator = new InputValidator();
    private CustomerOrder customerOrder = new CustomerOrder();

    @FXML
    protected ComboBox customersComboBox;
    @FXML
    protected Button createOrderButton;
    @FXML
    protected CheckBox item1;
    @FXML
    protected ComboBox item1ComboBox;

    @FXML
    protected TextField quantity1TextField;

    @FXML
    protected CheckBox item2;
    @FXML
    protected ComboBox item2ComboBox;

    @FXML
    protected TextField quantity2TextField;

    @FXML
    protected CheckBox item3;
    @FXML
    protected ComboBox item3ComboBox;

    @FXML
    protected TextField quantity3TextField;

    @FXML
    protected CheckBox item4;
    @FXML
    protected ComboBox item4ComboBox;

    @FXML
    protected TextField quantity4TextField;

    @FXML
    protected CheckBox item5;
    @FXML
    protected ComboBox item5ComboBox;

    @FXML
    protected TextField quantity5TextField;

    @FXML
    protected DatePicker needByDatePicker;
    @FXML
    protected DatePicker orderDatePicker;


    @FXML
    public void onCustomerComboBoxValueChanged() throws SQLException {
        customerOrder.setCompany(customersComboBox.getValue().toString());
    }
    @FXML
    public void onCreateOrderButtonClick() throws  SQLException {
        customerOrder.clearCustomerOrderList();
        addItemsToCustomerOrder();
        if (orderDatePicker != null)
        {
            customerOrder.setOrderDate(orderDatePicker.getValue().toString());
        }
        else
            orderDateNotSelectedAlert();
        if (needByDatePicker!=null)
        {
            customerOrder.setNeedBy(needByDatePicker.getValue().toString());
        }
        else
            needByDateNotSelectedAlert();

        addCustomerOrderToDB();


    }
    public void initialize() throws SQLException {
        expiredItemsAlert();
        getCustomersFromDB();
        getItemsFromDB();
        customersComboBox.getItems().addAll(customers);
        item1ComboBox.getItems().addAll(items);
        item2ComboBox.getItems().addAll(items);
        item3ComboBox.getItems().addAll(items);
        item4ComboBox.getItems().addAll(items);
        item5ComboBox.getItems().addAll(items);


    }
//    public void forwardToCreatePage() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("Create-customer-order-view.fxml"));
//        Stage window = (Stage) createOrderButton.getScene().getWindow();
//        window.setScene(new Scene(root));
//    }
    public void getCustomersFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select company_name from customer;");
        addCustomersToList(results);
        connection.closeConnection();
    }
    public void getItemsFromDB() throws SQLException{
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select name from item;");
        addItemsToList(results);
        connection.closeConnection();

    }
    public void addCustomerOrderToDB() throws SQLException {
        if (needByDatePicker.getValue()!=null && orderDatePicker.getValue()!=null)
        {
            if (inputValidator.validateDate(needByDatePicker.getValue()) || inputValidator.validateDate(orderDatePicker.getValue()))
            {
                customerOrder.addCustomerOrderToDB();
                customerSuccessfullyAddedAlert();

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "One of the dates entered is past due. Please select a valid date");
                alert.show();
            }
        }
    }

    public void addItemsToList(List<Map<String,Object>> results){
        for (int i=0; i<results.size();i++)
        {
            items.add(results.get(i).get("name").toString());
        }
    }
    public void addCustomersToList(List<Map<String, Object>> results) {
        for (int i = 0; i < results.size(); i++) {
            customers.add(results.get(i).get("company_name").toString());
        }
    }
    public void addItemsToCustomerOrder() throws SQLException {
        if (item1.isSelected() || item2.isSelected() || item3.isSelected()||item4.isSelected()||item5.isSelected())
        {
            handleItem1Selected();
            handleItem2Selected();
            handleItem3Selected();
            handleItem4Selected();
            handleItem5Selected();
        }
        else
        {
            itemNotSelectedAlert();
        }

    }
    public void handleItem1Selected() throws SQLException {
        if (item1.isSelected()) {
            if (isItemSelected(item1ComboBox, 1)) {
                if (isQuantityValid(quantity1TextField, 1)) {
                    customerOrder.addItemToOrder(saveItemValues(item1ComboBox.getValue().toString()), Double.parseDouble(quantity1TextField.getText().toString()));
                    updateItemQuantityInDB(Double.parseDouble(quantity1TextField.getText()), item1ComboBox.getValue().toString());
                }
            }
        }

    }
    public void handleItem2Selected() throws SQLException {

        if (item2.isSelected()) {
            if (isItemSelected(item2ComboBox, 2)) {
                if (isQuantityValid(quantity2TextField, 2)) {
                    customerOrder.addItemToOrder(saveItemValues(item2ComboBox.getValue().toString()), Double.parseDouble(quantity2TextField.getText().toString()));
                    updateItemQuantityInDB(Double.parseDouble(quantity2TextField.getText()), item2ComboBox.getValue().toString());

                }
            }
        }
    }
    public void handleItem3Selected() throws SQLException
    {
        if (item3.isSelected())
        {
            if (isItemSelected(item3ComboBox, 3))
            {
                if (isQuantityValid(quantity3TextField, 3))
                {
                    customerOrder.addItemToOrder(saveItemValues(item3ComboBox.getValue().toString()), Double.parseDouble(quantity3TextField.getText().toString()));
                    updateItemQuantityInDB(Double.parseDouble(quantity3TextField.getText()), item3ComboBox.getValue().toString());
                }
            }
        }
    }
    public void handleItem4Selected() throws SQLException {
        if (item4.isSelected())
        {
            if (isItemSelected(item4ComboBox, 4))
            {
                if (isQuantityValid(quantity4TextField, 4))
                {
                    customerOrder.addItemToOrder(saveItemValues(item4ComboBox.getValue().toString()), Double.parseDouble(quantity4TextField.getText().toString()));
                    updateItemQuantityInDB(Double.parseDouble(quantity4TextField.getText()), item4ComboBox.getValue().toString());
                }
            }
        }
    }
    public void handleItem5Selected() throws SQLException{
        if (item5.isSelected())
        {
            if (isItemSelected(item5ComboBox, 5))
            {
                if (isQuantityValid(quantity5TextField, 5))
                {
                    customerOrder.addItemToOrder(saveItemValues(item5ComboBox.getValue().toString()), Double.parseDouble(quantity5TextField.getText().toString()));
                    updateItemQuantityInDB(Double.parseDouble(quantity5TextField.getText()), item5ComboBox.getValue().toString());

                }
            }
        }
    }
    public Boolean isItemSelected(ComboBox comboBox, int i )
    {
        if (comboBox.getValue() == null)
        {
            itemNotSelectedAlert(i);
            return false;
        }
        else
            return true;
    }
    public void customerNotSelectedAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Customer not selected. Select customer to proceed with order.", ButtonType.OK);
        alert.show();
    }
    public void itemNotSelectedAlert(int i)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Item " + i + " is not selected. Please select item to proceed", ButtonType.OK);
        alert.show();
    }
    public void itemNotSelectedAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, " no Items  is selected. Please select item checkbox to proceed", ButtonType.OK);
        alert.show();
    }
    public void quantityNotValidAlert(int i)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Quantity for item " + i + " is empty or not valid", ButtonType.OK);
        alert.show();
    }
    public void orderDateNotSelectedAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Order date not selected", ButtonType.OK);
        alert.show();
    }
    public void needByDateNotSelectedAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Need by date not selected", ButtonType.OK);
        alert.show();
    }
    public boolean isQuantityValid(TextField textField, int itemNum)
    {
        if (inputValidator.validateDouble(textField.getText().toString(), "Quantity is Invalid") && textField.getText()!=null)
        {
            return true;
        }
        else
        {
            return false;

        }
    }
    public void findItemInfoInDB(String itemName) throws SQLException
    {
        DBConnection connection = new DBConnection();
        itemResults=  connection.getResults("select ID, name, vendor_id, selling_price, expiration, purchase_price, measurment_unit, quantity_available from item where name = '"+ itemName + "';");
        connection.closeConnection();

    }
    public Item saveItemValues(String itemInput) throws SQLException {
        findItemInfoInDB(itemInput);
        Item item = new Item();
        if (itemResults.size() > 0)
        {
            item.setItemName(itemResults.get(0).get("name").toString());
            item.setItemID(Integer.parseInt(itemResults.get(0).get("ID").toString()));
            item.setVendorID(Integer.parseInt(itemResults.get(0).get("vendor_id").toString()));
            item.setSalePrice(Double.parseDouble(itemResults.get(0).get("selling_price").toString()));
            item.setPurchasePrice(Double.parseDouble(itemResults.get(0).get("purchase_price").toString()));
            item.setMeasurement(Measurements.valueOf(itemResults.get(0).get("measurment_unit").toString()));
            item.setQuantityOnHand(Double.parseDouble(itemResults.get(0).get("quantity_available").toString()));

        }
        return item;
    }
    public void expiredItemsAlert() throws SQLException
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        LocalDate now = LocalDate.now();
        dateTimeFormatter.format(now);
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select * from item where expiration <'"+ now+"';");
        if (results.size() >= 2)
        {

            Alert alert = new Alert(Alert.AlertType.WARNING, "There are more than 2 expired items in stock", ButtonType.OK);
            alert.show();
        }
    }
    public void customerSuccessfullyAddedAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer order was successfully added.\n Subtotal: " + customerOrder.subTotal()+ "\nTotal Cost: " + customerOrder.totalCost());
        alert.show();
    }
    public void updateItemQuantityInDB(Double quantity, String item) throws SQLException
    {
        DBConnection connection = new DBConnection();
        connection.addEntryToDB("update item set quantity_available ="+ quantity+ " where name ='"+item+"';");
        connection.closeConnection();
    }

}
