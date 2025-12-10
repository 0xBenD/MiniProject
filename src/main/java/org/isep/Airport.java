package org.isep;

import org.isep.FileClasses.WriteToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Airport{
    private static ArrayList<Airport> allAirports = new ArrayList<>();

    private static final String PATHNAME = "src/main/resources/airport.csv";


    private String name;
    private String city;
    private String description;
    private ArrayList<Flight> departingFlights = new ArrayList<>();
    private ArrayList<Flight> arrivingFlights = new ArrayList<>();

    public Airport(String name, String city, String description){
        this.name = name;
        this.city = city ;
        this.description = description;
        allAirports.add(this);
    }
    public static Airport findAirport(String name){
        for(Airport a : allAirports){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

    public void addDepartingFlight(Flight flight){
        departingFlights.add(flight);
    }

    public void addArrivingFlight(Flight flight){
        arrivingFlights.add(flight);
    }

    public String getName(){
        return name;
    }

    public String getCity(){
        return city;
    }

    public String toCSV() {
        return this.getName() + "," + city + "," + description + "\n";
    }


    public static ArrayList<Airport> getAllAirports(){
        return allAirports;
    }

    public static void updateAirportCSV() throws IOException {
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        fw.close();
        for (Airport a : allAirports) WriteToFile.write(PATHNAME, a.toCSV());
    }

    public static void addAirport(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Airport Name");
        String name = scanner.nextLine();
        System.out.println("Please enter City");
        String city = scanner.nextLine();
        System.out.println("Please enter Description");
        String desc = scanner.nextLine();

        new Airport(name, city, desc);
        System.out.println("Airport added successfully\n");
    }

    public static void userRemoveAirport(){
        Scanner scanner = new Scanner(System.in);
        String nameStr = "";
        boolean cond = false;
        System.out.println("--- List of Airports ---");
        for(Airport a : allAirports) {
            System.out.println(a.getName() + " (" + a.getCity() + ")");
        }

        while(!cond) {
            System.out.println("Please enter Airport Name to remove");
            nameStr = scanner.nextLine();
            // On vérifie si le nom existe
            for(Airport a : allAirports) {
                if(a.getName().equalsIgnoreCase(nameStr)) cond = true;
            }
            if(!cond) System.out.println("Enter an existing Airport Name");
        }

        System.out.println("Removing Airport " + nameStr);
        Airport toRemove = findAirport(nameStr);

        if(toRemove != null) {
            allAirports.remove(toRemove);
            System.out.println("Airport deleted");
            try {
                updateAirportCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void userEditAirport() {
        Scanner scanner = new Scanner(System.in);
        String nameStr = "";
        boolean cond = false;

        // Affichage de la liste
        for (Airport a : allAirports) {
            System.out.println(a.getName() + " (" + a.getCity() + ")");
        }

        while (!cond) {
            System.out.println("Please enter Airport Name to edit");
            nameStr = scanner.nextLine();
            for (Airport a : allAirports) {
                if (a.getName().equalsIgnoreCase(nameStr)) cond = true;
            }
            if (!cond) System.out.println("Enter an existing Airport Name");
        }

        // On récupère l'objet
        Airport airport = findAirport(nameStr);

        if (airport != null) {
            // Modification de la Ville
            System.out.println("Please enter new City (current: " + airport.getCity() + ")");
            String newCity = scanner.nextLine();
            if (!newCity.isEmpty()) airport.city = newCity;

            // Modification de la Description
            // Note: getCity() existe mais pas getDescription() dans votre code initial,
            // donc j'accède directement à airport.description
            System.out.println("Please enter new Description (current: " + airport.description + ")");
            String newDesc = scanner.nextLine();
            if (!newDesc.isEmpty()) airport.description = newDesc;

            System.out.println("Airport edited!");
            try {
                updateAirportCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString(){
        return name + " " + city + " " + description;
    }
}
