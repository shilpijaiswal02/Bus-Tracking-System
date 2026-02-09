package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.userDAO;
import com.mycompany.bustrackingsystem.modelclass.user;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();
        String role = "student"; // ✅ force role to student

        userDAO dao = new userDAO();

        // ✅ Check if username already exists
        if (dao.isUsernameExists(username)) {
            request.setAttribute("error", "Username already exists!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // ✅ Create new user object
        user newUser = new user();
        newUser.setUsername(username);
        newUser.setPassword(password); // Consider hashing in production
        newUser.setEmail(email);
        newUser.setRole(role);

        // ✅ Use registerStudent() for public registration
        if (dao.registerStudent(newUser)) {
            request.setAttribute("message", "Registration successful! Please login.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed! Try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
