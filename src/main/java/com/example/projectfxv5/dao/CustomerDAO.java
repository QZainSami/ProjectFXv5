package com.example.projectfxv5.dao;

import com.example.projectfxv5.DatabaseConfig;
import com.example.projectfxv5.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Customer entities.
 * Handles database operations related to customers.
 */
public class CustomerDAO implements DAO<Customer, String> {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> getAll() throws SQLException {
        return getAllCustomers();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Customer getById(String id) throws SQLException {
        return getCustomerById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Customer customer) throws SQLException {
        addCustomer(customer);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Customer customer) throws SQLException {
        updateCustomer(customer);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) throws SQLException {
        return deleteCustomer(id);
    }
    
    /**
     * Retrieves all customers from the database.
     * 
     * @return List of Customer objects
     * @throws SQLException if a database error occurs
     */
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Customers")) {
            
            while (rs.next()) {
                Customer customer = mapResultSetToCustomer(rs);
                customers.add(customer);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
        
        return customers;
    }
    
    /**
     * Retrieves a customer by their ID.
     * 
     * @param id The ID of the customer to retrieve
     * @return The Customer object or null if not found
     * @throws SQLException if a database error occurs
     */
    public Customer getCustomerById(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customers WHERE id = ?")) {
            
            stmt.setString(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
        
        return null;
    }
    
    /**
     * Adds a new customer to the database.
     * 
     * @param customer The Customer object to add
     * @throws SQLException if a database error occurs
     */
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customers (id, name, phone, email) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getEmail());
            
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
    
    /**
     * Updates an existing customer in the database.
     * 
     * @param customer The Customer object to update
     * @throws SQLException if a database error occurs
     */
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customers SET name = ?, phone = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getId());
            
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
    
    /**
     * Deletes a customer from the database by their ID.
     * 
     * @param id The ID of the customer to delete
     * @return The number of rows affected (should be 1 if successful)
     * @throws SQLException if a database error occurs
     */
    public int deleteCustomer(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Customers WHERE id = ?")) {
            
            stmt.setString(1, id);
            return stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
    
    /**
     * Maps a ResultSet row to a Customer object.
     * 
     * @param rs The ResultSet containing customer data
     * @return A new Customer object with data from the ResultSet
     * @throws SQLException if a database error occurs
     */
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getString("id"));
        customer.setName(rs.getString("name"));
        customer.setPhone(rs.getString("phone"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }
}