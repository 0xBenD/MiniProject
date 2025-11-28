package org.isep;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Book {
    private int bookNumber;
    private String Status;
    private Date date;
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
        this.date = Date.from(Instant.now());
    }

    public boolean confirmReservation(Flight flight, int ID,String passeport){

    }

    public void bookCancellation(){

    }
    public void bookModification(){

    }

}
