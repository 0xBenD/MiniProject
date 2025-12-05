package org.isep;
import java.io.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> data = FileReaderWithBufferedReader.readCSV("src/main/resources/flight.txt", new ArrayList<String>());
        Airport Paris = new Airport("Paris", "Paris", "Paris");
        Airport Rome = new Airport("Rome", "Rome", "Rome");
        Airport Lyon = new Airport("Lyon", "Lyon", "Lyon");
        Airport Prague = new Airport("Prague", "Prague", "Prague");
        Airport Nice = new Airport("Nice", "Nice", "Nice");
        Airport Bale = new Airport("Bale", "Bale", "Bale");
        //Flight flight = new Flight(parseInt(data.get(0)), Airport.findAirport(data.get(1)), Airport.findAirport(data.get(2)), Flight.Stringto Date(data.get(3)), Flight.StringtoDate(data.get(4)));
        ArrayList<Flight> flightList = new ArrayList<>();
        for(int i = 0; i < data.size(); i+=5) {
            flightList.add(new Flight(parseInt(data.get(i)), Airport.findAirport(data.get(i+1)), Airport.findAirport(data.get(i+2)), Flight.StringtoDate(data.get(i+3)), Flight.StringtoDate(data.get(i+4))));
        }

        for(Flight a : flightList) System.out.println(a);
        System.out.println(data);
    }
}