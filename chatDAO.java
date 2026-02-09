/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bustrackingsystem.daopackage;

/**
 *
 * @author shilpi
 */


import com.mycompany.bustrackingsystem.dbconnection.DBConnection;
import com.mycompany.bustrackingsystem.modelclass.chat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class chatDAO {

    public boolean sendMessage(int senderId, int receiverId, String message) {
        boolean sent = false;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO chats (sender_id, receiver_id, message) VALUES (?, ?, ?)"
            );
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setString(3, message);
            sent = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sent;
    }

    public List<chat> getChatHistory(int user1, int user2) {
        List<chat> chatList = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM chats WHERE (sender_id=? AND receiver_id=?) OR (sender_id=? AND receiver_id=?) ORDER BY timestamp ASC"
            );
            ps.setInt(1, user1);
            ps.setInt(2, user2);
            ps.setInt(3, user2);
            ps.setInt(4, user1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                chat c = new chat();
                c.setChatId(rs.getInt("chat_id"));
                c.setSenderId(rs.getInt("sender_id"));
                c.setReceiverId(rs.getInt("receiver_id"));
                c.setMessage(rs.getString("message"));
                c.setTimestamp(rs.getTimestamp("timestamp"));
                chatList.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatList;
    }
}
