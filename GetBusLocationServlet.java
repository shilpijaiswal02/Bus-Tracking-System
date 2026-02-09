package com.mycompany.bustrackingsystem.servlet;

import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import com.mycompany.bustrackingsystem.modelclass.Bus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.IOException;

@WebServlet("/getBusLocation")
public class GetBusLocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // You can fetch the specific bus or all buses
        int busId = 1; // example: fixed bus ID or dynamically fetch based on session/driver

        BusDAO dao = new BusDAO();
        Bus bus = dao.getBusById(busId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if(bus != null) {
            String json = new Gson().toJson(bus);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("{}");
        }
    }
}
