package com.example.se_project;

//Developer: Tanni Dev

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class CustomerInvoiceControllerTest {
    /**
     * Method under test: {@link CustomerInvoiceController#initialize(URL, ResourceBundle)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInitialize() {
        // TODO: Complete this test.
        //   Reason: F009 Internal error.
        //   java.lang.AssertionError: Can't invoke method com.apple.laf.resources.aqua() with arguments []
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support/) providing details about this error.

        // Arrange
        // TODO: Populate arranged inputs
        CustomerInvoiceController customerInvoiceController = null;
        URL url = null;
        ResourceBundle rb = null;

        // Act
        customerInvoiceController.initialize(url, rb);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link CustomerInvoiceController#getCustomersFromDB()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCustomersFromDB() throws SQLException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.sql.SQLNonTransientConnectionException: Could not create connection to database server.
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:110)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
        //       at com.mysql.cj.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:1008)
        //       at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:825)
        //       at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:455)
        //       at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:240)
        //       at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:207)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:681)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:252)
        //       at com.example.se_project.DBConnection.<init>(DBConnection.java:16)
        //       at com.example.se_project.CustomerInvoiceController.getCustomersFromDB(CustomerInvoiceController.java:211)
        //   In order to prevent getCustomersFromDB()
        //   from throwing SQLNonTransientConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCustomersFromDB().
        //   See https://diff.blue/R013 to resolve this issue.

        (new CustomerInvoiceController()).getCustomersFromDB();
    }

    /**
     * Method under test: {@link CustomerInvoiceController#addCustomersToList(List)}
     */
    @Test
    void testAddCustomersToList() {
        CustomerInvoiceController customerInvoiceController = new CustomerInvoiceController();
        ArrayList<Map<String, Object>> mapList = new ArrayList<>();
        customerInvoiceController.addCustomersToList(mapList);
        assertEquals(0, customerInvoiceController.idfromdb);
        assertEquals(mapList, customerInvoiceController.id);
    }

    /**
     * Method under test: {@link CustomerInvoiceController#addCustomersToList(List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCustomersToList2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.toString()" because the return value of "com.diffblue.cover.agent.readwrite.RuntimeWrappers.map$get(java.util.Map, Object)" is null
        //       at com.example.se_project.CustomerInvoiceController.addCustomersToList(CustomerInvoiceController.java:220)
        //   In order to prevent addCustomersToList(List)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addCustomersToList(List).
        //   See https://diff.blue/R013 to resolve this issue.

        CustomerInvoiceController customerInvoiceController = new CustomerInvoiceController();

        ArrayList<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(new HashMap<>());
        customerInvoiceController.addCustomersToList(mapList);
    }

    /**
     * Method under test: {@link CustomerInvoiceController#addCustomersToList(List)}
     */
    @Test
    void testAddCustomersToList3() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by addCustomersToList(List)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        CustomerInvoiceController customerInvoiceController = new CustomerInvoiceController();

        HashMap<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("company_name", "Value");

        ArrayList<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(stringObjectMap);
        customerInvoiceController.addCustomersToList(mapList);
    }

    /**
     * Method under test: {@link CustomerInvoiceController#getItemName(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetItemName() throws SQLException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.sql.SQLNonTransientConnectionException: Could not create connection to database server.
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:110)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
        //       at com.mysql.cj.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:1008)
        //       at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:825)
        //       at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:455)
        //       at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:240)
        //       at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:207)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:681)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:252)
        //       at com.example.se_project.DBConnection.<init>(DBConnection.java:16)
        //       at com.example.se_project.CustomerInvoiceController.getItemName(CustomerInvoiceController.java:226)
        //   In order to prevent getItemName(int)
        //   from throwing SQLNonTransientConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getItemName(int).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CustomerInvoiceController()).getItemName(1);
    }

    /**
     * Method under test: {@link CustomerInvoiceController#setInvoiceDetails(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetInvoiceDetails() throws SQLException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.sql.SQLNonTransientConnectionException: Could not create connection to database server.
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:110)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
        //       at com.mysql.cj.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:1008)
        //       at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:825)
        //       at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:455)
        //       at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:240)
        //       at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:207)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:681)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:252)
        //       at com.example.se_project.DBConnection.<init>(DBConnection.java:16)
        //       at com.example.se_project.CustomerInvoiceController.setInvoiceDetails(CustomerInvoiceController.java:237)
        //   In order to prevent setInvoiceDetails(int)
        //   from throwing SQLNonTransientConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   setInvoiceDetails(int).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CustomerInvoiceController()).setInvoiceDetails(1);
    }

    /**
     * Method under test: {@link CustomerInvoiceController#gettingDataOfInvoice()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGettingDataOfInvoice() throws SQLException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.sql.SQLNonTransientConnectionException: Could not create connection to database server.
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:110)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
        //       at com.mysql.cj.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:1008)
        //       at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:825)
        //       at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:455)
        //       at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:240)
        //       at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:207)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:681)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:252)
        //       at com.example.se_project.DBConnection.<init>(DBConnection.java:16)
        //       at com.example.se_project.CustomerInvoiceController.gettingDataOfInvoice(CustomerInvoiceController.java:304)
        //   In order to prevent gettingDataOfInvoice()
        //   from throwing SQLNonTransientConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   gettingDataOfInvoice().
        //   See https://diff.blue/R013 to resolve this issue.

        (new CustomerInvoiceController()).gettingDataOfInvoice();
    }

    /**
     * Method under test: {@link CustomerInvoiceController#getData(String)}
     */
    @Test
    void testGetData() {
        CustomerInvoiceController customerInvoiceController = new CustomerInvoiceController();
        customerInvoiceController.getData("Customer");
        assertEquals(0, customerInvoiceController.idfromdb);
        assertTrue(customerInvoiceController.id.isEmpty());
    }

    /**
     * Method under test: {@link CustomerInvoiceController#customerValueChanged()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCustomerValueChanged() throws SQLException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javafx.scene.control.ComboBox.getValue()" because "this.comboSelect" is null
        //       at com.example.se_project.CustomerInvoiceController.customerValueChanged(CustomerInvoiceController.java:372)
        //   In order to prevent customerValueChanged()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   customerValueChanged().
        //   See https://diff.blue/R013 to resolve this issue.

        (new CustomerInvoiceController()).customerValueChanged();
    }
}

