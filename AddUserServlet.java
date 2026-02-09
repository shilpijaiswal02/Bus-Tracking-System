package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.userDAO;
import com.mycompany.bustrackingsystem.modelclass.user;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        user currentUser = (user) session.getAttribute("user");

        if (currentUser == null || !"admin".equals(currentUser.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();
        String role = request.getParameter("role").trim();

        if (!"driver".equals(role) && !"admin".equals(role)) {
            request.setAttribute("error", "Invalid role selection.");
            request.getRequestDispatcher("addUser.jsp").forward(request, response);
            return;
        }

        user newUser = new user();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setRole(role);

        userDAO dao = new userDAO();
        boolean success = dao.addUserByAdmin(newUser);

        if (success) {
            request.setAttribute("message", "New " + role + " added successfully!");
        } else {
            request.setAttribute("error", "Failed to add new user. Username may already exist.");
        }

        request.getRequestDispatcher("addUser.jsp").forward(request, response);
    }
}
