package com.example.projectfxv5.model;

/**
 * Model class representing an Employee entity.
 */
public class Employee {
    private String id;
    private String name;
    private double salary;
    // Commission rate for the employee
    private double commissionRate = 0.10;

    // Default constructor
    public Employee() {
    }

    // Parameterized constructor
    public Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Parameterized constructor with commission rate
    public Employee(String id, String name, double salary, double commissionRate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Commission Rate: " + commissionRate;
    }
}
