package com.mycompany.bustrackingsystem.daopackage;

import com.mycompany.bustrackingsystem.dbconnection.DBConnection;
import com.mycompany.bustrackingsystem.modelclass.user;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    // Login validation
    public user validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username.trim());
            ps.setString(2, password.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Public student registration
    public boolean registerStudent(user u) {
        if (!"student".equals(u.getRole())) {
            System.out.println("⚠️ Only students can register via public registration.");
            return false;
        }
        return insertUser(u);
    }

    // Admin adding driver or admin
    public boolean addUserByAdmin(user u) {
        if (!"driver".equals(u.getRole()) && !"admin".equals(u.getRole())) {
            System.out.println("⚠️ Admin can only add 'driver' or 'admin'.");
            return false;
        }
        return insertUser(u);
    }

    // Private common insert method
    private boolean insertUser(user u) {
        String sql = "INSERT INTO users(username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUsername().trim());
            ps.setString(2, u.getPassword().trim());
            ps.setString(3, u.getEmail().trim());
            ps.setString(4, u.getRole().trim());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if username exists
    public boolean isUsernameExists(String username) {
        String sql = "SELECT id FROM users WHERE username=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username.trim());
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch users by role
    public List<user> getUsersByRole(String role) {
        List<user> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, role.trim());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
