package com.example.se_project;


import java.sql.SQLException;

public class Customer  {
    private int ID;
    private String name;
    private Address address;
    private String phone;
    private double balance;
    private double lastPaidAmount;
    private Date lastOrderDate;
    public Customer(String name, String street, String city, String state, String phone) throws SQLException {
        this.name = name;
        this.address = new Address(street, city, state);
        this.phone = formatPhoneNumber(phone);
        addToDB();
    }
    public String formatPhoneNumber(String number)
    {
        return number.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");

    }
    public void addToDB() throws SQLException {
        String query = "INSERT INTO customer(company_name, street_adress, city, state, phone) values('"+ name+ "','"+ address.getStreet()+ "','"+
                address.getCity()+"', '"+ address.getState()+ "','"+ phone + "');";
        DBConnection connection = new DBConnection();
        connection.addEntryToDB(query);

        connection.closeConnection();

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public double getLastPaidAmount() {
        return lastPaidAmount;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLastPaidAmount(double lastPaidAmount) {
        this.lastPaidAmount = lastPaidAmount;
    }
}
