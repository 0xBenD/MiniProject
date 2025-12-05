package org.isep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteWithFile {

    public static void main( String[] args ) {
        Path path = Paths.get("writefile.txt");
        String content = "Content to write to file";
        try {
            byte[] bytes = content.getBytes();
            Files.write(path, bytes);
        } catch(IOException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
    }
}