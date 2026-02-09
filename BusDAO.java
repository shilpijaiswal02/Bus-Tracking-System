package com.mycompany.bustrackingsystem.daopackage;

import com.mycompany.bustrackingsystem.dbconnection.DBConnection;
import com.mycompany.bustrackingsystem.modelclass.Bus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    // ✅ Fetch all buses
    public List<Bus> getAllBuses() {
        List<Bus> list = new ArrayList<>();
        String sql = "SELECT * FROM buses";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bus b = new Bus();
                b.setBusId(rs.getInt("bus_id"));
                b.setBusNumber(rs.getString("bus_number"));
                b.setDriverId(rs.getInt("driver_id"));
                b.setCurrentLocation(rs.getString("current_location"));
                b.setLatitude(rs.getDouble("latitude"));
                b.setLongitude(rs.getDouble("longitude"));
                b.setRoute(rs.getString("route"));
                list.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Get bus by ID
    public Bus getBusById(int busId) {
        Bus bus = null;
        String sql = "SELECT * FROM buses WHERE bus_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, busId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusNumber(rs.getString("bus_number"));
                bus.setDriverId(rs.getInt("driver_id"));
                bus.setCurrentLocation(rs.getString("current_location"));
                bus.setLatitude(rs.getDouble("latitude"));
                bus.setLongitude(rs.getDouble("longitude"));
                bus.setRoute(rs.getString("route"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bus;
    }

    // ✅ Update only the current location (manual driver update)
   public boolean updateBusLocation(int busId, String currentLocation, double latitude, double longitude) {
    boolean updated = false;
    String sql = "UPDATE buses SET current_location=?, latitude=?, longitude=?, last_updated=NOW() WHERE bus_id=?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, currentLocation);
        ps.setDouble(2, latitude);
        ps.setDouble(3, longitude);
        ps.setInt(4, busId);
        updated = ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return updated;
}


    // ✅ Add new bus
    public boolean addBus(Bus bus) {
        boolean added = false;
        String sql = "INSERT INTO buses (bus_number, driver_id, current_location, latitude, longitude, route) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bus.getBusNumber());
            ps.setInt(2, bus.getDriverId());
            ps.setString(3, bus.getCurrentLocation());
            ps.setDouble(4, bus.getLatitude());
            ps.setDouble(5, bus.getLongitude());
            ps.setString(6, bus.getRoute());

            added = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return added;
    }

    // ✅ Update full bus info
    public boolean updateBus(Bus bus) {
        boolean updated = false;
        String sql = "UPDATE buses SET bus_number=?, driver_id=?, current_location=?, latitude=?, longitude=?, route=? WHERE bus_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bus.getBusNumber());
            ps.setInt(2, bus.getDriverId());
            ps.setString(3, bus.getCurrentLocation());
            ps.setDouble(4, bus.getLatitude());
            ps.setDouble(5, bus.getLongitude());
            ps.setString(6, bus.getRoute());
            ps.setInt(7, bus.getBusId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    // ✅ Delete bus
    public boolean deleteBus(int busId) {
        boolean deleted = false;
        String sql = "DELETE FROM buses WHERE bus_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, busId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
  
    
    public Bus getBusByDriverId(int driverId) {
    Bus bus = null;
    String sql = "SELECT * FROM buses WHERE driver_id=?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, driverId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            bus = new Bus();
            bus.setBusId(rs.getInt("bus_id"));
            bus.setBusNumber(rs.getString("bus_number"));
            bus.setDriverId(rs.getInt("driver_id"));
            bus.setCurrentLocation(rs.getString("current_location"));
            bus.setLatitude(rs.getDouble("latitude"));
            bus.setLongitude(rs.getDouble("longitude"));
            bus.setRoute(rs.getString("route"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return bus;
}

    
    
    
    
    
    
}
