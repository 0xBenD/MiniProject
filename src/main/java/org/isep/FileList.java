package org.isep;

import java.io.*;

public class FileList {

    public static void main(String[] args) throws IOException {
        File repertoire = new File(args[0]);
        String list[] = repertoire.list();

        if(list != null){
            for(int i = 0; i < list.length; i++) System.out.println(list[i]);
            //for(int i = 0; i < list.length; i++) FileReaderWithBufferedReader.readFile("src/main/resources/"+list[i]);
        }
        else System.err.println("Invalid Folder Name");
    }

}
