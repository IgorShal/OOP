
package org.example;

/**
 * Класс решения.
 */

public class Solution {
    /**
     * Функция для смены элементов массива по индексам.
     */

    public static void swap(int index1, int index2, int[] arr) {
        //
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * Фуенкция для прохода по куче с просеиванием.
     */
    public static void makeHeapFromArr(int[] arr, int length, int i) {
        while (true) {
            int max_index = i;
            int left_elem_tree = 2 * i + 1;
            int right_elem_tree = 2 * i + 2;
            if (left_elem_tree < length && arr[left_elem_tree] > arr[max_index]) {
                max_index = left_elem_tree;
            }
            if (right_elem_tree < length && arr[right_elem_tree] > arr[max_index]) {
                max_index = right_elem_tree;
            }
            if (max_index != i) {
                swap(i, max_index, arr);
                i = max_index;
            } else {
                break;
            }
        }

    }

    /**
     * Функция самого heap sort в которой мы сначала создаём из исходного массива кучу
     * а потом вытесняем максимальные элементы.
     */
    public static String heapsort(int[] arr) {

        int length = arr.length;
        if (length == 0) {
            return "[]";
        }
        for (int i = length / 2 - 1; i >= 0; i--) {
            makeHeapFromArr(arr, arr.length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            swap(0, i, arr);
            makeHeapFromArr(arr, i, 0);
        }

        // Возвращаем результат как строку.
        String result = "[";
        for (int i = 0; i < length - 1; i++) {
            result = result.concat(arr[i] + ", ");
        }
        result += (arr[length - 1] + "]");
        return result;
    }

    public static void main(String[] args) {
        heapsort(new int[]{5, 4, 3, 2, 1});
    }
}
