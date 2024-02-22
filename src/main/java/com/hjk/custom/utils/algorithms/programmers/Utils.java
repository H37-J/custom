package com.hjk.custom.utils.algorithms.programmers;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Utils {
    public static <T> List<T> ArrayToList(T[] arr) {
        return new ArrayList<>(Arrays.stream(arr).collect(Collectors.toList()));
    }

    public static <Integer> void groupMap(Integer[] arr) {
        Map<Integer, Long> map = Arrays.stream(arr)
                .collect(Collectors.groupingBy(val -> val, Collectors.counting()));
        Map<Integer, List<Integer>> map1 = Arrays.stream(arr)
                .collect(Collectors.groupingBy(val -> val));
        print(map);
    }


    public static int[] range(int[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end);
    }

    public static <T> T[] range(T[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end);
    }

    public static <T> void swap(T[] arr, int index1, int index2) {
        var temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void swap(List<String> list, int index1, int index2) {
        var temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    public static void print(int val) {
        System.out.println(val);
    }

    public static <T> void print(T val) {
        System.out.println(val);
    }

    public static <T> void print(int val1, int val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static <T> void print(int val1, String val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static <T> void print(String val1, int val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static <T> void print(T val1, T val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static <T> void print(T val1, T val2, T val3) {
        System.out.print(val1 + ",");
        System.out.print(val2 + ",");
        System.out.print(val3);
        System.out.println();
    }

    public static <T> void print(T val1, T val2, T val3, T val4) {
        System.out.print(val1 + ",");
        System.out.print(val2 + ",");
        System.out.print(val3 + ",");
        System.out.print(val4);
        System.out.println();
    }

    public static void print(int[] arr) {
        var joiner = new StringJoiner(",");
        for (var val : arr) {
            joiner.add(String.valueOf(val));
        }
        System.out.println(joiner.toString());
    }

    public static void print(String[] arr) {
        var joiner = new StringJoiner(",");
        for (var val : arr) {
            joiner.add(String.valueOf(val));
        }
        System.out.println(joiner.toString());
    }

    public static <T> void print(T[] arr) {
        var joiner = new StringJoiner(",");
        for (var val : arr) {
            joiner.add(String.valueOf(val));
        }
        System.out.println(joiner.toString());
    }

    public static void print(char[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(boolean[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(char val) {
        System.out.println(val);
    }

    public static <T> void print(PriorityQueue<T> queue) {
        var joiner = new StringJoiner(",");
        while(!queue.isEmpty()) {
            joiner.add(String.valueOf(queue.poll()));
        }
        System.out.println(joiner.toString());
    }

    public static <T> void print(T[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static <T, R> void print(Map<T, R> map) {
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }

    public static void consumer() {
        Consumer<String> print1 = System.out::println;
        Consumer<String> print2 = (val) -> {
            System.out.println("val2");
        };
        print1.andThen(print2).accept("val1");
    }

    public static int findIndex(int[] arr, int find) {
        int index = 0;
        for (var val : arr) {
            if (val == find) {
                return index;
            } else {
                index += 1;
            }
        }
        return -1;
    }

    public static int[] getRandom(int size, int range) {
        Random random = new Random();
        var arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = random.nextInt(range + 1);
        }
        return arr;
    }

    public static void time1() {
        long start = System.currentTimeMillis();

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Execution time: " + duration + "ms");
    }

    public static void test() {
        int size = 100000000;
        var arr = new int[size];
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            arr[i] = i;
        }
        int num = 0;
        for(int i = 0; i < size; i++) {
            num = arr[i];
        }
    }

}
