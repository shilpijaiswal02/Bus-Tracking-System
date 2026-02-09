/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.bustrackingsystem.servlet;



import com.mycompany.bustrackingsystem.daopackage.chatDAO;
import com.mycompany.bustrackingsystem.modelclass.chat;
import com.mycompany.bustrackingsystem.modelclass.user;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        user user = (user) session.getAttribute("user");

        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        chatDAO dao = new chatDAO();
        List<chat> chatList = dao.getChatHistory(user.getId(), receiverId);

        request.setAttribute("chatList", chatList);
        request.setAttribute("receiverId", receiverId);
        request.getRequestDispatcher("chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        user user = (user) session.getAttribute("user");

        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        String message = request.getParameter("message");

        chatDAO dao = new chatDAO();
        dao.sendMessage(user.getId(), receiverId, message);

        response.sendRedirect("chat?receiverId=" + receiverId);
    }
}
