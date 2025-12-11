package org.isep;
import org.isep.FileClasses.FileReaderWithBufferedReader;

import java.io.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        readAlldata();
        testReadData();
        assignAircraftTest();
        simulation(100);
        //Flight.addFlight();
        Passenger.userRemovePassenger();
        updateAllData();
        updateFlightStatus();

        for(Book book : Book.getAllBooks()) System.out.println(book);
        displayStats();
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
        for (int i = 0; i < dataFlight.size(); i += 5) new Flight(parseInt(dataFlight.get(i)), Airport.findAirport(dataFlight.get(i + 1)), Airport.findAirport(dataFlight.get(i + 2)), Flight.StringtoDate(dataFlight.get(i + 3)), Flight.StringtoDate(dataFlight.get(i + 4)));

        ArrayList<String> dataAircraft = FileReaderWithBufferedReader.readCSV("src/main/resources/aircraft.csv", new ArrayList<String>());
        for (int i = 0; i < dataAircraft.size(); i += 3) new Aircraft(parseInt(dataAircraft.get(i)), dataAircraft.get(i + 1), dataAircraft.get(i + 2));

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

    public static void assignAircraftTest(){
        for(Flight f : Flight.getAllFlights()){
            for(Aircraft a : Aircraft.getAllAircraft()){
                if(a.checkAvailability(f.getDepartureDate(),f.getArrivaleDate())){
                    a.assignFlight(f);
                    break;
                }
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