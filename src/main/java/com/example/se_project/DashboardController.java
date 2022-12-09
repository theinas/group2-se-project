/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.se_project;
/*
Developer: Tanni Dev

this class display the buttons for the dashboard screen and also check that which user use which features. and show him features accordingly
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {


    @FXML
    private Button btnUser;

    @FXML
    private Button btnVendor;

    @FXML
    private Button btnItem;

    @FXML
    private Button btnPurchaseOrder;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCustomerOrder;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnCustomerProfile;

    static boolean Disable = true;

    DB_Connection con;
    Connection connection;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        con = new DB_Connection();
        connection = con.getConnection();

        btnVendor.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("Vendor_Customer.fxml").openStream());
                Vendor_CustomerController Msg = (Vendor_CustomerController) loader.getController();
                Scene scene = new Scene(root);
                scene.getStylesheets().setAll(
                        getClass().getResource("style.css").toExternalForm()
                );
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                ((Node) e.getSource()).getScene().getWindow().hide();

            } catch (Exception ex) {
                System.out.println(ex);
            }

        });

        btnCustomer.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("CustomerInvoice.fxml").openStream());
                CustomerInvoiceController Msg = (CustomerInvoiceController) loader.getController();
                Scene scene = new Scene(root);
                scene.getStylesheets().setAll(
                        getClass().getResource("style.css").toExternalForm()
                );
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                ((Node) e.getSource()).getScene().getWindow().hide();

            } catch (Exception ex) {
                System.out.println(ex);
            }

        });


    }

    public void alertAccountant() {
        int temp = 0;
        String query = "Select status from distributer.customerorder";
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                if ((rs.getString(1)).equals("deactive")) {
                    temp++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (temp >= 2) {
            AlertController a = new AlertController(Alert.AlertType.INFORMATION, "Accountant User", "More then two Customers Orders are Avaliable");
        }
    }

    public void alertExpired() {
        int temp = 0;
        String query = "Select expirydate from distributer.item";
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                SimpleDateFormat
                        sdfo
                        = new SimpleDateFormat("yyyy-MM-dd");

                // Get the two dates to be compared
                Date d1 = null;
                Date d2 = null;
                try {
                    LocalDate todayDate = LocalDate.now();
                    d1 = sdfo.parse(rs.getString(1));
                    d2 = sdfo.parse(todayDate.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (d1.compareTo(d2) < 0) {
                    temp++;
                } else if (d1.compareTo(d2) == 0) {
                    temp++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("temp " + temp);
        if (temp >= 2) {
            AlertController a = new AlertController(Alert.AlertType.INFORMATION, "Sales User", "More then two Items are Expired");
        }
    }

    public void alertOutOfStock() {
        int temp = 0;
        String query = "Select quantityonhand from distributer.item";
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(1).equals("0")) {
                    temp++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("temp " + temp);
        if (temp >= 2) {
            AlertController a = new AlertController(Alert.AlertType.INFORMATION, "Purchaser User", "More then two Items are Out of Stock");
        }
    }

    public void sessionalDiscountStartDate() {
        String query = "Select fname from distributer.vendor where sessionaldiscountstartdate = current_date ";
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                AlertController a = new AlertController(Alert.AlertType.INFORMATION, "Sessional Discount", "Sessional Discount Started for given Vendor " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
