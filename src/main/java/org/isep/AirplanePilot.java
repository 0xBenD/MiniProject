package org.isep;

import java.util.ArrayList;

public class AirplanePilot extends Employee{
    public static ArrayList<AirplanePilot> allPilots = new ArrayList<>();

    private String licence;
    private int flightHours;
    private String role = "Airplane Pilot";
    private ArrayList<Flight> assignedFlights = new ArrayList<>();

    public AirplanePilot(String name, String address, String contact, String hiringDate, String licence, int flightHours){
        super(name, address, contact, hiringDate);
        this.licence = licence;
        this.flightHours = flightHours;
        super.setRole(role);
        allPilots.add(this);

    }

    public static ArrayList<AirplanePilot> getAllPilots(){
        return allPilots;
    }

    public String getLicence() {
        return licence;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void assignFlight(Flight flight){
        assignedFlights.add(flight);
    }

    public ArrayList<Flight> obtainFlight(){
        return assignedFlights;
    }
}
