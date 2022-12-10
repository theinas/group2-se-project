package com.example.se_project;
/*
Developer: Tanni Dev

this class is used to create vendor profiles delete vendor profiles edit vendor profiles.
 and to view them in a table form. also this class is used to search for the vendor profile
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class Vendor_CustomerController implements Initializable {


//    DB_Connection con;
//    Connection connection;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtStaddress;

    @FXML
    private TextField txtCity;

    @FXML
    private ComboBox<String> txtState;

    @FXML
    private TextField txtPhn;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtLastPaid;

    @FXML
    private DatePicker txtLastOrder;

    @FXML
    private DatePicker txtSessional;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnBack;

    @FXML
    private TableView<Vendor_Customer> tbl;

    @FXML
    private TableColumn<Vendor_Customer, String> cfname;

    @FXML
    private TableColumn<Vendor_Customer, String> cphn;

    @FXML
    private TableColumn<Vendor_Customer, String> cbalance;

    @FXML
    private TableColumn<Vendor_Customer, String> clastpaid;

    @FXML
    private TableColumn<Vendor_Customer, String> clastorder;

    @FXML
    private TextField txrSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnLogout;

    int id = 0;
    double balance = 0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        con = new DB_Connection();
//        connection = con.getConnection();

        setDefault();

        txtState.getItems().addAll( "AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY" );
        txtBalance.setText("0");
        txtLastPaid.setText("0");
        btnCreate.setVisible(true);
        btnUpdate.setVisible(false);


        btnSearch.setOnAction(event -> {
            try {

                String query_getAll = ("Select * from project.vendor where company_name = '" + txrSearch.getText() + "' OR ID = '" + txrSearch.getText() + "'");
                DBConnection connection = new DBConnection();
                List<Map<String, Object>> rs =  connection.getResults(query_getAll);
//            connection.closeConnection();

                if(rs.size()>0){

                    for (int i = 0; i < rs.size(); i++) {
//                n.add(rs.get(i).get(1).toString());
//                        n.add(new Vendor_Customer(rs.get(i).get("ID").toString(),
//                        rs.get(i).get("company_name").toString(),
//                        rs.get(i).get("street_address").toString(),
//                        rs.get(i).get("city").toString(), rs.get(i).get("state").toString(),
//                        rs.get(i).get("phone").toString(), rs.get(i).get("balance").toString(),
//                        rs.get(i).get("last_paid_amount").toString(), rs.get(i).get("last_order_date").toString(),
//                        rs.get(i).get("seasonal_discount_start").toString()));

                        txtid.setText(rs.get(i).get("ID").toString());
                        txtFullName.setText(rs.get(i).get("company_name").toString());
                        txtStaddress.setText(rs.get(i).get("street_address").toString());
                        txtCity.setText(rs.get(i).get("city").toString());
                        txtState.setValue(rs.get(i).get("state").toString());
                        txtPhn.setText(rs.get(i).get("phone").toString());
                        txtBalance.setText(rs.get(i).get("balance").toString());
                        txtLastPaid.setText(rs.get(i).get("last_paid_amount").toString());
                        txtLastOrder.setValue(LocalDate.parse(rs.get(i).get("last_order_date").toString()));
                        txtSessional.setValue(LocalDate.parse(rs.get(i).get("seasonal_discount_start").toString()));

                        txtFullName.setEditable(false);

                    }

                }else{
                    AlertController a = new AlertController(Alert.AlertType.WARNING, "Not Found", "Profile not found of this user");
                }


//                Statement s = connection.createStatement();
//                ResultSet rs = s.executeQuery("Select * from project.vendor where company_name = '" + txrSearch.getText() + "' OR ID = '" + txrSearch.getText() + "'");
//                if (rs.next()) {
//                    id = rs.getInt("id");
//                    txtid.setText(rs.getInt("id") + "");
//                    txtFullName.setText(rs.getString(2));
//                    txtStaddress.setText(rs.getString(3));
//                    txtCity.setText(rs.getString(4));
//                    txtState.setValue(rs.getString(5));
//                    txtPhn.setText(rs.getString(6));
//                    balance = Double.valueOf(rs.getString(7));
//                    txtBalance.setText(rs.getString(7));
//                    txtLastPaid.setText(rs.getString(8));
//                    txtLastOrder.setValue(LocalDate.parse(rs.getString(9)));
//                    txtSessional.setValue(LocalDate.parse(rs.getString(10)));
//
//                    txtFullName.setEditable(false);
//                } else {
//
//                    AlertController a = new AlertController(Alert.AlertType.WARNING, "Not Found", "Profile not found of this user");
//                }

                if (txtid.getText().length() != 0) {
                    btnCreate.setVisible(false);
                    btnUpdate.setVisible(true);
                } else {

                }
                //connection.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        });

        btnCreate.setOnAction(event -> {

            int duplicate = 0;
            String name = txtFullName.getText();
            String address = txtStaddress.getText();
            String city = txtCity.getText();
            String state = txtState.getValue();
            String phone = txtPhn.getText();
            String balance = txtBalance.getText();
            String lastPaid = txtLastPaid.getText();
            String lastdate = "";
            String sessional = "";
            try {
                lastdate = txtLastOrder.getValue().toString();
                sessional = txtSessional.getValue().toString();
            } catch (Exception e) {

            }
            try {
                String query_getAll = "Select company_name from project.vendor";
                DBConnection connection = new DBConnection();
                List<Map<String, Object>> rs =  connection.getResults(query_getAll);
//            connection.closeConnection();
                for (int i = 0; i < rs.size(); i++) {

                    if (rs.get(i).get("company_name").toString().equals(name)) {
                        duplicate = 1;
                        AlertController a = new AlertController(Alert.AlertType.ERROR, "Duplicate User id", "This User id is already exists");
                    }
                }



//                String query = "Select company_name from project.vendor";
//                Statement s = connection.createStatement();
//                ResultSet rs = s.executeQuery(query);
//
//                while (rs.next()) {
//                    if (rs.getString(1).equals(name)) {
//                        duplicate = 1;
//                        AlertController a = new AlertController(Alert.AlertType.ERROR, "Duplicate User id", "This User id is already exists");
//                    }
//                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            int check = 0;
            if (ErrorController.nameChecker(name, 20, "Company Full Name") && ErrorController.strChecker(address, 20, "Street Address") && ErrorController.nameChecker(city, 20, "City") && state != null && ErrorController.phnChecker(phone) && ErrorController.phnChecker(phone) && ErrorController.numChecker(balance, "Balance") && ErrorController.numChecker(lastPaid, "Last Paid") && ErrorController.pastChecker(sessional)) {
                check = 1;
            }

            if (duplicate == 0 && check == 1) {
                try {   // if the name is alphabet and status is not null then category is added to database
//                    Statement s = connection.createStatement();
////                    String q = "INSERT INTO distributer.vendor(fname, staddress, city, state, phone, balance, lastpaidamount, lastorderdate, sessionaldiscountstartdate) VALUES ('" + name + "','" + address + "','" + city + "','" + state + "','" + phone + "','" + balance + "','" + lastPaid + "','" + lastdate + "','" + sessional + "'  )";
//                    String query = "INSERT INTO project.vendor(company_name, street_address, city, state, phone, balance, last_paid_amount, last_order_date, seasonal_discount_start) VALUES ('" + name + "','" + address + "','" + city + "','" + state + "','" + phone + "','" + balance + "','" + lastPaid + "','" + lastdate + "','" + sessional + "'  )";
//                    s.execute(query);
                    // setting default for table view.. because we added a new item so it need to be shown
                    // setDefault();

                    String query = "INSERT INTO project.vendor(company_name, street_address, city, state, phone, balance, last_paid_amount, last_order_date, seasonal_discount_start) VALUES ('" + name + "','" + address + "','" + city + "','" + state + "','" + phone + "','" + balance + "','" + lastPaid + "','" + lastdate + "','" + sessional + "'  )";
                    DBConnection connection = new DBConnection();
                    connection.addEntryToDB(query);
                    connection.closeConnection();



                    AlertController a = new AlertController(Alert.AlertType.INFORMATION, null, "Successfully Added");
                    setDefault();
                    clear();

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }

        });
        btnUpdate.setOnAction(event -> {
            String name = txtFullName.getText();
            String address = txtStaddress.getText();
            String city = txtCity.getText();
            String state = txtState.getValue();
            String phone = txtPhn.getText();
            String balance = txtBalance.getText();
            String lastPaid = txtLastPaid.getText();
            String lastdate = "";
            String sessional = "";
            try {
                lastdate = txtLastOrder.getValue().toString();
                sessional = txtSessional.getValue().toString();
            } catch (Exception e) {

            }
            int check = 0;
            if (ErrorController.strChecker(address, 20, "Street Address") && ErrorController.nameChecker(city, 20, "City") && state != null && ErrorController.phnChecker(phone) && ErrorController.phnChecker(phone) && ErrorController.numChecker(balance, "Balance") && ErrorController.numChecker(lastPaid, "Last Paid")) {
                check = 1;
            }
            if (check == 1) {
                try {   // if the name is alphabet and status is not null then category is added to database
//                    Statement s = connection.createStatement();
//                    String q = "UPDATE project.vendor SET street_address = '" + address + "', city ='" + city + "',state ='" + state + "', phone ='" + phone + "',balance= '" + balance + "', last_paid_amount = '" + lastPaid + "', last_order_date ='" + lastdate + "', seasonal_discount_start ='" + sessional + "' where ID = '" + id + "'";
//                    s.execute(q);

                    String query_update = "UPDATE project.vendor SET street_address = '" + address + "', city ='" + city + "',state ='" + state + "', phone ='" + phone + "',balance= '" + balance + "', last_paid_amount = '" + lastPaid + "', last_order_date ='" + lastdate + "', seasonal_discount_start ='" + sessional + "' where ID = '" + txrSearch.getText() + "'";
                    DBConnection connection = new DBConnection();
                    connection.addEntryToDB(query_update);
                    connection.closeConnection();
                    setDefault();

//                    String query = "UPDATE project.vendor SET staddress = '" + address + "', city ='" + city + "',state ='" + state + "', phone ='" + phone + "',balance= '" + balance + "', lastpaidamount = '" + lastPaid + "', lastorderdate ='" + lastdate + "', sessionaldiscountstartdate ='" + sessional + "' where id = '" + id + "'";
//                    DBConnection connection = new DBConnection();
//                    connection.addEntryToDB(query);
//
//                    connection.closeConnection();


                    AlertController a = new AlertController(Alert.AlertType.INFORMATION, null, "Successfully Updated");
                    setDefault();
                    clear();
                    btnUpdate.setVisible(false);
                    btnCreate.setVisible(true);
                    txtFullName.setEditable(true);

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }

        });

        btnDelete.setOnAction(event -> {
            if (balance == 0) {

                String query1 = "delete from project.vendor where ID = '" + txrSearch.getText() + "'";
                try {
                    if (txtFullName.getLength() >= 1) {

//                        Statement s = connection.createStatement();
//                        s.execute(query1);
                        deleteItemfromItemTable(Integer.parseInt(txrSearch.getText()));
                        DBConnection connection = new DBConnection();
                        connection.addEntryToDB(query1);
                        connection.closeConnection();

                        setDefault();

                        ///trunForeignkeyOnOFF(1);


                        AlertController a = new AlertController(Alert.AlertType.INFORMATION, null, "Successfully Deleted");
                        clear();
                    } else {
                        AlertController a = new AlertController(Alert.AlertType.WARNING, null, "Search a user then press delete Button");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            } else {
                AlertController a = new AlertController(Alert.AlertType.WARNING, null, "the balance is not zero");
            }
        });

        btnClear.setOnAction(event -> {
            clear();
        });

        btnBack.setOnAction(event -> {
            try {
//                Stage stage = new Stage();  // go to dashboard if information is correct
//                FXMLLoader loader = new FXMLLoader();
////                Pane root = loader.load(getClass().getResource("Dashboard.fxml").openStream());
//                Pane root = loader.load(getClass().getResource("Login-view.fxml").openStream());
//                DashboardController Msg = (DashboardController) loader.getController();
//                Scene scene = new Scene(root);
//                scene.getStylesheets().setAll(
//                        getClass().getResource("style.css").toExternalForm()
//                );
//                stage.setScene(scene);
//                stage.show();
//                ((Node) event.getSource()).getScene().getWindow().hide();

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Purchaser-view.fxml")));
                Stage window = (Stage)btnBack.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

    }

    public void deleteItemfromItemTable(int val) throws SQLException {

        String query1 = "delete from project.item where vendor_id = " + val + "";
            DBConnection connection = new DBConnection();
            connection.addEntryToDB(query1);
            connection.closeConnection();
    }

    public void clear() {
        txtid.clear();
        txtFullName.clear();
        txtStaddress.clear();
        txtCity.clear();
        txtState.setValue(null);
        txtPhn.clear();
        txtBalance.clear();
        txtLastPaid.clear();
        txtLastOrder.setValue(null);
        txtSessional.setValue(null);
        btnCreate.setVisible(true);
        btnUpdate.setVisible(false);
        txtFullName.setEditable(true);
    }

    public void setDefault() {

        ObservableList<Vendor_Customer> n = FXCollections.observableArrayList();
        try {
//            String query;
//
//            query = "Select * from project.vendor";

            String query_getAll = "Select * from project.vendor";
            DBConnection connection = new DBConnection();
            List<Map<String, Object>> rs =  connection.getResults(query_getAll);
//            connection.closeConnection();
            for (int i = 0; i < rs.size(); i++) {
//                n.add(rs.get(i).get(1).toString());
                n.add(new Vendor_Customer(rs.get(i).get("ID").toString(), rs.get(i).get("company_name").toString(), rs.get(i).get("street_address").toString(), rs.get(i).get("city").toString(), rs.get(i).get("state").toString(), rs.get(i).get("phone").toString(), rs.get(i).get("balance").toString(), rs.get(i).get("last_paid_amount").toString(), rs.get(i).get("last_order_date").toString(), rs.get(i).get("seasonal_discount_start").toString()));
            }

//
//            //query = "Select * from distributer.vendor where fname Like '%" + txrSearch.getText() + "%'";
//
//            Statement s = connection.createStatement();
//            ResultSet rs = s.executeQuery(query);

//            DBConnection connection = new DBConnection();

//            Statement s = connection.createStatement();
//            ResultSet rs = s.executeQuery(query);

//            connection.addEntryToDB(query);
//            connection.closeConnection();




//            while (rs.next()) { // setting the values from table view same way as approval brand category
//                n.add(new Vendor_Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
//                //wholeData.add(rs.getString(1)+","+ rs.getString(2)+","+ rs.getString(3)+","+ rs.getString(4)+","+ rs.getString(5)+","+ rs.getString(6)+","+ rs.getString(7)+","+ rs.getString(8)+","+ rs.getString(9)+","+ rs.getString(10));
//            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cfname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cphn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cbalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        clastpaid.setCellValueFactory(new PropertyValueFactory<>("lastpaidamount"));
        clastorder.setCellValueFactory(new PropertyValueFactory<>("lastorderdate"));
        tbl.setItems(n);

    }
}

