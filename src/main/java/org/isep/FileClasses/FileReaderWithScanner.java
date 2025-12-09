package org.isep.FileClasses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class FileReaderWithScanner {


    public static ArrayList<String> readCSV(String fileName, ArrayList<String> data) throws IOException{

        String file = fileName;
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            data.add(scanner.next());
            if (data.getLast() == "\n") break;
        }
        scanner.close();
        return data;
    }
}

