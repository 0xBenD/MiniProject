package org.isep;
import org.isep.FileClasses.WriteToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;


public class Flight {
    private static ArrayList<Flight> allFlights = new ArrayList<>();

    private static final String PATHNAME = "src/main/resources/flight.csv";

    private int flightNumber;
    private Airport origine;
    private Airport destination;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private FlightStatus status;
    private Aircraft aircraft;
    private ArrayList<Passenger> passengerList = new ArrayList<>();
    private AirplanePilot pilot;

    private ArrayList<StaffCabin> cabinCrew = new ArrayList<>();

    public Flight(int flightNumber, Airport origine, Airport destination, LocalDateTime departureDate,LocalDateTime arrivalDate){
        this.flightNumber = flightNumber;
        this.origine = origine;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.status = FlightStatus.ON_TIME;
        origine.addDepartingFlight(this);
        destination.addArrivingFlight(this);
        allFlights.add(this);
    }

    public static Flight scheduleFlight(int number,Airport origine, Airport destination, String departureTime, String arrivalTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime departureDate = LocalDateTime.parse(departureTime, formatter);
        LocalDateTime arrivalDate = LocalDateTime.parse(arrivalTime, formatter);
        return new Flight(number, origine, destination, departureDate, arrivalDate);
    }

    public static LocalDateTime StringtoDate(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime date = LocalDateTime.parse(time, formatter);
        return date;
    }


    public void assignPilot(AirplanePilot pilot){
        this.pilot = pilot;
        pilot.assignFlight(this);
    }

    public void assignCabin(StaffCabin cabinMember){
        this.cabinCrew.add(cabinMember);
        cabinMember.assignFlight(this);
    }

    public boolean addPassenger(Passenger passenger){
        if(aircraft != null && passengerList.size() < aircraft.getCapacity()){
            passengerList.add(passenger);
            return true;
        }
        else {
            System.out.println("Flight is full or no aircraft assigned");
            return false;
        }
    }

    public int getFlightNumber(){
        return flightNumber;
    }

    public void listPassenger(){
        System.out.println("Passenger List for Flight "+ flightNumber + " : ");
        for(Passenger p : passengerList){
            p.getInfos();
        }
    }

    public void assignAircraft(Aircraft aircraft){
        this.aircraft = aircraft;
        aircraft.assignFlight(this);
    }


    public void cancelFlight(){
        this.status = FlightStatus.CANCELED;
        if(this.aircraft != null){
            this.aircraft.releaseAircraft(this);
        }
        System.out.println("Flight " + flightNumber + " has been canceled");
    }

    public static Flight findFlight(int flightNumber){
        for(Flight f : allFlights){
            if(f.getFlightNumber() == flightNumber){
                return f;
            }
        }
        return null;
    }

    public static void addFlight(){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        Airport destinationAirport = null;
        Airport originAirport = null;
        LocalDateTime departureDate;
        LocalDateTime arrivalDate;
        boolean nbCorrect = false;
        do{
            System.out.println("Please enter Flight's number");
            try{
                number = Integer.parseInt(scanner.nextLine());
                nbCorrect = true;
                for(Flight f : allFlights){
                    if(f.getFlightNumber() == number){
                        nbCorrect = false;
                        System.out.println("Flight number must be new!");
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a correct number!");
            }
        }while(!nbCorrect);
        boolean originCorrect = false;
        do {
            System.out.println("Please enter Flight's origin");
            String origin = scanner.nextLine();
            for(Airport airport : Airport.getAllAirports()) {
                if(Objects.equals(airport.getName(), origin)) {
                    originCorrect = true;
                    originAirport = airport;
                }
            }
        } while(!originCorrect);
        boolean destinationCorrect = false;
        do {
            System.out.println("Please enter Flight's destination");
            String destination = scanner.nextLine();
            for(Airport airport : Airport.getAllAirports()) {
                if(Objects.equals(airport.getName(), destination)) {
                    destinationCorrect = true;
                    destinationAirport = airport;
                }
            }
        } while(!destinationCorrect);
        while(true) {
            System.out.println("Please enter Flight's departure time");
            try {
                String time = scanner.nextLine();
                departureDate = StringtoDate(time);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Correct format is yyyy-MM-ddTHH:mm");
            }
        }
        while(true) {
            System.out.println("Please enter Flight's arrival time");
            try {
                String time = scanner.nextLine();
                arrivalDate = StringtoDate(time);
                if(arrivalDate.isAfter(departureDate)) break;
                else System.out.println("Arrival time must be after departure time");
            } catch (DateTimeParseException e) {
                System.out.println("Correct format is yyyy-MM-ddTHH:mm");
            }
        }
        new Flight(number, originAirport, destinationAirport, departureDate, arrivalDate);
        System.out.println("Flight added successfully\n");

    }

    public static void userRemoveFlight(){
        Scanner scanner = new Scanner(System.in);
        int ID = 0;
        boolean cond = false;
        for(Flight p : allFlights) System.out.println(p);
        while(!cond) {
            System.out.println("Please enter Flight's number");
            try {
                ID = Integer.parseInt(scanner.nextLine());
                for(Flight p : allFlights) if(p.getFlightNumber() == ID) cond = true;
                if(!cond) System.out.println("Enter an existing Flight number");
            } catch (NumberFormatException e) {
                System.out.println("Enter a correct number");
            }
        }
        System.out.println("Removing Flight #" + ID);
        deleteFlight(ID);
    }

    public static void deleteFlight(int flightNumber){
        Flight f = findFlight(flightNumber);
        if(f != null){
            allFlights.remove(f);
            System.out.println("Flight deleted");
        }
        else {
            System.out.println("Flight not found");
        }
    }

    public static void userEditFlight() {
        Scanner scanner = new Scanner(System.in);
        int ID = 0;
        boolean cond = false;
        for (Flight p : allFlights) System.out.println(p);
        while (!cond) {
            System.out.println("Please enter Flight's number");
            try {
                ID = Integer.parseInt(scanner.nextLine());
                for (Flight p : allFlights) if (p.getFlightNumber() == ID) cond = true;
                if (!cond) System.out.println("Enter an existing Flight number");
            } catch (NumberFormatException e) {
                System.out.println("Enter a correct number");
            }
        }
        Flight editFlight = findFlight(ID);
        Airport destinationAirport = null;
        Airport originAirport = null;
        LocalDateTime departureDate;
        LocalDateTime arrivalDate;
        boolean originCorrect = false;
        do {
            System.out.println("Please enter Flight's origin");
            String origin = scanner.nextLine();
            for(Airport airport : Airport.getAllAirports()) {
                if(Objects.equals(airport.getName(), origin)) {
                    originCorrect = true;
                    originAirport = airport;
                }
            }
        } while(!originCorrect);
        boolean destinationCorrect = false;
        do {
            System.out.println("Please enter Flight's destination");
            String destination = scanner.nextLine();
            for(Airport airport : Airport.getAllAirports()) {
                if(Objects.equals(airport.getName(), destination)) {
                    destinationCorrect = true;
                    destinationAirport = airport;
                }
            }
        } while(!destinationCorrect);
        while(true) {
            System.out.println("Please enter Flight's departure time");
            try {
                String time = scanner.nextLine();
                departureDate = StringtoDate(time);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Correct format is yyyy-MM-ddTHH:mm");
            }
        }
        while(true) {
            System.out.println("Please enter Flight's arrival time");
            try {
                String time = scanner.nextLine();
                arrivalDate = StringtoDate(time);
                if(arrivalDate.isAfter(departureDate)) break;
                else System.out.println("Arrival time must be after departure time");
            } catch (DateTimeParseException e) {
                System.out.println("Correct format is yyyy-MM-ddTHH:mm");
            }
        }
        System.out.println("Editing flight");
        editFlight.origine = originAirport;
        editFlight.destination = destinationAirport;
        editFlight.rescheduleFlight(departureDate,arrivalDate);
        System.out.println("Flight edited!");
    }

    public void updateStatusRandomly(){
        if(this.status == FlightStatus.CANCELED){
            return;
        }
        Random random = new Random();
        int randomInt = random.nextInt(100);
        if(randomInt < 5){
            this.status = FlightStatus.DELAYED;
        }
        else if(this.status != FlightStatus.DELAYED){
            this.status = FlightStatus.ON_TIME;
        }
    }

    public void updateStatus(LocalDateTime currentTime){
        if(this.status == FlightStatus.CANCELED){
            return;
        }
        if(currentTime.isAfter(this.arrivalDate)) {
            return;
        }
        if(currentTime.isAfter(this.departureDate) && currentTime.isBefore(this.arrivalDate)){
            if(this.status != FlightStatus.DELAYED) {
                this.status = FlightStatus.DEPARTURE;
            }
        }
        else if(currentTime.isAfter(this.departureDate.minusMinutes(30)) && currentTime.isBefore(this.departureDate)){
            if(this.status != FlightStatus.DELAYED) {
                this.status = FlightStatus.BOARDING;
            }
        }
        if(currentTime.isBefore(this.departureDate)){
            updateStatusRandomly();
        }
    }

    public void rescheduleFlight(LocalDateTime newDeparture, LocalDateTime newArrival){
        this.departureDate = newDeparture;
        this.arrivalDate = newArrival;
        System.out.println("Flight " + flightNumber + " has been rescheduled");
        if(this.aircraft != null){
            System.out.println("Check if aircraft " + aircraft.getRegistration() + " is still available for this new dates.");
        }
    }

    @Override
    public String toString() {
        return flightNumber + " origin: " + origine + " destination: " + destination + " departure date: " + departureDate.toString() + " arrival date: " + arrivalDate.toString() ;
    }

    public void setStatus(FlightStatus status){
        this.status = status;
    }

    public String toCSV() {
        return flightNumber + "," + origine.getName() + "," + destination.getName() + "," + departureDate.toString() + "," + arrivalDate.toString() + "," + status + "\n";
    }

    public static void updateFlightCSV() throws IOException {
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        for(Flight a : allFlights) WriteToFile.write(PATHNAME, a.toCSV());
    }
    public static ArrayList<Flight> getAllFlights(){
        return allFlights;
    }

    public Airport getOrigine(){
        return origine;
    }
    public Airport getDestination(){
        return destination;
    }
    public LocalDateTime getDepartureDate(){
        return departureDate;
    }
    public LocalDateTime getArrivaleDate(){
        return arrivalDate;
    }
    public FlightStatus getStatus(){
        return status;
    }

}
