package org.isep;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Aircraft {

    private static ArrayList<Aircraft> fleet = new ArrayList<>();

    private int capacity;
    private String model;
    private String registration;

    private ArrayList<Flight> schedule = new ArrayList<>();

    public Aircraft(int capacity, String model, String registration, boolean isAvailable) {
        this.capacity = capacity;
        this.model = model;
        this.registration = registration;
        this.isAvailable = isAvailable;
        fleet.add(this);
    }

    public boolean checkAvailability(LocalDateTime newDeparture, LocalDateTime newArrival){
        for(Flight f : schedule){
            if(newDeparture.isBefore(f.getArrivaleDate()) && newArrival.isAfter(f.getDepartureDate())){
                return false;
            }
        }
        return true;
    }

    public void assignFlight(Flight flight){
        if(checkAvailability(flight.getDepartureDate(), flight.getArrivaleDate())){
            schedule.add(flight);
            System.out.println("Aircraft "+registration+" has been assigned to flight "+ flight.getFlightNumber());
        }
        else {
            System.out.println("Aircraft is not available");
        }
    }


    public static Aircraft findAircraft(String registration){
        for(Aircraft a : fleet){
            if(a.getRegistration().equalsIgnoreCase(registration)){
                return a;
            }
        }
        return null;
    }

    /*
    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void releaseAircraft(){
        this.isAvailable = true;
    }

    public boolean checkAvailability(){
        return isAvailable;
    }
    */

    public int getCapacity() {
        return capacity;
    }

    public String getRegistration(){
        return registration;
    }
    public String getModel(){
        return model;
    }


}
