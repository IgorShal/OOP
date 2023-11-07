package org.example;


import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Finder finder = new Finder("file.txt", "f", Finder.fileType.file);
        ArrayList<Integer> result = finder.find();
        System.out.println();
    }

}