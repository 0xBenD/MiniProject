package org.isep;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime departureDate = LocalDateTime.parse(departureTime, formatter);
        LocalDateTime arrivalDate = LocalDateTime.parse(arrivalTime, formatter);
        return new Flight(number, origine, destination, departureDate, arrivalDate);
    }

    public static LocalDateTime StringtoDate(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
        return flightNumber + " origin: "+ origine;
    }

    public String toCSV() {
        return flightNumber + "," + origine + "," + destination + "," + departureDate.toString() + "," + arrivalDate.toString() + "\n";
    }

    public static void updateFlightCSV() throws IOException {
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        for(Flight a : allFlights) WriteToFile.write(PATHNAME, a.toCSV());
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

}
