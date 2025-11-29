package org.isep;

import java.util.ArrayList;

public class Airport{
    private String name;
    private String city;
    private String description;
    private ArrayList<Flight> departingFlights = new ArrayList<>();
    private ArrayList<Flight> arrivingFlights = new ArrayList<>();

    public Airport(String name, String city, String description){
        this.name = name;
        this.city = city ;
        this.description = description;
    }

    public void addDepartingFlight(Flight flight){
        departingFlights.add(flight);
    }

    public void addArrivingFlight(Flight flight){
        arrivingFlights.add(flight);
    }

    public String getName(){
        return name;
    }

    public String getCity(){
        return city;
    }
}
