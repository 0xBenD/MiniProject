package org.isep;

import java.util.ArrayList;

public class StaffCabin extends Employee{

    public static ArrayList<StaffCabin> allCabinStaff = new ArrayList<>();

    private String qualification;
    private String role = "Staff Cabin";
    private ArrayList<Flight> assignedFlights = new ArrayList<>();

    public StaffCabin(String name, String address, String contact, String hiringDate, String qualification){
        super(name, address, contact, hiringDate);
        this.qualification = qualification;
        super.setRole(role);
        allCabinStaff.add(this);
    }

    public static ArrayList<StaffCabin> getAllCabinStaff(){
        return allCabinStaff;
    }


    public String getQualification() {
        return qualification;
    }

    public void assignFlight(Flight flight){
        assignedFlights.add(flight);
    }

    public ArrayList<Flight> obtainFlight(){
        return assignedFlights;
    }
}
