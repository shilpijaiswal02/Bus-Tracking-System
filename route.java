/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bustrackingsystem.modelclass;

/**
 *
 * @author shilpi
 */
//package com.bustrackingsystem.model;

public class route {
    private int routeId;
    private String source;
    private String destination;
    private String stops;

    // Getters and Setters
    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getStops() { return stops; }
    public void setStops(String stops) { this.stops = stops; }
}

