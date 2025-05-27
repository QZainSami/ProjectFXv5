package com.example.projectfxv5.dao;

import com.example.projectfxv5.DatabaseConfig;
import com.example.projectfxv5.model.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data Access Object for Sale entities.
 * Handles database operations related to sales.
 */
public class SaleDAO implements DAO<Sale, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sale> getAll() throws SQLException {
        return getAllSales();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sale getById(String id) throws SQLException {
        return getSaleById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Sale sale) throws SQLException {
        addSale(sale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Sale sale) throws SQLException {
        updateSale(sale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) throws SQLException {
        return deleteSale(id);
    }

    /**
     * Retrieves all sales from the database.
     * 
     * @return List of Sale objects
     * @throws SQLException if a database error occurs
     */
    public List<Sale> getAllSales() throws SQLException {
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Sales")) {

            while (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sales.add(sale);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return sales;
    }

    /**
     * Retrieves a sale by its ID.
     * 
     * @param id The ID of the sale to retrieve
     * @return The Sale object or null if not found
     * @throws SQLException if a database error occurs
     */
    public Sale getSaleById(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Sales WHERE id = ?")) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSale(rs);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return null;
    }

    /**
     * Retrieves sales by employee ID.
     * 
     * @param employeeId The ID of the employee
     * @return List of Sale objects for the specified employee
     * @throws SQLException if a database error occurs
     */
    public List<Sale> getSalesByEmployeeId(String employeeId) throws SQLException {
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Sales WHERE employeeId = ?")) {

            stmt.setString(1, employeeId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sale sale = mapResultSetToSale(rs);
                    sales.add(sale);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return sales;
    }

    /**
     * Adds a new sale to the database.
     * 
     * @param sale The Sale object to add
     * @throws SQLException if a database error occurs
     */
    public void addSale(Sale sale) throws SQLException {
        String sql = "INSERT INTO Sales (id, carId, customerId, employeeId, amount, date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sale.getId());
            pstmt.setString(2, sale.getCarId());
            pstmt.setString(3, sale.getCustomerId());
            pstmt.setString(4, sale.getEmployeeId());
            pstmt.setDouble(5, sale.getAmount());
            pstmt.setString(6, sale.getDate());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Updates an existing sale in the database.
     * 
     * @param sale The Sale object to update
     * @throws SQLException if a database error occurs
     */
    public void updateSale(Sale sale) throws SQLException {
        String sql = "UPDATE Sales SET carId = ?, customerId = ?, employeeId = ?, amount = ?, date = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sale.getCarId());
            pstmt.setString(2, sale.getCustomerId());
            pstmt.setString(3, sale.getEmployeeId());
            pstmt.setDouble(4, sale.getAmount());
            pstmt.setString(5, sale.getDate());
            pstmt.setString(6, sale.getId());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Deletes a sale from the database by its ID.
     * 
     * @param id The ID of the sale to delete
     * @return The number of rows affected (should be 1 if successful)
     * @throws SQLException if a database error occurs
     */
    public int deleteSale(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Sales WHERE id = ?")) {

            stmt.setString(1, id);
            return stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Maps a ResultSet row to a Sale object.
     * 
     * @param rs The ResultSet containing sale data
     * @return A new Sale object with data from the ResultSet
     * @throws SQLException if a database error occurs
     */
    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId(rs.getString("id"));
        sale.setCarId(rs.getString("carId"));
        sale.setCustomerId(rs.getString("customerId"));
        sale.setEmployeeId(rs.getString("employeeId"));
        sale.setAmount(rs.getDouble("amount"));
        sale.setDate(rs.getString("date"));
        return sale;
    }

    /**
     * Generates the next sale ID based on existing sales.
     * The ID follows the pattern "SALE[XX]" where [XX] is a sequential number with leading zeros.
     * 
     * @return The next available sale ID
     * @throws SQLException if a database error occurs
     */
    public String generateNextSaleId() throws SQLException {
        List<Sale> sales = getAllSales();
        int maxNumber = 0;
        Pattern pattern = Pattern.compile("SALE(\\d+)");

        for (Sale sale : sales) {
            String id = sale.getId();
            Matcher matcher = pattern.matcher(id);
            if (matcher.matches()) {
                try {
                    int number = Integer.parseInt(matcher.group(1));
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                } catch (NumberFormatException e) {
                    // Skip if the number part is not a valid integer
                }
            }
        }

        // Increment the highest number found
        int nextNumber = maxNumber + 1;

        // Format with leading zeros (e.g., SALE001, SALE021)
        return String.format("SALE%03d", nextNumber);
    }
}
