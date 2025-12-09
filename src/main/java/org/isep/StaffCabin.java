package org.isep;

import org.isep.FileClasses.WriteToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StaffCabin extends Employee{

    public static ArrayList<StaffCabin> allCabinStaff = new ArrayList<>();

    private static final String PATHNAME = "src/main/resources/staffcabin.csv";

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

    public String toCSV() {
        return this.getName() + "," + this.getAddress() + "," + this.getContact() + "," + this.getHiringDate() + "," + qualification + "\n";
    }

    public static void updateStaffCabinCSV() throws IOException {
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        for(StaffCabin a : allCabinStaff) WriteToFile.write(PATHNAME, a.toCSV());
    }

    @Override
    public String toString(){
        return this.getName() + " qualification: " + qualification;
    }
}
