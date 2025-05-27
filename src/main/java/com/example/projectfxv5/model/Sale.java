package com.example.projectfxv5.model;

import java.time.LocalDate;

/**
 * Model class representing a Sale entity.
 */
public class Sale {
    private String id;
    private String carId;
    private String customerId;
    private String employeeId;
    private double amount;
    private String date;

    // Default constructor
    public Sale() {
    }

    // Parameterized constructor
    public Sale(String id, String carId, String customerId, String employeeId, double amount, String date) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Car ID: " + carId + ", Customer ID: " + customerId + 
               ", Employee ID: " + employeeId + ", Amount: " + amount + ", Date: " + date;
    }
}