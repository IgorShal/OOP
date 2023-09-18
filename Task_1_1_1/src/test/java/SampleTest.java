import org.example.Solution;
import org.junit.jupiter.api.Assertions;

/**
 * Класс с тестами.
 */
public class SampleTest {
    @org.junit.jupiter.api.Test
    void differentElements() {
        Assertions.assertEquals("[1, 2, 3, 4, 5]",
                Solution.heapsort(new int[]{5, 4, 3, 2, 1}));
    }

    @org.junit.jupiter.api.Test
    void someEqualItems() {
        Assertions.assertEquals("[1, 1, 3, 4, 5]",
                Solution.heapsort(new int[]{1, 4, 3, 5, 1}));
    }

    @org.junit.jupiter.api.Test
    void manyItems() {
        Assertions.assertEquals("[-4, 1, 2, 3, 4, 5, 44, 54, 333333333]",
                Solution.heapsort(new int[]{1, 2, 3, 5, 333333333, 44, 54, -4, 4}));
    }

    @org.junit.jupiter.api.Test
    void negativeItems() {
        Assertions.assertEquals("[-34939493, -4, -3, -2, 0]",
                Solution.heapsort(new int[]{-4, -3, 0, -2, -34939493}));
    }

    @org.junit.jupiter.api.Test
    void someEqualItems2() {
        Assertions.assertEquals("[1, 1, 3]",
                Solution.heapsort(new int[]{3, 1, 1}));
    }

    @org.junit.jupiter.api.Test
    void empty() {
        Assertions.assertEquals("[]",
                Solution.heapsort(new int[]{}));
    }

    @org.junit.jupiter.api.Test
    void allEqual() {
        Assertions.assertEquals("[1, 1, 1, 1, 1]",
                Solution.heapsort(new int[]{1, 1, 1, 1, 1}));
    }

    @org.junit.jupiter.api.Test
    void twoElements() {
        Assertions.assertEquals("[1, 2]",
                Solution.heapsort(new int[]{1, 2}));
    }

    @org.junit.jupiter.api.Test
    void evenCountOfElements() {
        Assertions.assertEquals("[1, 1, 2, 2, 3, 5]",
                Solution.heapsort(new int[]{1, 2, 5, 3, 2, 1}));
    }

    @org.junit.jupiter.api.Test
    void oneElement() {
        Assertions.assertEquals("[1]",
                Solution.heapsort(new int[]{1}));
    }

    @org.junit.jupiter.api.Test
    void swapTest() {
        int[] arr = {5, 4};
        Solution.swap(0, 1, arr);
        Assertions.assertTrue(arr[0] == 4 && arr[1] == 5);
    }

    @org.junit.jupiter.api.Test
    void heapifyTest() {
        int[] arr = {1, 2, 3};
        Solution.makeHeapFromArr(arr, 3, 0);
        Assertions.assertArrayEquals(arr, new int[]{3, 2, 1});
    }
}
