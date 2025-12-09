package org.isep;

import org.isep.FileClasses.WriteToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AirplanePilot extends Employee{
    public static ArrayList<AirplanePilot> allPilots = new ArrayList<>();

    private static final String PATHNAME = "src/main/resources/pilot.csv";

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

    public String toCSV() {
        return this.getName() + "," + this.getAddress() + "," + this.getContact() + "," + this.getHiringDate() + "," + licence + "," + flightHours + "\n";
    }

    public static void updatePilotCSV() throws IOException {
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        for(AirplanePilot a : allPilots) WriteToFile.write(PATHNAME, a.toCSV());
    }

    @Override
    public String toString(){
        return this.getName() + " " + " licence:" + licence + " flight hours: " + flightHours;
    }
}
