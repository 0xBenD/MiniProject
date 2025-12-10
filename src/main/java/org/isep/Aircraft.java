package org.isep;
import org.isep.FileClasses.WriteToFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Aircraft {

    private static ArrayList<Aircraft> fleet = new ArrayList<>();
    private static final String PATHNAME = "src/main/resources/aircraft.csv";

    private int capacity;
    private String model;
    private String registration;

    private ArrayList<Flight> schedule = new ArrayList<>();

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
        for(Aircraft a : fleet){
            WriteToFile.write(PATHNAME, a.toCSV());
        }
    }
    public static void addAircraft(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Aircraft's capacity : ");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter Aircraft's model : ");
        String model = scanner.nextLine();
        System.out.println("Please enter Aircraft's registration : ");
        String registration = scanner.nextLine();
        new Aircraft(capacity, model, registration);
        System.out.println("Aircraft added successfully\n");
        try{
            updateAircraftCSV();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void userRemoveAircraft(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Registration to Remove : ");
        String registration = scanner.nextLine();
        Aircraft a = findAircraft(registration);
        if(a != null){
            fleet.remove(a);
            System.out.println("Aircraft removed");
            try{
                updateAircraftCSV();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Aircraft not found");
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


}
