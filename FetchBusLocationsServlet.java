/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import com.mycompany.bustrackingsystem.modelclass.Bus;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author shilpi
 */
@WebServlet("/fetchBusLocations")
public class FetchBusLocationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BusDAO busDAO = new BusDAO();
        List<Bus> buses = busDAO.getAllBuses();

        // Build JSON manually or use Gson/Jackson
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<buses.size(); i++) {
            Bus b = buses.get(i);
            sb.append("{")
              .append("\"busId\":").append(b.getBusId()).append(",")
              .append("\"currentLocation\":\"").append(b.getCurrentLocation()).append("\",")
              .append("\"latitude\":").append(b.getLatitude()).append(",")
              .append("\"longitude\":").append(b.getLongitude())
              .append("}");
            if(i < buses.size()-1) sb.append(",");
        }
        sb.append("]");
        response.getWriter().write(sb.toString());
    }
}
