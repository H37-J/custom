package com.hjk.custom.utils.algorithms.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ArraysUtils {

    public static void main(String... args) {
        Integer[] arr1= {1,2,3,4,3};
        String[] arr2 = {"ab", "bd", "ab"};
        var res1 = slice(new Integer[]{1,2,3,4},1,2);
        var res2 = find(arr1, 3);
        var res3 = find(arr2, "ab");
        print(res2);
        print(res3);
    }

    public static<T> List<Integer> find(T[] arr, T findVal) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, arr.length)
                .forEach(val -> {
                    if(arr[val] == findVal) {
                        list.add(val);
                    }
                });
        return list;
    }

    public static<T extends Comparable<T>> T[] ascSorted(T[] arr) {
        Arrays.sort(arr, Comparable::compareTo);
        return arr;
    }

    public static<T extends Comparable<T>> T[] descSorted(T[] arr) {
        Arrays.sort(arr, (val1, val2) -> val2.compareTo(val1));
        return arr;
    }

    public static Integer[] slice(Integer[] arr, int from, int count) {
        return Arrays.stream(arr)
                .skip(from)
                .limit(count)
                .toArray(Integer[]::new);
    }

    public static<T> void print(T[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static<T> void print(T[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }

    public static<T> void print(List<T> list) {
        System.out.println(list);
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T extends Comparable<T>> boolean less(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public static <T extends Comparable<T>> boolean greater(T firstElement, T secondElement) {
        return firstElement.compareTo(secondElement) > 0;
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
