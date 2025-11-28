package org.isep;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Flight {
    private int flightNumber;
    private String origine;
    private String destination;
    private String Status;// on time / departure / Boarding / Delayed /..
    private Date date;
    private FlightStatus status;

    /*
    new class + enum (flight status)
     */

    public Flight(int flightNumber,FlightStatus status,  String origine, String destination){
        this.flightNumber = flightNumber;
        this.origine = origine;
        this.destination = destination;
    }

    public void planFlight(){
    }

    public void cancelFlight(){
    }

    public void modifyFlight(){

    }

    public void list
    }

}
