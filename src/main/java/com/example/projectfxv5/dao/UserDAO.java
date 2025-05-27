package com.example.projectfxv5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.projectfxv5.DatabaseConfig;
import com.example.projectfxv5.model.User;

public class UserDAO {

    public User getUserByUsername(String username) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"));
            }
            return null;
        }
    }
}
