package org.isep;

import java.util.ArrayList;

public class Passenger extends Person{

    private String passeport;
    private ArrayList<Book> bookArrayList = new ArrayList<>();

    public Passenger(String name, String address, String contact, String passeport){
        super(name, address, contact);
        this.passeport = passeport;
    }

    public String getPasseport() {
        return passeport;
    }

    public void bookFlight(Flight flight){
        Book book = new Book(flight.getFlightNumber());
        bookArrayList.add(book);
        boolean success = book.confirmReservation(flight, super.getID(), passeport);
        if (success) System.out.printf("Booked flight number %d successfully.\n", flight.getFlightNumber());
        else System.out.printf("Failed to book flight number %d.\n", flight.getFlightNumber());
    }

    public void cancelFlight(int bookNumber){
        for(Book b : bookArrayList){
            if(b.getReservationNumber() == bookNumber) b.cancelReservation();
        }
    }

    public void getReservations(int bookNumber){
        for(Book b : bookArrayList){
            if(b.getReservationNumber() == bookNumber) System.out.printf("Reservation number is : %d , the date is : %s , status : ", bookNumber, b.getDate(), b.getReservationNumber());
        }
    }


}
