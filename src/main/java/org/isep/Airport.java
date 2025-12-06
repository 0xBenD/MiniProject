package org.isep;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Airport{
    private static ArrayList<Airport> allAirports = new ArrayList<>();

    private static final String PATHNAME = "src/main/resources/airport.csv";


    private String name;
    private String city;
    private String description;
    private ArrayList<Flight> departingFlights = new ArrayList<>();
    private ArrayList<Flight> arrivingFlights = new ArrayList<>();

    public Airport(String name, String city, String description){
        this.name = name;
        this.city = city ;
        this.description = description;
        allAirports.add(this);
    }
    public static Airport findAirport(String name){
        for(Airport a : allAirports){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
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

    public String toCSV() {
        return this.getName() + "," + city + "," + description + "\n";
    }

    public static void updateAirportCSV() throws IOException {
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        for (Airport a : allAirports) WriteToFile.write(PATHNAME, a.toCSV());
    }

    public static ArrayList<Airport> getAllAirports(){
        return allAirports;
    }

    @Override
    public String toString(){
        return name + " " + city + " " + description;
    }
}
