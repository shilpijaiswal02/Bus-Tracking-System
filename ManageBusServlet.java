/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.bustrackingsystem.servlet;



import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import com.mycompany.bustrackingsystem.modelclass.Bus;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/manageBus")
public class ManageBusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BusDAO dao = new BusDAO();
        List<Bus> buses = dao.getAllBuses();
        request.setAttribute("busList", buses);
        request.getRequestDispatcher("manageBus.jsp").forward(request, response);
    }
}
