import org.example.CalculatorException;
import org.example.DivisionByZeroException;
import org.example.LogNonPositiveException;
import org.example.NoSuchFunctionException;
import org.example.Parser;
import org.example.SqrtFromNegativeException;
import org.example.WrongExpressionException;
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
    public void sampleTest() throws CalculatorException {
        Assertions.assertEquals(0, new Parser("sin + - 1 2 1").calculate());
    }

    /**
     * Тест минуса.
     */
    @Test
    public void minusTest() throws CalculatorException {
        Assertions.assertEquals(-3,
                new Parser("+ -1 -2").calculate());
        Assertions.assertEquals(-3,
                new Parser("+ -1 -2").calculate());
        Assertions.assertEquals(-9,
                new Parser("- - - 1 2 3 5").calculate());
    }

    /**
     * Тест модуля.
     */
    @Test
    public void absTest() throws CalculatorException {
        Assertions.assertEquals(5,
                new Parser("abs 5").calculate());
        Assertions.assertEquals(5,
                new Parser("abs -5").calculate());
        Assertions.assertEquals(4,
                new Parser("abs + 1 3").calculate());
        Assertions.assertEquals(2,
                new Parser("abs - 1 3").calculate());
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("abs 2 1").calculate();
                });
    }

    /**
     * Тест косинуса.
     */
    @Test
    public void cosTest() throws CalculatorException {
        Assertions.assertEquals(-1,
                new Parser("cos 3.14159").calculate(), 0.1);
        Assertions.assertEquals(0,
                new Parser("+ 1 cos 3.14159").calculate(), 0.1);
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("cos 2 1").calculate();
                });
    }

    /**
     * Тест синуса.
     */
    @Test
    public void sinTest() throws CalculatorException {
        Assertions.assertEquals(0,
                new Parser("sin 3.14159").calculate(), 0.1);
        Assertions.assertEquals(1,
                new Parser("+ 1 sin 3.14159").calculate(), 0.1);
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("sin 2 1").calculate();
                });
    }

    /**
     * Тест деления.
     */
    @Test
    public void divTest() throws CalculatorException {
        Assertions.assertEquals(5,
                new Parser("/ 10 2").calculate());
        Assertions.assertEquals(-4,
                new Parser("/ 100 -25").calculate());
        Assertions.assertEquals(-1,
                new Parser("/ / 100 -25 4").calculate());
        Assertions.assertThrowsExactly(DivisionByZeroException.class,
                () -> {
                    new Parser("/ 2 0").calculate();
                });
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("/ 1").calculate();
                });
    }

    /**
     * Тест логарифма.
     */
    @Test
    public void logTest() throws Exception {
        Assertions.assertThrowsExactly(LogNonPositiveException.class,
                () -> {
                    new Parser("log 0").calculate();
                });
        Assertions.assertThrowsExactly(LogNonPositiveException.class,
                () -> {
                    new Parser("log -10").calculate();
                });
        Assertions.assertEquals(1,
                new Parser("log 2.71828").calculate(), 0.1);
        Assertions.assertEquals(5,
                new Parser("+ 4 log 2.71828").calculate(), 0.1);
        Assertions.assertEquals(0,
                new Parser("log log 2.71828").calculate(), 0.1);
    }

    /**
     * Тест умножения.
     */
    @Test
    public void multTest() throws CalculatorException {
        Assertions.assertEquals(20,
                new Parser("* 10 2").calculate());
        Assertions.assertEquals(-4,
                new Parser("* -2 2").calculate());
        Assertions.assertEquals(-8,
                new Parser("* * 2 -2 2").calculate());
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("1 + 1").calculate();
                });
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("* 1").calculate();
                });
    }

    /**
     * Тест пова.
     */
    @Test
    public void powTest() throws CalculatorException {
        Assertions.assertEquals(100,
                new Parser("pow 10 2").calculate());
        Assertions.assertEquals(-4,
                new Parser("* -2 2").calculate());
        Assertions.assertEquals(16,
                new Parser("pow pow 2 2 2").calculate());
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("pow 1").calculate();
                });
        Assertions.assertEquals(32,
                new Parser("pow 2 5").calculate());
    }

    /**
     * Тест плюса.
     */
    @Test
    public void plusTest() throws CalculatorException {
        Assertions.assertEquals(12,
                new Parser("+ 10 2").calculate());
        Assertions.assertEquals(0,
                new Parser("+ -2 2").calculate());
        Assertions.assertEquals(2,
                new Parser("+ + 2 -2 2").calculate());
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("+ 1").calculate();
                });
    }

    /**
     * Тест возведения в квадрат.
     */
    @Test
    public void sqrTest() throws CalculatorException {
        Assertions.assertEquals(100,
                new Parser("sqr 10").calculate());
        Assertions.assertEquals(4,
                new Parser("sqr -2").calculate());
        Assertions.assertEquals(16,
                new Parser("sqr sqr 2").calculate());
        Assertions.assertThrowsExactly(WrongExpressionException.class,
                () -> {
                    new Parser("sqr 1 2").calculate();
                });
        Assertions.assertEquals(7,
                new Parser("+ sqr 2 3").calculate());
        Assertions.assertEquals(25,
                new Parser("sqr + 2 3").calculate());
    }

    /**
     * Тест корня.
     */
    @Test
    public void sqrtTest() throws CalculatorException {
        Assertions.assertThrowsExactly(SqrtFromNegativeException.class,
                () -> {
                    new Parser("sqrt -1").calculate();
                });
        Assertions.assertEquals(0,
                new Parser("sqrt 0").calculate());
        Assertions.assertEquals(5,
                new Parser("sqrt 25").calculate());
        Assertions.assertEquals(2,
                new Parser("sqrt sqrt 16").calculate());
        Assertions.assertEquals(6,
                new Parser("+ sqrt 16 sqrt 4").calculate());
        Assertions.assertEquals(4,
                new Parser("sqrt + 5 11").calculate());
    }

    /**
     * Другие доп тесты.
     */
    @Test
    public void otherTests() throws CalculatorException {
        Assertions.assertEquals(1, new Parser("1").calculate());
        Assertions.assertEquals(15,
                new Parser("+ sqrt sqr + / * + 4 3 12 12 5 + 1 2").calculate());
        Assertions.assertEquals(4,
                new Parser("   +             1                     3").calculate());
        Assertions.assertThrowsExactly(NoSuchFunctionException.class,
                () -> {
                    new Parser("sdsd aada").calculate();
                });
    }
}
