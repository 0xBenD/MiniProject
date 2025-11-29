package org.isep;

import java.util.ArrayList;

public class Aircraft {
    private int capacity;
    private String model;
    private String registration;
    private boolean isAvailable;

    public Aircraft(int capacity, String model, String registration, boolean isAvailable) {
        this.capacity = capacity;
        this.model = model;
        this.registration = registration;
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
