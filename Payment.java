package com.mycompany.bustrackingsystem.modelclass;

import java.sql.Timestamp;

public class Payment {
    private int paymentId;
    private int studentId;
    private double amount;
    private Timestamp paymentDate; // Added payment date
    private String status;

    // Default constructor
    public Payment() {}

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: for debugging
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", studentId=" + studentId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", status='" + status + '\'' +
                '}';
    }
}
