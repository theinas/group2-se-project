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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/*

this class is used to control customer invoice
this class get data from database and create its invoice by using panes and labels

 */
public class CustomerInvoiceController implements Initializable {


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
        con = new DB_Connection();
        connection = con.getConnection();

        paneRecipt.setVisible(false);


        comboSelect.setOnAction(event -> {
            idfromdb = Integer.parseInt(id.get(comboSelect.getSelectionModel().getSelectedIndex()));
        });

        btnCreate.setOnAction(event -> {
            paneRecipt.setVisible(true);
            gettingDataOfInvoice();
        });

        btnBack.setOnAction(event -> {
            try {
                Stage stage = new Stage();  // go to dashboard if information is correct
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("Dashboard.fxml").openStream());
                DashboardController Msg = (DashboardController) loader.getController();
                Scene scene = new Scene(root);
                scene.getStylesheets().setAll(
                        getClass().getResource("style.css").toExternalForm()
                );
                stage.setScene(scene);
                stage.show();
                ((Node) event.getSource()).getScene().getWindow().hide();

            } catch (Exception ex) {
                System.out.println(ex);
            }
        });


    }

    public void gettingDataOfInvoice() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        try {


            String query = "Select * from distributer.customerinvoice";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            int temp = 0;
            while (rs.next()) {
                temp = Integer.parseInt(rs.getString(1));
            }
            invoice_id.setText(++temp + "");


            s = connection.createStatement();
            query = "INSERT INTO distributer.customerinvoice(customerorderid, invoicedate, orderdate) VALUES ('" + customer_order_no.getText() + "','" + dtf.format(now) + "','" + order_date.getText() + "'  )";
            s.execute(query);


        } catch (Exception e) {
            System.out.println(e.toString());
        }
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


}
