package com.hjk.custom.utils.algorithms.etc.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String... args) {
        sort(new Integer[]{4,3,1,2,5});
    }

    public static <T extends Comparable<T>> T[] sort(T[] arr) {
        doSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static <T extends Comparable<T>> void doSort(T[] arr, int left, int right) {
        if (left < right) {
            int pivot = randomPartition(arr, left, right);
            doSort(arr, left, pivot - 1);
            doSort(arr, pivot, right);
        }
    }

    private static <T extends Comparable<T>> int randomPartition(T[] arr, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(arr, randomIndex, right);
        return partition(arr, left, right);
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) >>> 1;
        T pivot = array[mid];

        while (left <= right) {
            while (less(array[left], pivot)) {
                ++left;
            }
            while (less(pivot, array[right])) {
                --right;
            }
            if (left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }

    public static <T extends Comparable<T>> boolean less(T firstElement, T secondElement) {
        return firstElement.compareTo(secondElement) < 0;
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
