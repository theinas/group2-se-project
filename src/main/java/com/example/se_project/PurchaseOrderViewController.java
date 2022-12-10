package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.se_project.LoginController.FINAL_ROLE;

public class PurchaseOrderViewController extends PurchaseOrder {

  PurchaseOrder thisOrder = new PurchaseOrder();
    @FXML
    protected Button searchForVendorButton;
    @FXML
   protected ComboBox<Item> displayVendorsItemsComboBox;
    @FXML
    protected Button createPurchaseOrderButton;
    @FXML
    protected Button backToMainButton;
    @FXML
    protected Button addToOrder;
    @FXML
    protected Label vendorLabel;
    @FXML
    protected Label vendorValueLabel;
    @FXML
    protected Label messageLabel;
    @FXML
    protected TextField vendorSearchTextField;
    @FXML
    protected DatePicker needByDatePicker;
    @FXML
    protected TextField amountTextField;
    @FXML
    protected Label subTotalLabel;
    @FXML
    protected Label TotalLabel;
    @FXML
    protected Button viewAllPurchaseOrdersButton;

    protected List<Map<String, Object>> vendorDBResults = new ArrayList<>();
    protected List<Map<String, Object>> itemDBResults = new ArrayList<>();
    protected InputValidator isValid = new InputValidator();
    protected ArrayList<Item> itemsList= new ArrayList<>();
    public int itemCount = 0;

 @FXML
 public void onSearchForVendorButtonClick() throws SQLException {

   findVendorInDB();
   if(!foundInDB(vendorDBResults))
   {
    notFoundAlert();
    vendorSearchTextField.setText("");
    vendorLabel.setText("Vendor Not Found");
   }
   else
   {
    thisOrder.vendorBoughtFrom = vendorDBResults.get(0).get("name").toString() ;
    vendorLabel.setText("Vendor Found");
    getItemsFromDB();
    vendorValueLabel.setText(thisOrder.vendorBoughtFrom);
   }

 }

 @FXML
 public void onAddToOrderButtonClick()
 {
  itemCount += 1;
  if(isValid() && itemCount<= 5 && isNotExpired(displayVendorsItemsComboBox.getValue())) {
  PurchaseOrderItem thisItem = new PurchaseOrderItem(displayVendorsItemsComboBox.getValue(),
          Double.parseDouble(amountTextField.getText()));
 thisOrder.items.add(thisItem);
 double subTotal = thisItem.getSubTotal();
 subTotalLabel.setText("$" + String.valueOf(subTotal));
 amountTextField.setText("");
 }
 }
 @FXML
 public void onCreatePurchaseOrderButtonClick() throws SQLException {
  addOrderToDB();
  messageLabel.setText("Purchase Order Added Successfully!");
 }

 @FXML
 public void onBackToMainButtonClick() throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("Purchaser-view.fxml"));
   Stage window = (Stage) backToMainButton.getScene().getWindow();
   window.setScene(new Scene(root));
  }
  public void getItemsFromDB() throws SQLException {
  DBConnection connection = new DBConnection();
  itemDBResults = connection.getResults("select " + thisOrder.vendorBoughtFrom + " from item;");
  populateItems();
  connection.closeConnection();

 }
 public void populateItems()
 {
  for (int i = 0; i < itemDBResults.size();i++)
   itemsList.add((Item)itemDBResults.get(i));
  displayVendorsItemsComboBox.getItems().addAll(itemsList);
 }
 public boolean foundInDB(List<Map<String,Object>> DBresults)
 {
  if (DBresults.isEmpty())
   return false;
  else
   return true;
 }
 public void findVendorInDB() throws SQLException
 {
  DBConnection connection = new DBConnection();
  vendorDBResults =  connection.getResults(Query());
  connection.closeConnection();

 }
 public String Query()
 {
  return "select * from vendor where name =" +vendorSearchTextField.getText() +";";
 }
 public void notFoundAlert()
 {
  Alert notFound = new Alert(Alert.AlertType.ERROR, "Vendor not found! try again", ButtonType.CLOSE);
  notFound.show();
 }

 public boolean isValid()
 {
  if(isAmountValid() && isDateValid())
   return true;
  else
   return false;
 }
 public boolean isAmountValid()
 {
  if(isValid.validateNotNegative(Double.parseDouble(amountTextField.getText()))){
   return true;}
   else
    return false;
 }
  public boolean isDateValid()
  {
   if(isValid.validateDate(needByDatePicker.getValue())){
    return true;
   }
   else
    return false;
  }
  public Boolean isNotExpired(Item i)
  {
   if(isValid.validateFutureDate(i.expiration))
    return true;
   else
    return false;
  }

 public void addOrderToDB() throws SQLException {
  DBConnection connection = new DBConnection();
  for (int i = 0; i < itemCount; i++){
   String query = constructFinalQuery(i);
  connection.addEntryToDB(query);}
  connection.closeConnection();
 }

 public String constructFinalQuery(int i) {
   String query = "update purchase_order set vendor_id = '" + vendorDBResults.get(0).get("vendor_id")
           + "', need_by = '" + needByDatePicker.getValue() + "', item_id='" + thisOrder.items.get(i).getItem().getItemID()
           + "', subtotal ='" + thisOrder.subTotal() + "', final_total='" + thisOrder.totalCost() +
           "';";
   return query;
 }


}
