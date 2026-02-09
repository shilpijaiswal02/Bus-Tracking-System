package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateBus")
public class BusUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int busId = Integer.parseInt(request.getParameter("busId"));
            String location = request.getParameter("location");
            double latitude = Double.parseDouble(request.getParameter("latitude"));
            double longitude = Double.parseDouble(request.getParameter("longitude"));

            BusDAO dao = new BusDAO();
            boolean success = dao.updateBusLocation(busId, location, latitude, longitude);

            if (success) {
                request.setAttribute("message", "Bus location updated successfully!");
            } else {
                request.setAttribute("message", "Update failed.");
            }

            request.getRequestDispatcher("driverDashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.getRequestDispatcher("driverDashboard.jsp").forward(request, response);
        }
    }
}
