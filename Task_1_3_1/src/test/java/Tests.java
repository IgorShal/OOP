import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Assertions.assertEquals(finder.find(), Arrays.asList(241, 362, 539));


    }

    /**
     * Тест русского.
     */
    @Test
    void ruTest() throws IOException {
        Finder finder = new Finder("Russian.txt", "привет", Finder.Type.resourse);
        Assertions.assertEquals(finder.find(), Arrays.asList(0, 11, 616, 856));
    }

    /**
     * English test.
     */
    @Test
    void enTest() throws IOException {
        Finder finder = new Finder("English.txt", "this", Finder.Type.resourse);

        Assertions.assertEquals(finder.find(), Arrays.asList(91, 531, 713, 728, 924, 939));
    }

    /**
     * Пустой файл.
     */
    @Test
    void emptyFileTest() throws IOException {
        Finder finder = new Finder("Empty.txt", "hello", Finder.Type.resourse);
        Assertions.assertTrue(finder.find().isEmpty());
    }

    /**
     * Одно слово без всего.
     */
    @Test
    void oneWordTest() throws IOException {
        Finder finder = new Finder("oneWord.txt", "hello", Finder.Type.resourse);
        Assertions.assertEquals(finder.find(), List.of(0));
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

        Assertions.assertEquals(finder.find(), result);
        new File("file.txt").delete();
    }

    /**
     * Метод создания файлов гигов на 18.Точнее 18.6
     */
    private ArrayList<Integer> generateTest(int size, String subString,
                                            String filename) throws IOException {
        ArrayList<Integer> result = new ArrayList<>();
        FileOutputStream fos = new FileOutputStream(filename);
        Random rnd = new Random();
        int countBytes;
        int current = 0;
        countBytes = 50000000;
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
                i += add.length() / 10;
            }
        }

        fos.flush();
        fos.close();

        return result;
    }
}

