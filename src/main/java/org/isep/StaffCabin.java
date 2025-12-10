package org.isep;

import org.isep.FileClasses.WriteToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void addStaffCabin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Staff's full name");
        String name = scanner.nextLine();
        System.out.println("Please enter address");
        String address = scanner.nextLine();
        System.out.println("Please enter contact");
        String contact = scanner.nextLine();
        System.out.println("Please enter Hiring Date");
        String date = scanner.nextLine();
        System.out.println("Please enter Qualification");
        String qualif = scanner.nextLine();

        new StaffCabin(name, address, contact, date, qualif);
        System.out.println("Staff added successfully\n");
    }

    public static void userRemoveStaffCabin() {
        Scanner scanner = new Scanner(System.in);
        String licenceStr = "";
        int idToRemove = 0;
        boolean cond = false;

        for (StaffCabin p : allCabinStaff) System.out.println("Staff: " + p);
        while (!cond) {
            System.out.println("Please enter Employee ID to remove");
            try {
                idToRemove = Integer.parseInt(scanner.nextLine());
                for (StaffCabin s : allCabinStaff) {
                    if (s.getNumberEmp() == idToRemove) cond = true;
                }
                if (!cond) System.out.println("Enter an existing Employee ID");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }

        System.out.println("Removing Staff with ID " + idToRemove);
        StaffCabin toRemove = null;
        for (StaffCabin s : allCabinStaff) {
            if (s.getNumberEmp() == idToRemove) toRemove = s;
        }
        if (toRemove != null) {
            allCabinStaff.remove(toRemove);
            System.out.println("Staff deleted");
            try {
                updateStaffCabinCSV();
            } catch (IOException e) {
            }
        }
    }

    public static void userEditStaffCabin() {
        Scanner scanner = new Scanner(System.in);
        int idToEdit = 0;
        boolean cond = false;
        for (StaffCabin p : allCabinStaff) System.out.println("Staff: " + p);
        while (!cond) {
            System.out.println("Please enter Employee ID to edit");
            try {
                idToEdit = Integer.parseInt(scanner.nextLine());
                for (StaffCabin s : allCabinStaff){
                    if (s.getNumberEmp() == idToEdit) cond = true;
                }
                if (!cond) System.out.println("Enter an existing Employee ID");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }

        StaffCabin staffToEdit = null;
        for (StaffCabin s : allCabinStaff) {
            if (s.getNumberEmp() == idToEdit) staffToEdit = s;
        }
        if (staffToEdit != null) {
            System.out.println("Please enter new Name (current: " + staffToEdit.getName() + ")");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) staffToEdit.setName(newName);
            System.out.println("Please enter new Qualification (current: " + staffToEdit.getQualification() + ")");
            String newQualification = scanner.nextLine();
            if (!newQualification.isEmpty()) staffToEdit.qualification = newQualification;
            System.out.println("Staff edited!");
            try {
                updateStaffCabinCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String toString(){
        return this.getName() + " qualification: " + qualification;
    }
}
