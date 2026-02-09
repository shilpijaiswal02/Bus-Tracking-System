package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import com.mycompany.bustrackingsystem.modelclass.user;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateBusLocation")
public class UpdateBusLocationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String message = "";
        String color = "red";

        // Get user from session
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("login.jsp");
            return;
        }
        user user = (user) session.getAttribute("user");

        try {
            int busId = Integer.parseInt(request.getParameter("busId"));
            String stopName = request.getParameter("currentLocation");
            double latitude = Double.parseDouble(request.getParameter("latitude"));
            double longitude = Double.parseDouble(request.getParameter("longitude"));

            BusDAO dao = new BusDAO();
            boolean updated = dao.updateBusLocation(busId, stopName, latitude, longitude);

            if(updated){
                message = "Location updated successfully!";
                color = "lightgreen";
            } else {
                message = "Failed to update location!";
            }

        } catch (NumberFormatException e) {
            message = "Invalid input!";
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        }

        // Keep session alive & forward to dashboard
        request.setAttribute("message", message);
        request.setAttribute("color", color);
        request.getRequestDispatcher("driverDashboard.jsp").forward(request, response);
    }
}
