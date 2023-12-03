import org.example.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Класс тестов.
 */
public class Tests {
    /**
     * Тест из примера.
     */
    @Test
    public void sampleTest() throws Exception {
        Assertions.assertEquals(0, new Parser("sin + - 1 2 1").calculate());
    }

    /**
     * Неправильное количество аргументов.
     */
    @Test
    public void wrongCountOfArgumentsTest() throws Exception {
        Assertions.assertThrowsExactly(Exception.class,
                () -> {
                    new Parser("sin 2 1").calculate();
                });
    }

    /**
     * Одно число.
     */
    @Test
    public void oneArgumentTest() throws Exception {
        Assertions.assertEquals(1, new Parser("1").calculate());
    }

    /**
     * Тест на неправильную расстановку аргументов.
     */
    @Test
    public void wrongArgumentPositionTest() throws Exception {
        Assertions.assertThrowsExactly(Exception.class,
                () -> {
                    new Parser("1 + 1").calculate();
                });
    }

    /**
     * Деление на 0.
     */
    @Test
    public void divisionByZeroTest() throws Exception {
        Assertions.assertThrowsExactly(Exception.class,
                () -> {
                    new Parser("/ 1 0").calculate();
                });
    }

    /**
     * Логарифм нуля.
     */
    @Test
    public void logZeroTest() throws Exception {
        Assertions.assertThrowsExactly(Exception.class,
                () -> {
                    new Parser("log 0").calculate();
                });
    }

    /**
     * Большое выражение.
     */
    @Test
    public void bigExpTest() throws Exception {
        Assertions.assertEquals(15,
                new Parser("+ sqrt sqr + / * + 4 3 12 12 5 + 1 2").calculate());
    }

    /**
     * Корень из отрицательного.
     */
    @Test
    public void negSqrtTest() throws Exception {
        Assertions.assertThrowsExactly(Exception.class,
                () -> {
                    new Parser("sqrt -1").calculate();
                });
    }

    /**
     * Проверка пова.
     */
    @Test
    public void powTest() throws Exception {
        Assertions.assertEquals(32,
                new Parser("pow 2 5").calculate());
    }

    /**
     * Унарный минус.
     */
    @Test
    public void unMinusTest() throws Exception {
        Assertions.assertEquals(-3,
                new Parser("+ -1 -2").calculate());
    }


    @Test
    public void manySpacesTest() throws Exception {
        Assertions.assertEquals(4,
                new Parser("   +             1                     3").calculate());
    }

    /**
     * Абс и косинус.
     */
    @Test
    public void cosAbsTest() throws Exception {
        Assertions.assertEquals(1,
                new Parser("abs cos 3.14159").calculate(), 0.001);
    }
}
