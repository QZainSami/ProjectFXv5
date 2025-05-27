package com.example.projectfxv5.model;

/**
 * Model class representing a Customer entity.
 */
public class Customer {
    private String id;
    private String name;
    private String phone;
    private String email;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}