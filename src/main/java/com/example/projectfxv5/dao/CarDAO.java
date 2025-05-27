package com.example.projectfxv5.dao;

import com.example.projectfxv5.DatabaseConfig;
import com.example.projectfxv5.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Car entities.
 * Handles database operations related to cars.
 */
public class CarDAO implements DAO<Car, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Car> getAll() throws SQLException {
        return getAllCars();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Car getById(String id) throws SQLException {
        return getCarById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Car car) throws SQLException {
        addCar(car);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Car car) throws SQLException {
        updateCar(car);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) throws SQLException {
        return deleteCar(id);
    }

    /**
     * Retrieves all cars from the database.
     * 
     * @return List of Car objects
     * @throws SQLException if a database error occurs
     */
    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Cars")) {

            while (rs.next()) {
                Car car = mapResultSetToCar(rs);
                cars.add(car);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return cars;
    }

    /**
     * Retrieves a car by its ID.
     * 
     * @param id The ID of the car to retrieve
     * @return The Car object or null if not found
     * @throws SQLException if a database error occurs
     */
    public Car getCarById(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cars WHERE id = ?")) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCar(rs);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return null;
    }

    /**
     * Adds a new car to the database.
     * 
     * @param car The Car object to add
     * @throws SQLException if a database error occurs
     */
    public void addCar(Car car) throws SQLException {
        String sql = "INSERT INTO Cars (id, model, variant, price, mileage, color, type, weight, numberPlate, chassisNumber) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getId());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getVariant());
            pstmt.setDouble(4, car.getPrice());
            pstmt.setDouble(5, car.getMileage());
            pstmt.setString(6, car.getColor());
            pstmt.setString(7, car.getType());
            pstmt.setDouble(8, car.getWeight());
            pstmt.setString(9, car.getNumberPlate());
            pstmt.setString(10, car.getChassisNumber());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Updates an existing car in the database.
     * 
     * @param car The Car object to update
     * @throws SQLException if a database error occurs
     */
    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE Cars SET model = ?, variant = ?, price = ?, mileage = ?, " +
                     "color = ?, type = ?, weight = ?, numberPlate = ?, chassisNumber = ? " +
                     "WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getVariant());
            pstmt.setDouble(3, car.getPrice());
            pstmt.setDouble(4, car.getMileage());
            pstmt.setString(5, car.getColor());
            pstmt.setString(6, car.getType());
            pstmt.setDouble(7, car.getWeight());
            pstmt.setString(8, car.getNumberPlate());
            pstmt.setString(9, car.getChassisNumber());
            pstmt.setString(10, car.getId());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Deletes a car from the database by its ID.
     * 
     * @param id The ID of the car to delete
     * @return The number of rows affected (should be 1 if successful)
     * @throws SQLException if a database error occurs
     */
    public int deleteCar(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cars WHERE id = ?")) {

            stmt.setString(1, id);
            return stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    /**
     * Maps a ResultSet row to a Car object.
     * 
     * @param rs The ResultSet containing car data
     * @return A new Car object with data from the ResultSet
     * @throws SQLException if a database error occurs
     */
    private Car mapResultSetToCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getString("id"));
        car.setModel(rs.getString("model"));
        car.setVariant(rs.getString("variant"));
        car.setPrice(rs.getDouble("price"));
        car.setMileage(rs.getDouble("mileage"));
        car.setColor(rs.getString("color"));
        car.setType(rs.getString("type"));
        car.setWeight(rs.getDouble("weight"));
        car.setNumberPlate(rs.getString("numberPlate"));
        car.setChassisNumber(rs.getString("chassisNumber"));
        return car;
    }
}
