
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
    public static int[] heapsort(int[] arr) {
        int[] result_arr = arr.clone();
        int length = result_arr.length;
        if (length == 0) {
            return result_arr;
        }
        for (int i = length / 2 - 1; i >= 0; i--) {
            makeHeapFromArr(result_arr, result_arr.length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            swap(0, i, result_arr);
            makeHeapFromArr(result_arr, i, 0);
        }


        return result_arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        arr = heapsort(arr);
        for (int j : arr) {
            System.out.println(j);
        }

    }
}
