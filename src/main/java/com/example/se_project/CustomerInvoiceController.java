package com.example.se_project;

/*
Developer: Tanni Dev
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/*

this class is used to control customer invoice
this class get data from database and create its invoice by using panes and labels

 */
public class CustomerInvoiceController implements Initializable {

    private List<String> customers = new ArrayList<>();
    @FXML
    private ListView<String> listInvoices;

    @FXML
    private ComboBox<String> comboInvoices;

    @FXML
    private ComboBox<String> comboSelect;

    @FXML
    private Button btnCreate;

    @FXML
    private Label invoice_date;

    @FXML
    private Label order_date;

    @FXML
    private Label invoice_id;

    @FXML
    private Label customer_order_no;

    @FXML
    private Label item1;

    @FXML
    private Label item2;

    @FXML
    private Label item3;

    @FXML
    private Label item4;

    @FXML
    private Label item5;

    @FXML
    private Label need1;

    @FXML
    private Label need2;

    @FXML
    private Label need3;

    @FXML
    private Label need4;

    @FXML
    private Label need5;

    @FXML
    private Label q1;

    @FXML
    private Label q2;

    @FXML
    private Label q3;

    @FXML
    private Label q4;

    @FXML
    private Label q5;

    @FXML
    private Label c1;

    @FXML
    private Label c2;

    @FXML
    private Label c3;

    @FXML
    private Label c4;

    @FXML
    private Label c5;

    @FXML
    private Label lbltotal;

    @FXML
    private Pane paneRecipt;


    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    DB_Connection con;
    Connection connection;


    ArrayList<String> id = new ArrayList<>();
    int idfromdb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        con = new DB_Connection();
//        connection = con.getConnection();

        paneRecipt.setVisible(false);

        try {
            getCustomersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboSelect.getItems().addAll(customers);
        comboInvoices.getItems().addAll(customers);


        comboSelect.setOnAction(event -> {
            idfromdb = Integer.parseInt(id.get(comboSelect.getSelectionModel().getSelectedIndex()));
        });

        btnCreate.setOnAction(event -> {
            paneRecipt.setVisible(true);
            try {
                gettingDataOfInvoice();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btnBack.setOnAction(event -> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Account-view.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage window = (Stage) btnBack.getScene().getWindow();
            window.setScene(new Scene(root));
//            try {
//                Stage stage = new Stage();  // go to dashboard if information is correct
//                FXMLLoader loader = new FXMLLoader();
//                Pane root = loader.load(getClass().getResource("Account-view.fxml").openStream());
//                DashboardController Msg = (DashboardController) loader.getController();
//                Scene scene = new Scene(root);
//                scene.getStylesheets().setAll(
//                        getClass().getResource("style.css").toExternalForm()
//                );
//                stage.setScene(scene);
//                stage.show();
//                ((Node) event.getSource()).getScene().getWindow().hide();
//
//            } catch (Exception ex) {
//                System.out.println(ex);
//            }
        });


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

    public String getItemName(int item_id) throws SQLException {

        DBConnection connection = new DBConnection();
        List<Map<String, Object>> item_result = connection.getResults("select * from item where ID = '" + item_id + "';");
        connection.closeConnection();

        String item_name =  item_result.get(0).get("name").toString();
        return item_name;

    }

    public void setInvoiceDetails(int customer_id) throws SQLException {

        DBConnection connection = new DBConnection();
        List<Map<String, Object>> customer_order_result = connection.getResults("select * from customer_order where customer_id = '" + customer_id + "';");
        connection.closeConnection();

        int order_id =  ( int)customer_order_result.get(0).get("ID");
        int item_id =  ( int)customer_order_result.get(0).get("item_id");
        String order_date_val =  customer_order_result.get(0).get("order_date").toString();

        String needed_by =  customer_order_result.get(0).get("need_by").toString();
        double item_quantity =  (double)customer_order_result.get(0).get("quantity");
        double subtotal =  ( double)customer_order_result.get(0).get("subtotal");
        double total_val =  ( double)customer_order_result.get(0).get("final_total");


        String setInvoiceDate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        invoice_date.setText(setInvoiceDate);
        order_date.setText(order_date_val);
        customer_order_no.setText(Integer.toString(order_id));
        lbltotal.setText(Double.toString(total_val) );

        item1.setText(getItemName(item_id));
        need1.setText(needed_by);
        q1.setText(Double.toString(item_quantity));
        c1.setText(Double.toString(total_val));



    }


    public void gettingDataOfInvoice() throws SQLException {

        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select * from customer where company_name = '" + comboSelect.getValue().toString() + "';");
        connection.closeConnection();
//
        int cid = (int) results.get(0).get("ID");
        setInvoiceDetails(cid);

        String customer_id =  results.get(0).get("company_name").toString();

//        List<Map<String, Object>> customer_order_result = connection.getResults("select * from customer_order where customer_id = '" + cid + "';");
//
////
//
//
//        String order_date_val =  customer_order_result.get(0).get("order_date").toString();
//        String order_no =  customer_order_result.get(0).get("id").toString();
////
////        String final_total =  customer_order_result.get(0).get("final_total").toString();
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        LocalDateTime now = LocalDateTime.now();





//        try {
//
//
//            String query = "Select * from distributer.customerinvoice";
//            Statement s = connection.createStatement();
//            ResultSet rs = s.executeQuery(query);
//            int temp = 0;
//            while (rs.next()) {
//                temp = Integer.parseInt(rs.getString(1));
//            }
//            invoice_id.setText(++temp + "");
//            invoice_date.setText(String.valueOf(now));
//
//
//            s = connection.createStatement();
//            query = "INSERT INTO distributer.customerinvoice(customerorderid, invoicedate, orderdate) VALUES ('" + customer_order_no.getText() + "','" + dtf.format(now) + "','" + order_date.getText() + "'  )";
//            s.execute(query);
//
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    }

    public void getData(String customer) {
        try {
            listInvoices.getItems().clear();
            String query = "Select id,orderdate,invoicedate,subtotal from distributer.customerinvoice where givencustomername = '" + customer + "'";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            listInvoices.getItems().add("ID" + " \t\t " + "ORDER DATE" + " \t\t " + "INVOICE DATE" + " \t\t " + "TOTAL");
            while (rs.next()) {
                listInvoices.getItems().add(rs.getString(1) + " \t\t " + rs.getString(2) + " \t\t " + rs.getString(3) + " \t\t\t " + rs.getString(4));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    public void customerValueChanged() throws SQLException {
        String customer_name = comboSelect.getValue().toString();
    }


}
