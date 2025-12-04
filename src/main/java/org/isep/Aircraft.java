package org.isep;

import java.util.ArrayList;

public class Aircraft {

    private static ArrayList<Aircraft> fleet = new ArrayList<>();

    private int capacity;
    private String model;
    private String registration;
    private boolean isAvailable;

    public Aircraft(int capacity, String model, String registration, boolean isAvailable) {
        this.capacity = capacity;
        this.model = model;
        this.registration = registration;
        this.isAvailable = isAvailable;
        fleet.add(this);
    }

    public static Aircraft findAircraft(String registration){
        for(Aircraft a : fleet){
            if(a.getRegistration().equals(registration)){
                return a;
            }
        }
        return null;
    }

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void assignFlight(Flight flight){
        if (checkAvailability()) {
            this.isAvailable = false;
            System.out.println("Aircraft" + registration + "assigned to flight");
        }
        else {
            System.out.println("this Aircraft is not available");
        }
    }
    public void releaseAircraft(){
        this.isAvailable = true;
    }

    public int getCapacity() {
        return capacity;
    }
    public boolean checkAvailability(){
        return isAvailable;
    }
    public String getRegistration(){
        return registration;
    }
    public String getModel(){
        return model;
    }


}
