package org.isep;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private int flightNumber;
    private Airport origine;
    private Airport destination;
    private String departureTime;
    private String arrivalTime;
    private FlightStatus status;
    private Aircraft aircraft;
    private ArrayList<Passenger> passengerList = new ArrayList<>();
    private AirplanePilot pilot;
    private StaffCabin cabin;

    public Flight(int flightNumber, Airport origine, Airport destination, String departureTime,String arrivalTime){
        this.flightNumber = flightNumber;
        this.origine = origine;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = FlightStatus.ON_TIME;
        origine.addDepartingFlight(this);
        destination.addArrivingFlight(this);
    }

    public int getFlightNumber(){
        return flightNumber;
    }

    public void assignPilot(AirplanePilot pilot){
        this.pilot = pilot;
        pilot.assignFlight(this);
    }

    public void assignCabin(StaffCabin cabin){
        this.cabin = cabin;
        cabin.assignFlight(this);
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
    



}
