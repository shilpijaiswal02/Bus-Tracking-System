/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bustrackingsystem.daopackage;

/**
 *
 * @author shilpi
 */


import com.mycompany.bustrackingsystem.dbconnection.DBConnection;
import com.mycompany.bustrackingsystem.modelclass.route;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class routeDAO {

    public List<route> getAllRoutes() {
        List<route> routes = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM routes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                route r = new route();
                r.setRouteId(rs.getInt("route_id"));
                r.setSource(rs.getString("source"));
                r.setDestination(rs.getString("destination"));
                r.setStops(rs.getString("stops"));
                routes.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }
}
