package org.isep.FileClasses;
import java.io.*;
import java.nio.file.*;


public class LargeFileReaderWithFiles {

    public static void main(String[] args) throws IOException {
        String file = "src/file.txt";
        Path path = Paths.get(file);

        BufferedReader bufferedReader = Files.newBufferedReader(path);
        String curLine;

        while ((curLine = bufferedReader.readLine()) != null){
            System.out.println(curLine);
        }
        bufferedReader.close();
    }
}
