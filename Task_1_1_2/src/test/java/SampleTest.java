import org.example.Polynomial;
import org.junit.jupiter.api.Assertions;

/**
 * Класс с тестами.
 */
public class SampleTest {
    @org.junit.jupiter.api.Test
    void sumTest() {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        Assertions.assertTrue(p2.plus(p1).equals(new Polynomial(new int[]{7, 14, 5, 7})));
    }

    @org.junit.jupiter.api.Test
    void minusTest() {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        Assertions.assertTrue(p1.minus(p2).equals(new Polynomial(new int[]{7, -2, 1, 1})));
    }

    @org.junit.jupiter.api.Test
    void multTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Polynomial p2 = new Polynomial(new int[]{1, -1});
        Assertions.assertTrue(p1.times(p2).equals(new Polynomial(new int[]{1, 0, 0, -1})));
    }

    @org.junit.jupiter.api.Test
    void minusItSelfTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertTrue(p1.minus(p1).equals(new Polynomial(new int[]{0})));
    }

    @org.junit.jupiter.api.Test
    void plusMinusItself() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Polynomial zero = new Polynomial(new int[]{0});
        Assertions.assertTrue(p1.plus(zero.minus(p1)).equals(zero));
    }

    @org.junit.jupiter.api.Test
    void minusZerot() {
        Polynomial p1 = new Polynomial(new int[]{1, 2, 1});
        Polynomial p2 = new Polynomial(new int[]{0});
        Assertions.assertTrue(p1.times(p2).equals(p2));
    }

    @org.junit.jupiter.api.Test
    void toStringTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Polynomial p2 = new Polynomial(new int[]{1, -1});
        Assertions.assertEquals(p1.times(p2).toString(), "x^3 - 1.0");
    }

    @org.junit.jupiter.api.Test
    void differentiateTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertTrue(p1.differentiate(1).equals(new Polynomial(new int[]{2, 1})));
    }

    @org.junit.jupiter.api.Test
    void doubleDifferentiateTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertTrue(p1.differentiate(2).equals(new Polynomial(new int[]{2})));
        ;
    }

    @org.junit.jupiter.api.Test
    void veryBigCountDifferentiate() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertTrue(p1.differentiate(50).equals(new Polynomial(new int[]{0})));
        ;
    }

    @org.junit.jupiter.api.Test
    void evaluateTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.evaluate(2), 7);
    }

    @org.junit.jupiter.api.Test
    void evaluateZero() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.evaluate(0), 1);
    }

    @org.junit.jupiter.api.Test
    void evaluateZeroFunc() {
        Polynomial p1 = new Polynomial(new int[]{0});
        Assertions.assertEquals(p1.evaluate(100), 0);
    }

    @org.junit.jupiter.api.Test
    void requestedTest() {
        double[] coef = new double[]{1, 2, 3};
        Polynomial p1 = new Polynomial(coef);
        coef[0] = 5;
        Assertions.assertEquals(p1, new Polynomial(new double[]{1, 2, 3}));
    }

    @org.junit.jupiter.api.Test
    void reduceTest() {
        double[] coef = new double[]{0};
        Polynomial p1 = new Polynomial(coef);
        Assertions.assertEquals(p1, new Polynomial(new double[]{0}));
    }

    @org.junit.jupiter.api.Test
    void equalsTest() {
        double[] coef = new double[]{1, 2, 3, 4};
        Polynomial p1 = new Polynomial(coef);
        double[] coefs = new double[]{1};
        Polynomial p2 = new Polynomial(coefs);
        Assertions.assertFalse(p1.equals(p2));
        Assertions.assertFalse(p1.equals(""));
    }


}
