import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import org.example.ParallelSolver;
import org.example.ParallelStreamSolver;
import org.example.SequentualSolver;
import org.example.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Класс тестов.
 */
public class PrimeSolversTest {
    ArrayList<Solver> solvers;
    ArrayList<Long> results;

    /**
     * Метод подготовки начальных данных тестов.
     */
    @BeforeEach
    public void prepareTests() {
        solvers = new ArrayList<>();
        solvers.add(new SequentualSolver());
        solvers.add(new ParallelStreamSolver());
        int maxThreads = ManagementFactory.getThreadMXBean().getThreadCount();
        for (int i = 1; i < maxThreads; i++) {
            solvers.add(new ParallelSolver(i));
        }
        System.out.println("active threads:" + maxThreads);
        results = new ArrayList<>();
    }

    /**
     * Первый тест из условия.
     */
    @Test
    public void taskTest1() {
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertTrue(solver.hasNonPrimeNumber(new long[]{6, 8, 7, 13, 5, 9, 4}));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        solvers.clear();
        results.clear();
    }

    /**
     * Второй тест из условия.
     */
    @Test
    public void taskTest2() {
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertFalse(solver.hasNonPrimeNumber(new long[]{
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053}));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        solvers.clear();
        results.clear();
    }

    /**
     * Проверка стандартных простых.
     */
    @Test
    public void basicPrimesTest() {
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertFalse(solver.hasNonPrimeNumber(new long[]{
                1, 2, 3, 5, 7, 11,
                13, 17, 19, 23, 29, 31}));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        solvers.clear();
        results.clear();
    }

    /**
     * Массив из только непростых.
     */
    @Test
    public void allNonPrimeTest() {
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertTrue(solver.hasNonPrimeNumber(new long[]{
                4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 30}));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        solvers.clear();
        results.clear();
    }

    /**
     * Массив с числами до 1000000.
     */
    @Test
    public void hugeIntTest() {
        ArrayList<Long> testArrayList = new ArrayList<>();
        for (long i = 1; i < 1000000; i++) {
            testArrayList.add(i);
        }
        Number[] testArray = new Number[testArrayList.size()];
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertTrue(solver.hasNonPrimeNumber(
                testArrayList.stream().mapToLong(i -> i).toArray()));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        testArrayList.clear();
        solvers.clear();
        results.clear();
    }

    /**
     * Массив простых до 1000000.
     */
    @Test
    public void hugeIntPrimeTest() {
        ArrayList<Long> testArrayList = new ArrayList<>();
        for (long i = 1; i < 1000000; i++) {
            if (solvers.get(0).isPrime(i)) {
                testArrayList.add(i);
            }
        }
        Number[] testArray = new Number[testArrayList.size()];
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertFalse(solver.hasNonPrimeNumber(
                testArrayList.stream().mapToLong(i -> i).toArray()));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        testArrayList.clear();
        solvers.clear();
        results.clear();
    }

    /**
     * Простые лонги.
     */
    @Test
    public void primeLongTest() {
        long[] testArray = new long[]{
            9223372036854775783L, 9223372036854775643L, 9223372036854775549L,
            9223372036854775507L, 9223372036854775507L, 9223372036854775783L,
            9223372036854775643L, 9223372036854775549L, 9223372036854775783L};
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertFalse(solver.hasNonPrimeNumber(testArray));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        solvers.clear();
        results.clear();
    }

    /**
     * Простые лонги с непростым в начале массива.
     */
    @Test
    public void primeLongWithNonPrimeInBegTest() {
        long[] testArray = new long[]{
            6, 9223372036854775783L, 9223372036854775643L, 9223372036854775549L,
            9223372036854775507L, 9223372036854775507L, 9223372036854775783L,
            9223372036854775643L, 9223372036854775549L, 9223372036854775783L};
        for (Solver solver : solvers) {
            long start = System.currentTimeMillis();
            Assertions.assertTrue(solver.hasNonPrimeNumber(testArray));
            results.add(System.currentTimeMillis() - start);
        }
        System.out.println(results);
        solvers.clear();
        results.clear();
    }
}
