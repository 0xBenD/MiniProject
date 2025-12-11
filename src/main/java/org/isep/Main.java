package org.isep;
import org.isep.FileClasses.FileReaderWithBufferedReader;

import java.io.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class Main {

    public static LocalDateTime simulationTime = LocalDateTime.of(2025,11,10,9,0 );

    public static void main(String[] args) throws IOException {
        /*
        readAlldata();
        testReadData();
        assignAircraftTest();
        simulation(100);
        //Flight.addFlight();
        Passenger.userRemovePassenger();
        updateAllData();
        updateFlightStatus();
        */

        readAlldata();
        assignAircraftTest();
        interractiveMenu();

        for(Book book : Book.getAllBooks()) System.out.println(book);
        displayStats();
    }

    public static void interractiveMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("#######################################################################################################################################################################################################");
        System.out.println("                                                                             AIRLINE MANAGEMENT SYSTEM CONSOLE"                                                                                         );
        System.out.println("#######################################################################################################################################################################################################");
        while(running){
            System.out.println("\n -- Main Menu -- \n");
            System.out.println("1.  Manage Passengers (Add/Remove/Edit)");
            System.out.println("2.  Manage Flights (Add/Remove/Edit)");
            System.out.println("3.  Manage Aircrafts & Airports");
            System.out.println("4.  Manage Staff (Pilots & Cabin)");
            System.out.println("5.  SIMULATION: Advance Time (Update Status)");
            System.out.println("6.  SIMULATION: Re-Assign Aircrafts (Random)");
            System.out.println("7.  Display Global Statistics");
            System.out.println("8.  Launch SIMULATION");
            System.out.println("9.  Display Flights' Status");
            System.out.println("0.  Save Data & Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice) {
                case "1":
                    System.out.println("\n--- PASSENGERS ---");
                    System.out.println("1. Add Passenger");
                    System.out.println("2. Remove Passenger");
                    System.out.println("3. Edit Passenger");
                    System.out.println("4. List All Passengers");
                    System.out.print("Choice: ");
                    String pChoice = scanner.nextLine();
                    if (pChoice.equals("1")) Passenger.addPassenger();
                    else if (pChoice.equals("2")) Passenger.userRemovePassenger();
                    else if (pChoice.equals("3")) Passenger.userEditPassenger();
                    else if (pChoice.equals("4")) Passenger.listAllPassengers();
                    break;


                case "2":
                    System.out.println("\n--- FLIGHTS ---");
                    System.out.println("1. Add Flight");
                    System.out.println("2. Remove Flight");
                    System.out.println("3. Edit Flight");
                    System.out.println("4. List All Flights");
                    System.out.print("Choice: ");
                    String fChoice = scanner.nextLine();
                    if(fChoice.equals("1")) Flight.addFlight();
                    else if(fChoice.equals("2")) Flight.userRemoveFlight();
                    else if(fChoice.equals("3")) Flight.userEditFlight();
                    else if(fChoice.equals("4")) {
                        for(Flight f : Flight.getAllFlights()) System.out.println(f);
                    }
                    break;

                case "3":
                    System.out.println("\n--- INFRASTRUCTURE ---");
                    System.out.println("1. Add Aircraft");
                    System.out.println("2. Remove Aircraft");
                    System.out.println("3. Edit Aircraft");
                    System.out.println("4. Add Airport");
                    System.out.println("5. Remove Airport");
                    System.out.print("Choice: ");
                    String iChoice = scanner.nextLine();
                    if(iChoice.equals("1")) Aircraft.addAircraft();
                    else if(iChoice.equals("2")) Aircraft.userRemoveAircraft();
                    else if(iChoice.equals("3")) Aircraft.userEditAircraft();
                    else if(iChoice.equals("4")) Airport.addAirport();
                    else if(iChoice.equals("5")) Airport.userRemoveAirport();
                    break;

                case "4":
                    System.out.println("\n--- STAFF ---");
                    System.out.println("1. Add Pilot");
                    System.out.println("2. Remove Pilot");
                    System.out.println("3. Edit Pilot");
                    System.out.println("4. Add Cabin Staff");
                    System.out.println("5. Remove Cabin Staff");
                    System.out.print("Choice: ");
                    String sChoice = scanner.nextLine();
                    if(sChoice.equals("1")) AirplanePilot.addPilot();
                    else if(sChoice.equals("2")) AirplanePilot.userRemovePilot();
                    else if(sChoice.equals("3")) AirplanePilot.userEditPilot();
                    else if(sChoice.equals("4")) StaffCabin.addStaffCabin();
                    else if(sChoice.equals("5")) StaffCabin.userRemoveStaffCabin();
                    break;

                case "5":
                    System.out.println("How many minutes do you want to advance?");
                    try {
                        int minutes = Integer.parseInt(scanner.nextLine());
                        userAdvanceTime(minutes);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                    }
                    break;

                case "6":
                    assignAircraftTest();
                    break;

                case "7":
                    for(Book book : Book.getAllBooks()) System.out.println(book);
                    displayStats();
                    break;

                case "8" :
                    int nb = 0;
                    do {
                        System.out.println("How many simulation rounds?");
                        try{
                            nb = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch (NumberFormatException e){
                            System.out.println("Please enter an Integer");
                        }
                    }while(true);
                    simulation(abs(nb));
                    break;

                case "9":
                    for(Flight f : Flight.getAllFlights()) System.out.println("flight" + f.getFlightNumber() + " is " + f.getStatus());

                case "0":
                    System.out.println("Saving data...");
                    try {
                        updateAllData();
                        System.out.println("Data saved successfully.");
                    } catch (IOException e) {
                        System.out.println("(!) Error while saving data: " + e.getMessage());
                    }
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");

            }
        }
    }

    public static void readAlldata() throws IOException {

        ArrayList<String> dataPassenger = FileReaderWithBufferedReader.readCSV("src/main/resources/passenger.csv", new ArrayList<String>());
        for (int i = 0; i < dataPassenger.size(); i += 4) new Passenger(dataPassenger.get(i), dataPassenger.get(i + 1), dataPassenger.get(i + 2), dataPassenger.get(i+3));

        ArrayList<String> dataStaffCabin = FileReaderWithBufferedReader.readCSV("src/main/resources/staffcabin.csv", new ArrayList<String>());
        for (int i = 0; i < dataStaffCabin.size(); i += 5) new StaffCabin(dataStaffCabin.get(i), dataStaffCabin.get(i + 1), dataStaffCabin.get(i + 2), dataStaffCabin.get(i+3), dataStaffCabin.get(i+4));

        ArrayList<String> dataPilot = FileReaderWithBufferedReader.readCSV("src/main/resources/pilot.csv", new ArrayList<String>());
        for (int i = 0; i < dataPilot.size(); i += 6) new AirplanePilot(dataPilot.get(i), dataPilot.get(i + 1), dataPilot.get(i + 2), dataPilot.get(i+3), dataPilot.get(i+4), parseInt(dataPilot.get(i+5)));

        ArrayList<String> dataAirport = FileReaderWithBufferedReader.readCSV("src/main/resources/airport.csv", new ArrayList<String>());
        for (int i = 0; i < dataAirport.size(); i += 3) new Airport(dataAirport.get(i), dataAirport.get(i + 1), dataAirport.get(i + 2));

        ArrayList<String> dataFlight = FileReaderWithBufferedReader.readCSV("src/main/resources/flight.csv", new ArrayList<String>());
        for (int i = 0; i < dataFlight.size(); i += 6) {
            Flight f = new Flight(parseInt(dataFlight.get(i)),
                    Airport.findAirport(dataFlight.get(i + 1)),
                    Airport.findAirport(dataFlight.get(i + 2)),
                    Flight.StringtoDate(dataFlight.get(i + 3)),
                    Flight.StringtoDate(dataFlight.get(i + 4))
            );
            try {
                FlightStatus savedStatus = FlightStatus.valueOf(dataFlight.get(i + 5));
                f.setStatus(savedStatus);
            } catch (IllegalArgumentException e) {
                f.setStatus(FlightStatus.ON_TIME);
            }
        }

        ArrayList<String> dataAircraft = FileReaderWithBufferedReader.readCSV("src/main/resources/aircraft.csv", new ArrayList<String>());
        for (int i = 0; i < dataAircraft.size(); i += 3) new Aircraft(parseInt(dataAircraft.get(i)), dataAircraft.get(i + 1), dataAircraft.get(i + 2));
        for(Flight f : Flight.getAllFlights()){
            f.updateStatus(simulationTime);
        }

    }

    public static void testReadData(){
        System.out.println("\nTest for all Airports\n");
        for (Airport a : Airport.getAllAirports()) System.out.println(a);

        System.out.println("\nTest for all flights\n");
        for (Flight a : Flight.getAllFlights()) System.out.println(a);

        System.out.println("\nTest for all staff\n");
        for (StaffCabin a : StaffCabin.getAllCabinStaff()) System.out.println(a);

        System.out.println("\nTest for all pilots\n");
        for (AirplanePilot a : AirplanePilot.getAllPilots()) System.out.println(a);

        System.out.println("\nTest for all passengers\n");
        for (Passenger a : Passenger.getAllPassengers()) System.out.println(a);
    }



    public static void updateAllData() throws IOException {
        System.out.println("\nUpdating Airports' info\n");
        Airport.updateAirportCSV();

        System.out.println("\nUpdating Flights' info\n");
        Flight.updateFlightCSV();

        System.out.println("\nUpdating Staff's info\n");
        StaffCabin.updateStaffCabinCSV();

        System.out.println("\nUpdating Pilots' info\n");
        AirplanePilot.updatePilotCSV();

        System.out.println("\nUpdating Passengers' info\n");
        Passenger.updatePassengerCSV();
    }

    public static void assignAircraftTest() {
        System.out.println("Assigning aircraft randomly");
        ArrayList<Aircraft> fleetCopy = new ArrayList<>(Aircraft.getAllAircraft());
        for (Flight f : Flight.getAllFlights()) {
            Collections.shuffle(fleetCopy);
            boolean assigned = false;
            for (Aircraft a : fleetCopy) {
                if (a.checkAvailability(f.getDepartureDate(), f.getArrivaleDate())) {
                    a.assignFlight(f);
                    //f.assignAircraft(a);
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                System.out.println("No aircraft available for flight " + f.getFlightNumber());
            }
            System.out.println("Aircraft assignement process finished");
        }

        /*
        for(Flight f : Flight.getAllFlights()){
            for(Aircraft a : Aircraft.getAllAircraft()){
                if(a.checkAvailability(f.getDepartureDate(),f.getArrivaleDate())){
                    a.assignFlight(f);
                    break;
                }
            }
        }
        */

    }
    public static void userAdvanceTime(int minutes){
        simulationTime = simulationTime.plusMinutes(minutes);
        System.out.println("New time: " + simulationTime+ "\n");
        for(Flight f : Flight.getAllFlights()){
            f.updateStatus(simulationTime);
        }
        for(Flight f : Flight.getAllFlights()){
            if(f.getDepartureDate().toLocalDate().isBefore(simulationTime.toLocalDate())){
                System.out.println("flight" + f.getFlightNumber() + " is " + f.getStatus());
            }
        }
    }

    public static void simulation(int nb){
        Random randInt = new Random();
        for(int i = 0; i < nb; i++) {
            Passenger tempPassenger = Passenger.getAllPassengers().get(randInt.nextInt(Passenger.getAllPassengers().size()-1));
            Flight tempFlight = Flight.getAllFlights().get(randInt.nextInt(Flight.getAllFlights().size()-1));
            tempPassenger.bookFlight(tempFlight,tempFlight.getDepartureDate().toString());
        }
    }

    public static void updateFlightStatus(){
        System.out.println("Updating flight status");
        for(Flight f : Flight.getAllFlights()){
            f.updateStatusRandomly();
        }
        System.out.println("Flight status updated");
    }

    public static void displayStats(){
        HashMap<Airport,Integer> destinationData = new HashMap<>();
        HashMap<Airport,Integer> departureData = new HashMap<>();
        for(Airport airport : Airport.getAllAirports()){
            destinationData.put(airport,0);
            departureData.put(airport,0);
        }
        for(Book book : Book.getAllBooks()){
            if (book.getFlight() != null) {
                destinationData.put(book.getFlight().getDestination(), destinationData.get(book.getFlight().getDestination()) + 1);
                departureData.put(book.getFlight().getOrigine(), departureData.get(book.getFlight().getOrigine()) + 1);
            }
        }
        HashMap<Integer,Airport> popularDestination = new HashMap<>();
        HashMap<Integer,Airport> fledDepartures = new HashMap<>();
        ArrayList<Integer> sortDestination = new ArrayList<>();
        ArrayList<Integer> sortDeparture = new ArrayList<>();
        for(Airport airport : Airport.getAllAirports()){
            int tempNbDestination = destinationData.get(airport);
            if(!popularDestination.containsKey(tempNbDestination)) sortDestination.add(tempNbDestination);
            popularDestination.put(tempNbDestination,airport);
            int tempNbDeparture = departureData.get(airport);
            if(!fledDepartures.containsKey(tempNbDeparture)) sortDeparture.add(tempNbDeparture);
            fledDepartures.put(tempNbDeparture,airport);
        }
        sortDestination.sort(null);
        sortDeparture.sort(null);
        System.out.println("Most popular destinations : \n");
        for (int i = sortDestination.size()-1 ; i >= 0; i--) System.out.println(popularDestination.get(sortDestination.get(i))+ ": " + sortDestination.get(i) + " tourists");
        System.out.println("\nMost fled departures : \n");
        for (int i = sortDeparture.size()-1 ; i >= 0; i--) System.out.println(fledDepartures.get(sortDeparture.get(i))+ ": " + sortDeparture.get(i) + " tourists");
    }

}

// DON'T FORGET TO ADD FLIGHT STATUS
// NEED A GETTER FOR ALL CSV eg. getAllFlights