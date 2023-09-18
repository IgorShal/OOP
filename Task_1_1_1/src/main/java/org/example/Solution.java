
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
            int mx = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < length && arr[left] > arr[mx]) {
                mx = left;
            }
            if (right < length && arr[right] > arr[mx]) {
                mx = right;
            }
            if (mx != i) {
                swap(i, mx, arr);
                i = mx;
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
