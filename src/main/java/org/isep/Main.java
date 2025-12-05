package org.isep;
import java.io.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> data = FileReaderWith.readCSV("src/main/resources/flight.txt", new ArrayList<String>());
        Airport Paris = new Airport("Paris", "Paris", "Paris");
        Airport Rome = new Airport("Rome", "Rome", "Rome");
        Flight flight = new Flight(parseInt(data.get(0)), Airport.findAirport(data.get(1)), Airport.findAirport(data.get(2)), Flight.StringtoDate(data.get(3)), Flight.StringtoDate(data.get(4)));
        System.out.println(flight);
        System.out.println(data);
    }
}