package org.isep;

public class Passenger extends Person{

    private String passeport;

    public Passenger(String name, String address, String contact, String passeport){
        super(name, address, contact);
        this.passeport = passeport;
    }

    public void bookFlight(Flight flight){
        Book book = new Book();
        boolean success = book.confirmReservation(flight, super.getID(), passeport);
        if (success) System.out.printf("Booked flight number %d successfully.\n", flightNumber);
        else System.out.printf("Failed to book flight number %d.\n", flightNumber);
    }
}
