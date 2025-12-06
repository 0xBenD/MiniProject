package org.isep;
import java.io.*;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {

        readAlldata();
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

    }
}

// DON'T FORGET TO ADD FLIGHT STATUS
// NEED A GETTER FOR ALL CSV eg. getAllFlights