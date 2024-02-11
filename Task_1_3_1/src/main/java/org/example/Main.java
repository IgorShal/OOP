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
        String s = "🏳";
        int res = s.codePointCount(0, s.length());
        System.out.println(res);
    }

}