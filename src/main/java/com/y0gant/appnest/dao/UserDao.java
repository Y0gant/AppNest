package com.y0gant.appnest.dao;

import com.y0gant.appnest.model.User;
import com.y0gant.appnest.utils.DBConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public boolean userExists(String username, String email) {
        String sql = "SELECT 1 FROM users WHERE username = ? AND email = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // if any record exists
            }

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error checking if user exists: {} / {}", username, email, e);
            return true; // to avoid duplicate insertion if DB check fails
        }
    }

    public boolean registerUser(User user) {
        if (userExists(user.getName(), user.getEmail())) {
            return false;
        }
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            int rows = stmt.executeUpdate();
            logger.info("Inserted {} row(s) for user: {}", rows, user.getEmail());
            return rows > 0;

        } catch (SQLException e) {
            logger.error("SQL Exception while registering user: {}", user.getEmail(), e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("JDBC Driver not found while registering user: {}", user.getEmail(), e);
            throw new RuntimeException(e);
        }
    }

    public User findUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        return getUser(email, password, sql);
    }

    public User findUserByUserNameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        return getUser(username, password, sql);
    }

    private User getUser(String identifier, String password, String sql) {
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, identifier);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                logger.info("User found: {}", identifier);
                return user;
            } else {
                logger.warn("No user found for identifier: {}", identifier);
            }

        } catch (SQLException e) {
            logger.error("SQL Exception while fetching user with identifier: {}", identifier, e);
        } catch (ClassNotFoundException e) {
            logger.error("JDBC Driver not found while fetching user with identifier: {}", identifier, e);
        }
        return null;
    }
}
