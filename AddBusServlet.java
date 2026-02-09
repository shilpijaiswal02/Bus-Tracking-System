package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import com.mycompany.bustrackingsystem.modelclass.Bus;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addBus")
public class AddBusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addbus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String busNumber = request.getParameter("busNumber");
        String driverIdStr = request.getParameter("driverId");
        String currentLocation = request.getParameter("currentLocation");
        String route = request.getParameter("route"); // ✅ added
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));

        int driverId = 0;
        if (driverIdStr != null && !driverIdStr.isEmpty()) {
            driverId = Integer.parseInt(driverIdStr);
        }

        Bus bus = new Bus();
        bus.setBusNumber(busNumber);
        bus.setDriverId(driverId);
        bus.setCurrentLocation(currentLocation);
        bus.setRoute(route); // ✅ added
        bus.setLatitude(latitude);
        bus.setLongitude(longitude);

        BusDAO dao = new BusDAO();
        boolean added = dao.addBus(bus);

        if (added) {
            request.setAttribute("message", "✅ Bus added successfully!");
        } else {
            request.setAttribute("message", "❌ Failed to add bus. Try again.");
        }

        request.getRequestDispatcher("addbus.jsp").forward(request, response);
    }
}
