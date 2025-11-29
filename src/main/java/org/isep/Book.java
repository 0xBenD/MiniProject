package org.isep;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Book {
    private int bookNumber;
    private FlightStatus status;
    private String date;
    private Flight flight;
    private int passengerID;
    private ArrayList<Integer> bookList = new ArrayList<>();

    public Book(int bookNumber){
        Random randomInt = new Random();
        boolean cond;
        int newBook;
        do {
            cond = false;
            newBook = randomInt.nextInt();
            for (int i = 0; i < bookList.size(); i++) {
                if(bookList.get(i) == newBook) cond = true;
            }
        } while(cond);
        bookList.add(newBook);
        this.bookNumber = newBook;

        this.date = new Date().toString();
        this.status = FlightStatus.PENDING;
    }

    public boolean confirmReservation(Flight flight, int passengerID, String passeport){
        this.flight = flight;
        this.passengerID = passengerID;
        this.status = FlightStatus.CONFIRMED;
        System.out.println("Reservation " +bookNumber+ " confirmed for "+ passengerID);
        return true;
    }

    public void cancelReservation(){
        this.status = FlightStatus.CANCELED;
        System.out.println("Reservation " +bookNumber+ " canceled");
    }

    public int getReservationNumber(){
        return bookNumber;
    }

    public FlightStatus getStatus(){
        return status;
    }

    public String getDate(){
        return date;
    }


}
