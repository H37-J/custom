package com.hjk.custom.utils.algorithms.etc.search;

public class BinarySearch {

    public static void main(String... args) {

    }

    private static <T extends Comparable<T>> int search(T[] arr, T key, int left, int right) {
        if (right < left) {
            return -1;
        }

        int median = (left + right) >>> 1;
        int comp = arr[median].compareTo(key);

        if (comp == 0) {
            return median;
        } else if (comp < 0) {
            return search(arr, key, left, median - 1);
        } else {
            return search(arr, key, median + 1, right);
        }
    }
}
