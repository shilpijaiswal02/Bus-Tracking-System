package com.mycompany.bustrackingsystem.daopackage;

import com.mycompany.bustrackingsystem.dbconnection.DBConnection;
import com.mycompany.bustrackingsystem.modelclass.Stop;
import java.sql.*;
import java.util.*;

public class StopDAO {
    // Returns a List of String arrays: [stopName, latitude, longitude]
   public List<Stop> getAllStops() {
    List<Stop> stops = new ArrayList<>();
    String sql = "SELECT stop_name, latitude, longitude FROM stops";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            stops.add(new Stop(
                rs.getString("stop_name"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude")
            ));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return stops;
}
   public Stop getStopByName(String stopName){
    Stop stop = null;
    String sql = "SELECT * FROM stops WHERE stop_name = ?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, stopName);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            stop = new Stop();
            stop.setStopName(rs.getString("stop_name"));
            stop.setLatitude(rs.getDouble("latitude"));
            stop.setLongitude(rs.getDouble("longitude"));
        }
    } catch(Exception e){
        e.printStackTrace();
    }
    return stop;
}


}
