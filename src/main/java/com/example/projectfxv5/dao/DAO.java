package com.example.projectfxv5.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Generic DAO interface defining common operations for all entities.
 * 
 * @param <T> The entity type
 * @param <ID> The type of the entity's ID
 */
public interface DAO<T, ID> {
    
    /**
     * Retrieves all entities.
     * 
     * @return List of all entities
     * @throws SQLException if a database error occurs
     */
    List<T> getAll() throws SQLException;
    
    /**
     * Retrieves an entity by its ID.
     * 
     * @param id The ID of the entity to retrieve
     * @return The entity or null if not found
     * @throws SQLException if a database error occurs
     */
    T getById(ID id) throws SQLException;
    
    /**
     * Adds a new entity.
     * 
     * @param entity The entity to add
     * @throws SQLException if a database error occurs
     */
    void add(T entity) throws SQLException;
    
    /**
     * Updates an existing entity.
     * 
     * @param entity The entity to update
     * @throws SQLException if a database error occurs
     */
    void update(T entity) throws SQLException;
    
    /**
     * Deletes an entity by its ID.
     * 
     * @param id The ID of the entity to delete
     * @return The number of rows affected
     * @throws SQLException if a database error occurs
     */
    int delete(ID id) throws SQLException;
}