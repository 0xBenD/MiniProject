package org.isep.FileClasses;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;


public class SmallFileReaderWithFiles {

    public static void main(String[] args) throws IOException {
        String file = "src/file.txt";
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path);
    }
}