import org.example.Solution;
import org.junit.jupiter.api.Assertions;

public class SampleTest {
    @org.junit.jupiter.api.Test
    void test1() {
        Assertions.assertEquals("[1, 2, 3, 4, 5]", Solution.heapsort(new int[]{5, 4, 3, 2, 1}));
    }

    @org.junit.jupiter.api.Test
    void test2() {
        Assertions.assertNotEquals("[1, 1, 3, 4, 5]", Solution.heapsort(new int[]{1, 4, 3, 2, 1}));
    }

    @org.junit.jupiter.api.Test
    void test3() {
        Assertions.assertEquals("[1, 1, 1, 1, 1]", Solution.heapsort(new int[]{1, 1, 1, 1, 1}));
    }

    @org.junit.jupiter.api.Test
    void test4() {
        Assertions.assertNotEquals("[1, 4, 3, 4, 5]", Solution.heapsort(new int[]{5, 4, 3, 2, 1}));
    }

    @org.junit.jupiter.api.Test
    void test5() {
        int[] arr = {5, 4};
        Solution.swap(0, 1, arr);
        Assertions.assertTrue(arr[0] == 4 && arr[1] == 5);
    }

    @org.junit.jupiter.api.Test
    void test6() {
        int[] arr = {1, 2, 3};
        Solution.heapify(arr, 3, 0);
        Assertions.assertArrayEquals(arr, new int[]{3, 2, 1});
    }
}
