
package org.example;


public class Solution {
    //Класс решения
    public static void swap(int index1, int index2, int[] arr) {
        //Функция для смены элементов массива по индексам
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void heapify(int[] arr, int length, int i) {
        //Фуенкция для прохода по куче с просеиванием
        int mx = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < length && arr[left] > arr[mx]){
            mx = left;
        }
        if (right < length && arr[right] > arr[mx]) {
            mx = right;
        }
        if (mx != i) {
            swap(i, mx, arr);
            heapify(arr, length, mx);
        }
    }

    public static String heapsort(int[] arr) {
        //Функция самого heap sort в которой мы сначала создаём из исходного массива кучу а потом вытесняем максимальные элементы
        int length = arr.length;
        for (int i = length / 2 - 1; i >= 0; i--){
            heapify(arr, arr.length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            swap(0, i, arr);
            heapify(arr, i, 0);
        }
        //Возвращаем результат как строку
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
