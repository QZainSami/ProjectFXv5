package com.example.projectfxv5.dao;

import com.example.projectfxv5.DatabaseConfig;
import com.example.projectfxv5.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Employee entities.
 * Handles database operations related to employees.
 */
public class EmployeeDAO implements DAO<Employee, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAll() throws SQLException {
        return getAllEmployees();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getById(String id) throws SQLException {
        return getEmployeeById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Employee employee) throws SQLException {
        addEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Employee employee) throws SQLException {
        updateEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) throws SQLException {
        return deleteEmployee(id);
    }

    /**
     * Retrieves all employees from the database.
     * 
     * @return List of Employee objects
     * @throws SQLException if a database error occurs
     */
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employees")) {

            while (rs.next()) {
                Employee employee = mapResultSetToEmployee(rs);
                employees.add(employee);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return employees;
    }

    /**
     * Retrieves an employee by their ID.
     * 
     * @param id The ID of the employee to retrieve
     * @return The Employee object or null if not found
     * @throws SQLException if a database error occurs
     */
    public Employee getEmployeeById(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE id = ?")) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEmployee(rs);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return null;
    }

    /**
     * Adds a new employee to the database.
     * 
     * @param employee The Employee object to add
     * @throws SQLException if a database error occurs
     */
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employees (id, name, salary, commissionRate) VALUES (?, ?, ?, 0.10)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setDouble(3, employee.getSalary());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Updates an existing employee in the database.
     * 
     * @param employee The Employee object to update
     * @throws SQLException if a database error occurs
     */
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employees SET name = ?, salary = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setDouble(2, employee.getSalary());
            pstmt.setString(3, employee.getId());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Deletes an employee from the database by their ID.
     * 
     * @param id The ID of the employee to delete
     * @return The number of rows affected (should be 1 if successful)
     * @throws SQLException if a database error occurs
     */
    public int deleteEmployee(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Employees WHERE id = ?")) {

            stmt.setString(1, id);
            return stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Maps a ResultSet row to an Employee object.
     * 
     * @param rs The ResultSet containing employee data
     * @return A new Employee object with data from the ResultSet
     * @throws SQLException if a database error occurs
     */
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getString("id"));
        employee.setName(rs.getString("name"));
        employee.setSalary(rs.getDouble("salary"));
        // Commission rate is now fixed at 10% for all employees
        return employee;
    }
}
