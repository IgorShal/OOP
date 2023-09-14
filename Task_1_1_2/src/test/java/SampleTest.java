import org.example.Polynomial;
import org.junit.jupiter.api.Assertions;

/**
 * Класс с тестами.
 */
public class SampleTest {
    @org.junit.jupiter.api.Test
    void test1() {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        Assertions.assertTrue(p1.plus(p2).equal(new Polynomial(new int[]{7, 14, 5, 7})));
    }

    @org.junit.jupiter.api.Test
    void test2() {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        Assertions.assertTrue(p1.minus(p2).equal(new Polynomial(new int[]{7, -2, 1, 1})));
    }

    @org.junit.jupiter.api.Test
    void test3() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Polynomial p2 = new Polynomial(new int[]{1, -1});
        Assertions.assertTrue(p1.times(p2).equal(new Polynomial(new int[]{1, 0, 0, -1})));
    }

    @org.junit.jupiter.api.Test
    void test4() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Polynomial p2 = new Polynomial(new int[]{1, -1});
        Assertions.assertEquals(p1.times(p2).toString(), "x^3 - 1");
    }

    @org.junit.jupiter.api.Test
    void test5() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.differentiate(1).toString(), "2x + 1");
    }

    @org.junit.jupiter.api.Test
    void test6() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.differentiate(2).toString(), "2");
    }

    @org.junit.jupiter.api.Test
    void test7() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.differentiate(50).toString(), "0");
    }

    @org.junit.jupiter.api.Test
    void test8() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.differentiate(50).toString(), "0");
    }

    @org.junit.jupiter.api.Test
    void test9() {
        Polynomial.main(new String[]{null});
    }

    @org.junit.jupiter.api.Test
    void test10() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.evaluate(2), 7);
    }


}
