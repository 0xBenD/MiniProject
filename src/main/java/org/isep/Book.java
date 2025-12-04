package org.isep;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Book {
    private static ArrayList<Book> allBooks = new ArrayList<>();
    private static ArrayList<Integer> bookList = new ArrayList<>();

    private int bookNumber;
    private FlightStatus status;
    private String date;
    private Flight flight;
    private int passengerID;

    public Book(int bookNumber, String date){
        Random randomInt = new Random();
        boolean cond;
        int newBook;
        do {
            cond = false;
            newBook = randomInt.nextInt(100000);
            if (newBook < 0){
                newBook *= -1;
            }
            for (int id : bookList) {
                if (id == newBook) {
                    cond = true;
                    break;
                }
            }
        }while (cond);

        bookList.add(newBook);
        this.bookNumber = newBook;
        this.date = date;
        this.status = FlightStatus.PENDING;
        allBooks.add(this);
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

    public void updateDate(String newDate){
        this.date = newDate;
        System.out.println("Reservation date " +bookNumber+ " updated");
    }

    public static Book findBook(int id){
        for(Book b : allBooks){
            if(b.getReservationNumber() == id){
                return b;
            }
        }
        return null;
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
