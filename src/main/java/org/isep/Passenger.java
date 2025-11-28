package org.isep;

public class Passenger extends Person{

    private String passeport;

    public Passenger(String name, String address, String contact, String passeport){
        super(name, address, contact);
        this.passeport = passeport;
    }

    public void bookFlight(int flightNumber){
        boolean success = Book.confirmReservation(flightNumber, super.getID(), passeport);
        if (success) System.out.printf("Booked flight number %d successfully.\n", flightNumber);
        else System.out.printf("Failed to book flight number %d.\n", flightNumber);
    }
}
