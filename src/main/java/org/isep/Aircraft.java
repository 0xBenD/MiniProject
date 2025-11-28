package org.isep;

import java.util.ArrayList;

public class Aircraft {
    private int capacity;
    private String model;
    private String registration;

    public Aircraft(int capacity, String model, String registration) {
        this.capacity = capacity;
        this.model = model;
        this.registration = registration;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean verifyCapacity(int passengers){
        if (passengers <= capacity) return true;
        else return false;
    }

    public ArrayList<String> affectFlight(Flight flight){
        ArrayList<String> passengers = new ArrayList<>();
        return passengers;
    }
}
