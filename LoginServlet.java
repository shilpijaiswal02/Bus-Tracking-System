package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.userDAO;
import com.mycompany.bustrackingsystem.modelclass.user;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username") != null ? request.getParameter("username").trim() : "";
        String password = request.getParameter("password") != null ? request.getParameter("password").trim() : "";

        userDAO userDAO = new userDAO();
        user user = userDAO.validateUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // store user in session

            // Redirect based on role
            switch (user.getRole().toLowerCase()) {
                case "student":
                    response.sendRedirect(request.getContextPath() + "/studentDashboard.jsp");
                    break;
                case "driver":
                    response.sendRedirect(request.getContextPath() + "/driverDashboard.jsp");
                    break;
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
                    break;
                default:
                    session.invalidate();
                    request.setAttribute("error", "Unknown role. Contact admin.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
