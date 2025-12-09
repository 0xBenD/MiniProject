package org.isep;

import org.isep.FileClasses.WriteToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Passenger extends Person{

    private static ArrayList<Passenger> allPassengers = new ArrayList<>();

    private static final String PATHNAME = "src/main/resources/passenger.csv";

    private String passeport;
    private ArrayList<Book> bookArrayList = new ArrayList<>();

    public Passenger(String name, String address, String contact, String passeport){
        super(name, address, contact);
        this.passeport = passeport;
        allPassengers.add(this);
    }

    public String getPasseport() {
        return passeport;
    }

    public void bookFlight(Flight flight, String date){
        Book book = new Book(flight.getFlightNumber(), date);
        boolean isAdded = flight.addPassenger(this);

        if(isAdded){
            boolean success = book.confirmReservation(flight, super.getID(), passeport);
            if (success) {
                this.bookArrayList.add(book);
                System.out.printf("Booked flight number %d successfully.\n", flight.getFlightNumber());
            }
        } else {
            System.out.printf("Failed to book flight number %d.\n", flight.getFlightNumber());
        }
    }

    public void cancelFlight(int bookNumber){
        for(Book b : bookArrayList){
            if(b.getReservationNumber() == bookNumber) {
                b.cancelReservation();
            }
        }
    }

    public void getReservations(int bookNumber){
        for(Book b : bookArrayList){
            if(b.getReservationNumber() == bookNumber) {
                System.out.printf("Reservation number is : %d , the date is : %s , status : ", bookNumber, b.getDate(), b.getReservationNumber());
            }
        }
    }

    public static void listAllPassengers(){
        for(Passenger p : allPassengers){
            p.getInfos();
        }
    }

    public static Passenger findPassenger(int ID){
        for(Passenger p : allPassengers){
            if(p.getID() == ID){
                return p;
            }
        }
        return null;
    }

    public static void deletePassengers(int ID){
        Passenger p = findPassenger(ID);
        if(p != null){
            allPassengers.remove(p);
            System.out.println("Passenger deleted");
        }
        else {
            System.out.println("Passenger not found");
        }
    }

    public static ArrayList<Passenger> getAllPassengers(){
        return allPassengers;
    }

    public String toCSV() {
        return this.getName() + "," + this.getAddress() + "," + this.getContact() + "," + passeport + "\n";
    }

    public static void updatePassengerCSV() throws IOException {
        File file = new File(PATHNAME);
        FileWriter fw = new FileWriter(file, false);
        for(Passenger a : allPassengers) WriteToFile.write(PATHNAME, a.toCSV());
    }

    @Override
    public String toString(){
        return this.getName() + " passport: " + passeport;
    }
}
