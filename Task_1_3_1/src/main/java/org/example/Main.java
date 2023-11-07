package org.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Мейн класс с тестом файла.
 */
public class Main {
    /**
     * Мейн метод.
     */
    public static void main(String[] args) throws IOException {
        Finder finder = new Finder("Russian.txt", "привет", Finder.Type.resourse);
        ArrayList<Integer> res= finder.find();
        for (int i = 0;i<res.size();i++){
            System.out.println(res.get(i));
        }
    }

}