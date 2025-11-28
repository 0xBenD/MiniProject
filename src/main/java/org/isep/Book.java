package org.isep;
import java.time.Instant;
import java.util.Date;

public class Book {
    private int bookNumber;
    private String Status;
    private Date date;

    public Book(int bookNumber){
        this.bookNumber = bookNumber;
        this.date = Date.from(Instant.now());
    }

    public boolean confirmReservation(Flight flight, int ID,String passeport){

    }

    public void bookCancellation(){

    }
    public void bookModification(){

    }

}
