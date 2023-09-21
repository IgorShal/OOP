import org.example.Polynomial;
import org.junit.jupiter.api.Assertions;

/**
 * Класс с тестами.
 */
public class SampleTests {
    @org.junit.jupiter.api.Test
<<<<<<< HEAD:Task_1_1_2/src/test/java/SampleTests.java
    void summTest() {
=======
    void SumTest() {
>>>>>>> parent of dc7cc77 (task_1_1_2):Task_1_1_2/src/test/java/SampleTest.java
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        Assertions.assertTrue(p1.plus(p2).equal(new Polynomial(new int[]{7, 14, 5, 7})));
    }

    @org.junit.jupiter.api.Test
    void MinusTest() {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        Assertions.assertTrue(p1.minus(p2).equal(new Polynomial(new int[]{7, -2, 1, 1})));
    }

    @org.junit.jupiter.api.Test
    void MultTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Polynomial p2 = new Polynomial(new int[]{1, -1});
        Assertions.assertTrue(p1.times(p2).equal(new Polynomial(new int[]{1, 0, 0, -1})));
    }

    @org.junit.jupiter.api.Test
    void toStringTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Polynomial p2 = new Polynomial(new int[]{1, -1});
        Assertions.assertEquals(p1.times(p2).toString(), "x^3 - 1.0");
    }

    @org.junit.jupiter.api.Test
    void differenciateTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.differentiate(1).toString(), "2.0x + 1.0");
    }

    @org.junit.jupiter.api.Test
    void doubleDifferenciateTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.differentiate(2).toString(), "2.0");
    }

    @org.junit.jupiter.api.Test
    void veryBigCountDifferenciate() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.differentiate(50).toString(), "0.0");
    }

    @org.junit.jupiter.api.Test
    void mainTest() {
        Polynomial.main(new String[]{null});
    }

    @org.junit.jupiter.api.Test
    void evaluateTest() {
        Polynomial p1 = new Polynomial(new int[]{1, 1, 1});
        Assertions.assertEquals(p1.evaluate(2), 7);
    }


}
