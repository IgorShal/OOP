package org.example;


import java.io.IOException;
import java.util.ArrayList;


/**
 * Мейн класс с тестом файла.
 */
public class Main {
    /**
     * Мейн метод.
     */
    public static void main(String[] args) throws IOException {
        Finder finder = new Finder("file.txt", "f", Finder.Type.file);
        ArrayList<Integer> result = finder.find();
        System.out.println();
    }

}