package org.isep;
import java.util.ArrayList;

public class Flight {
    private static ArrayList<Flight> allFlights = new ArrayList<>();

    private int flightNumber;
    private Airport origine;
    private Airport destination;
    private String departureTime;
    private String arrivalTime;
    private FlightStatus status;
    private Aircraft aircraft;
    private ArrayList<Passenger> passengerList = new ArrayList<>();
    private AirplanePilot pilot;

    private ArrayList<StaffCabin> cabinCrew = new ArrayList<>();

    public Flight(int flightNumber, Airport origine, Airport destination, String departureTime,String arrivalTime){
        this.flightNumber = flightNumber;
        this.origine = origine;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = FlightStatus.ON_TIME;
        origine.addDepartingFlight(this);
        destination.addArrivingFlight(this);
        allFlights.add(this);
    }

    public static Flight scheduleFlight(int number,Airport origine, Airport destination, String departureTime, String arrivalTime){
        return new Flight(number, origine, destination, departureTime, arrivalTime);
    }

    public void updateFlightTimes(String newDeparture, String newArrival ){
        this.departureTime = newDeparture;
        this.arrivalTime = newArrival;
        System.out.println("Flight time updated");
    }

    public int getFlightNumber(){
        return flightNumber;
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

    public void listPassenger(){
        System.out.println("Passenger List for Flight "+ flightNumber + " : ");
        for(Passenger p : passengerList){
            p.getInfos();
        }
    }

    public void assignAircraft(Aircraft aircraft){
        if(aircraft.checkAvailability()){
            this.aircraft = aircraft;
            aircraft.assignFlight(this);
        }
        else {
            System.out.println("Aircraft is not available");
        }
    }

    public void cancelFlight(){
        this.status = FlightStatus.CANCELED;
        if(this.aircraft != null){
            this.aircraft.releaseAircraft();
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

    public Airport getOrigine(){
        return origine;
    }
    public Airport getDestination(){
        return destination;
    }
    public String getDepartureTime(){
        return departureTime;
    }
}
