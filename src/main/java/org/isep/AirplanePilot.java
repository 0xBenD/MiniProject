package org.isep;

import org.isep.FileClasses.WriteToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public static void addPilot(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Pilot's full name");
        String name = scanner.nextLine();
        System.out.println("Please enter Pilot's address : ");
        String address = scanner.nextLine();
        System.out.println("Please enter Pilot's contact : ");
        String contact = scanner.nextLine();
        System.out.println("Please enter Pilot's hiring date (yyyy-MM-dd) : ");
        String hiringDate = scanner.nextLine();
        System.out.println("Please enter Pilot's License number : ");
        String licence = scanner.nextLine();
        System.out.println("Please enter Pilot's flight hours : ");
        int flightHours = 0;
        try {
            flightHours = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Invalif number, setting default to 0 ");
            flightHours = 0;
        }
        new AirplanePilot(name,address,contact,hiringDate,licence,flightHours);
        System.out.println("Pilot added successfully\n");
    }
    public static void userEditPilot(){
        Scanner scanner = new Scanner(System.in);
        String licenceStr = "";
        boolean cond = false;
        for (AirplanePilot p : allPilots) System.out.println(p);
        while (!cond) {
            System.out.println("Please enter Pilot's Licence to edit");
            licenceStr = scanner.nextLine();
            // On vérifie si une licence correspond dans la liste
            for (AirplanePilot p : allPilots) {
                if (p.getLicence().equalsIgnoreCase(licenceStr)) cond = true;
            }
            if (!cond) System.out.println("Enter an existing Licence number");
        }

        AirplanePilot pilot = null;
        for(AirplanePilot p : allPilots) {
            if(p.getLicence().equalsIgnoreCase(licenceStr)) pilot = p;
        }

        if(pilot != null){
            System.out.println("Please enter new Name (current: " + pilot.getName() + ")");
            String newName = scanner.nextLine();
            if(!newName.isEmpty()) pilot.setName(newName);
            System.out.println("Please enter new Flight Hours (current: " + pilot.getFlightHours() + ")");
            try {
                pilot.flightHours = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, keeping old hours");
            }

            System.out.println("Pilot edited!");
            try {
                updatePilotCSV(); // Sauvegarde dans le fichier
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void userRemovePilot(){
        Scanner scanner = new Scanner(System.in);
        String licenceStr = "";
        boolean cond = false;
        for(AirplanePilot p : allPilots) System.out.println(p);
        while(!cond) {
            System.out.println("Please enter Pilot's Licence to remove");
            licenceStr = scanner.nextLine();
            for(AirplanePilot p : allPilots) {
                if(p.getLicence().equalsIgnoreCase(licenceStr)) cond = true;
            }
            if(!cond) System.out.println("Enter an existing Licence number");
        }
        System.out.println("Removing Pilot with licence " + licenceStr);
        AirplanePilot toRemove = null;
        for(AirplanePilot p : allPilots) {
            if(p.getLicence().equalsIgnoreCase(licenceStr)) toRemove = p;
        }
        if(toRemove != null) {
            allPilots.remove(toRemove);
            System.out.println("Pilot deleted");
            try {
                updatePilotCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Pilot not found");
        }
    }
    @Override
    public String toString(){
        return this.getName() + " " + " licence:" + licence + " flight hours: " + flightHours;
    }
}
