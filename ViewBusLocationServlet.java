package com.mycompany.bustrackingsystem.servlet;


import com.mycompany.bustrackingsystem.daopackage.BusDAO;
import com.mycompany.bustrackingsystem.modelclass.Bus;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;


@WebServlet("/api/buses")
public class ViewBusLocationServlet extends HttpServlet {
    private BusDAO dao = new BusDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*"); // optional for CORS

        try (PrintWriter out = resp.getWriter()) {
            List<Bus> buses = dao.getAllBuses();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write(gson.toJson(buses));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            resp.getWriter().write(gson.toJson(error));
        }
    }
}
