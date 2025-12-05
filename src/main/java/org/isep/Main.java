package org.isep;
import java.io.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> data = FileReaderWithScanner.readCSV("src/main/resources/flight.txt", new ArrayList<String>());
    }
}