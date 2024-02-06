package com.hjk.custom.utils.algorithms.utils;

import java.util.*;
import java.util.stream.Stream;

public class Utils {

    public static void main(String... args) {
//        setAll(5);
//        sort();
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(int[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }

    public static void setAll(int size) {
        int[] arr = new int[size];
        Arrays.setAll(arr, i -> (int) (Math.random() * 5) + 1);
        print(arr);
    }

    public static void toList() {
        List<Integer> list1 = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
    }

    public static void sort() {
        String[] arr = {"a", "b", "c"};
        System.out.println(Arrays.toString(arr));
    }

    public static void treeset() {
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        var min = set.headSet(3);
        System.out.println(min);
    }

    public static void test1() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        list.removeIf(val -> val % 2 == 0 || val % 3 == 0);
        System.out.println(list.toString());
        list.replaceAll(val -> val * 10);
    }

    public static void test2() {
        String[] arr = {"a", "b", "C", "d"};
        String[] temp = Arrays.stream(arr)
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .toArray(String[]::new);
        System.out.println(Arrays.toString(temp));
    }

    public static void test3() {
        Stream<String[]> StreamArray = Stream.of(new String[]{"abc"}, new String[]{"dfg"});
        Stream<String> stream = StreamArray.flatMap(Arrays::stream);
    }
}