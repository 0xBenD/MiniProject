package org.isep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileReaderWithBufferedReader {

    public static ArrayList<String> readFile(String file) throws IOException{

        ArrayList<String> data = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String curLine;
        while ((curLine = bufferedReader.readLine()) != null){      //process the line as required
            String[] temp = curLine.split(",");
            for(String a : temp) data.add(a);
        }
        bufferedReader.close();
        return data;
        }
}

