package org.isep;
import org.isep.FileClasses.WriteToFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Aircraft {

    protected static ArrayList<Aircraft> fleet = new ArrayList<>();
    protected static final String PATHNAME = "src/main/resources/aircraft.csv";

    protected int capacity;
    protected String model;
    protected String registration;

    protected ArrayList<Flight> schedule = new ArrayList<>();

    public Aircraft(int capacity, String model, String registration) {
        this.capacity = capacity;
        this.model = model;
        this.registration = registration;
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

    public void releaseAircraft(Flight flight){
        if(schedule.contains(flight)){
            schedule.remove(flight);
            System.out.println("Aircraft "+registration+" has been released");
        }
    }

    public String toCSV(){
        return capacity + "," +model + "," + registration + "\n";
    }
    public static void updateAircraftCSV() throws IOException{
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        fw.close();
        for(Aircraft a : fleet){
            WriteToFile.write(PATHNAME, a.toCSV());
        }
    }
    public static void addAircraft(){
        Scanner scanner = new Scanner(System.in);
        int capacity = 0;
        System.out.println("Enter Aircraft's capacity");
        try {
            capacity = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Invalif number, setting default to 100 ");
            capacity = 100;
        }
        System.out.println("Enter Aircraft's model");
        String model = scanner.nextLine();
        System.out.println("Enter Aircraft's registration");
        String registration = scanner.nextLine();
        new Aircraft(capacity, model, registration);
        System.out.println("Aircraft added successfully\n");
    }
    public static void userRemoveAircraft(){
       Scanner scanner = new Scanner(System.in);
       String registration = "";
       boolean cond = false;
       for(Aircraft a : fleet) System.out.println(a);
       while(!cond) {
           System.out.println("Please enter Aircraft's registration");
           registration = scanner.nextLine();
           for(Aircraft a : fleet) if(a.getRegistration().equalsIgnoreCase(registration)) cond = true;
           if(!cond) System.out.println("Enter an existing Aircraft's registration");
       }
       System.out.println("Removing Aircraft #" + registration);
       deleteAircraft(registration);
    }

    public static void deleteAircraft(String registration){
        Aircraft a = findAircraft(registration);
        if(a != null){
            fleet.remove(a);
            System.out.println("Aircraft deleted");
        }
        else {
            System.out.println("Aircraft not found");
        }
    }

    public static void userEditAircraft(){
        Scanner scanner = new Scanner(System.in);
        String registration = "";
        boolean cond = false;
        for(Aircraft a : fleet) System.out.println(a);
        while(!cond) {
            System.out.println("Please enter Aircraft's registration to edit : ");
            registration = scanner.nextLine();
            for (Aircraft a : fleet) {
                if (a.getRegistration().equalsIgnoreCase(registration)) {
                    cond = true;
                }
            }
            if (!cond) {
                System.out.println("Enter an existing Aircraft's registration");
            }
        }
        Aircraft editAircraft = findAircraft(registration);
        System.out.println("Please enter Aircraft's capacity : ");
        try {
            editAircraft.capacity = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Invalid number, keep old capacity ");
        }
        System.out.println("Please enter Aircraft's model : ");
        String newModel = scanner.nextLine();

        if(!newModel.isEmpty()){
            editAircraft.model = newModel;
        }
        System.out.println("Aircraft edited!");
        try {
            updateAircraftCSV();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getCapacity() {
        return capacity;
    }

    public String getRegistration(){
        return registration;
    }
    public String getModel(){
        return model;
    }

    public static ArrayList<Aircraft> getAllAircraft(){
        return fleet;
    }
    @Override
    public String toString() {
        return "Aircraft" + registration +  " (" + model + " ) , capaciity : " + capacity ;
    }


}
