/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.bustrackingsystem.servlet;



import com.mycompany.bustrackingsystem.daopackage.PaymentDAO;
import com.mycompany.bustrackingsystem.modelclass.Payment;
import com.mycompany.bustrackingsystem.modelclass.user;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PaymentDAO dao = new PaymentDAO();
        List<Payment> payments = dao.getAllPayments();
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        user user = (user) session.getAttribute("user");

        double amount = Double.parseDouble(request.getParameter("amount"));
        PaymentDAO dao = new PaymentDAO();
        boolean result = dao.makePayment(user.getId(), amount);

        if (result)
            request.setAttribute("message", "Payment successful!");
        else
            request.setAttribute("message", "Payment failed!");

        request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
    }
}
