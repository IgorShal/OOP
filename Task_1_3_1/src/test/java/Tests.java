import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.example.Finder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Класс с тестами.
 */
public class Tests {
    /**
     * 日本語テスト.
     */
    @Test
    void japTest() throws IOException {
        Finder finder = new Finder("Japanese.txt", "スト", Finder.Type.resourse);
        Assertions.assertEquals(finder.find(), Arrays.asList(247, 370, 549));


    }

    /**
     * Тест русского.
     */
    @Test
    void ruTest() throws IOException {
        Finder finder = new Finder("Russian.txt", "привет", Finder.Type.resourse);
        Assertions.assertEquals(finder.find().size(), Arrays.asList(0, 11, 626, 876).size());
    }

    /**
     * English test.
     */
    @Test
    void enTest() throws IOException {
        Finder finder = new Finder("English.txt", "this", Finder.Type.resourse);
        Assertions.assertEquals(finder.find().size(), Arrays.asList(0, 156, 223, 274, 664,
                937, 1826, 2352, 2810, 2909, 2982, 3630, 4340, 4600, 5062, 5254, 5269, 5477, 5492).size());
    }

    /**
     * Пустой файл.
     */
    @Test
    void emptyFileTest() throws IOException {
        Finder finder = new Finder("Empty.txt", "hello", Finder.Type.resourse);
        Assertions.assertEquals(finder.find(), Arrays.asList());
    }

    /**
     * Одно слово без всего.
     */
    @Test
    void oneWordTest() throws IOException {
        Finder finder = new Finder("OneWord.txt", "hello", Finder.Type.resourse);
        Assertions.assertEquals(finder.find().size(), Arrays.asList(0).size());
    }

    /**
     * Слово в слове.
     */
    @Test
    void oneInOneTest() throws IOException {
        Finder finder = new Finder("oneInOne.txt", "hello", Finder.Type.resourse);
        Assertions.assertEquals(finder.find(), Arrays.asList(3));
    }

    /**
     * Создаём файл гигов на 18.
     */
    @Test
    void generatedFile() throws IOException {

        ArrayList<Integer> result = generateTest(1000000000, "hello", "file.txt");
        Finder finder = new Finder("file.txt", "hello", Finder.Type.file);
        new File("file.txt").delete();
        Assertions.assertEquals(finder.find(), result);
    }

    /**
     * Метод создания файлов гигов на 18.
     */
    private ArrayList<Integer> generateTest(int size, String subString,
                                            String filename) throws IOException {
        ArrayList<Integer> result = new ArrayList<>();
        FileOutputStream fos = new FileOutputStream(filename);
        Random rnd = new Random();
        int countBytes;
        int current = 0;
        countBytes = 120000000;
        byte[] array = new byte[countBytes];
        for (int i = 0; i < size; i++) {
            if (rnd.nextInt(100) > 90) {
                result.add(current);
                current += subString.length();
                fos.write(subString.getBytes());
            } else {
                rnd.nextBytes(array);
                String add = new String(array, StandardCharsets.US_ASCII);
                fos.write(add.getBytes());
                current += (add.length());
                i += add.length() / 20;
            }
        }

        fos.flush();
        fos.close();

        return result;
    }
}

