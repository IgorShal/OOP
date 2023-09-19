
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
            int maxIndex = i;
            int leftElemTree = 2 * i + 1;
            int rightElemTree = 2 * i + 2;
            if (leftElemTree < length && arr[leftElemTree] > arr[maxIndex]) {
                maxIndex = leftElemTree;
            }
            if (rightElemTree < length && arr[rightElemTree] > arr[maxIndex]) {
                maxIndex = rightElemTree;
            }
            if (maxIndex != i) {
                swap(i, maxIndex, arr);
                i = maxIndex;
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
        System.out.println(heapsort(new int[]{5, 4, 3, 2, 1}));
    }
}
