package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import com.mycompany.bustrackingsystem.modelclass.Bus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editBus")
public class EditBusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int busId = Integer.parseInt(request.getParameter("id"));
        BusDAO dao = new BusDAO();
        Bus bus = dao.getBusById(busId);

        request.setAttribute("bus", bus);
        RequestDispatcher rd = request.getRequestDispatcher("editBus.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int busId = Integer.parseInt(request.getParameter("busId"));
        String busNumber = request.getParameter("busNumber");
        String currentLocation = request.getParameter("currentLocation");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        String driverIdStr = request.getParameter("driverId");
        Integer driverId = (driverIdStr == null || driverIdStr.isEmpty()) ? null : Integer.parseInt(driverIdStr);
        String route = request.getParameter("route");

        Bus bus = new Bus();
        bus.setBusId(busId);
        bus.setBusNumber(busNumber);
        bus.setCurrentLocation(currentLocation);
        bus.setLatitude(latitude);
        bus.setLongitude(longitude);
        bus.setDriverId(driverId);
        bus.setRoute(route);

        BusDAO dao = new BusDAO();
        boolean updated = dao.updateBus(bus);

        if (updated) {
            // ✅ Redirect to manageBus.jsp with a success message
           request.getSession().setAttribute("message", "Bus updated successfully");
           response.sendRedirect("adminDashboard.jsp");

        } else {
            // Forward back to edit page with error message
            request.setAttribute("bus", bus); // Keep the filled data
            request.setAttribute("error", "Failed to update bus.");
            RequestDispatcher rd = request.getRequestDispatcher("editBus.jsp");
            rd.forward(request, response);
        }
    }
}
