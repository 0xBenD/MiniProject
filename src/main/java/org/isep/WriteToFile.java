package org.isep;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriteToFile{

    public static void main(String[] args) throws IOException {
        String content = "Content to write to file";
        //Name and path of the file
        File file = new File("src/main/resources/writefile.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }
}