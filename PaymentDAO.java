package com.mycompany.bustrackingsystem.daopackage;

import com.mycompany.bustrackingsystem.dbconnection.DBConnection;
import com.mycompany.bustrackingsystem.modelclass.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // Make a payment (insert)
    public boolean makePayment(int studentId, double amount) {
        boolean result = false;
        String sql = "INSERT INTO payments (student_id, amount, status) VALUES (?, ?, 'done')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDouble(2, amount);
            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Get all payments (admin view)
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments ORDER BY payment_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Payment p = new Payment();
                p.setPaymentId(rs.getInt("payment_id"));
                p.setStudentId(rs.getInt("student_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPaymentDate(rs.getTimestamp("payment_date"));
                p.setStatus(rs.getString("status"));
                payments.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    // Get payments by student (for student dashboard)
    public List<Payment> getPaymentsByStudentId(int studentId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE student_id=? ORDER BY payment_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Payment p = new Payment();
                    p.setPaymentId(rs.getInt("payment_id"));
                    p.setStudentId(rs.getInt("student_id"));
                    p.setAmount(rs.getDouble("amount"));
                    p.setPaymentDate(rs.getTimestamp("payment_date"));
                    p.setStatus(rs.getString("status"));
                    payments.add(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    // Update payment status
    public boolean updatePaymentStatus(int paymentId, String status) {
        boolean updated = false;
        String sql = "UPDATE payments SET status=? WHERE payment_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, paymentId);
            updated = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    // Get payment by ID
    public Payment getPaymentById(int paymentId) {
        Payment p = null;
        String sql = "SELECT * FROM payments WHERE payment_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paymentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Payment();
                    p.setPaymentId(rs.getInt("payment_id"));
                    p.setStudentId(rs.getInt("student_id"));
                    p.setAmount(rs.getDouble("amount"));
                    p.setPaymentDate(rs.getTimestamp("payment_date"));
                    p.setStatus(rs.getString("status"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
