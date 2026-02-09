package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.userDAO;
import com.mycompany.bustrackingsystem.modelclass.user;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class AdminUserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        user currentUser = (user) (session != null ? session.getAttribute("user") : null);

        if (currentUser == null || !"admin".equals(currentUser.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        userDAO dao = new userDAO();

        // Fetch admin and driver lists
        List<user> adminList = dao.getUsersByRole("admin");
        List<user> driverList = dao.getUsersByRole("driver");

        // Set as request attributes
        request.setAttribute("adminList", adminList);
        request.setAttribute("driverList", driverList);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
        dispatcher.forward(request, response);
    }
}
