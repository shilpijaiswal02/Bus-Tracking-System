package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteBus")
public class DeleteBusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int busId = Integer.parseInt(request.getParameter("id"));
            BusDAO dao = new BusDAO();
            boolean deleted = dao.deleteBus(busId);

            if (deleted) {
                request.setAttribute("message", "Bus deleted successfully!");
            } else {
                request.setAttribute("error", "Failed to delete bus.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid bus ID.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
        rd.forward(request, response);
    }
}
