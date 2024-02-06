package com.hjk.custom.utils.algorithms;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Algos {

    private static int call = 0;

    public static void main(String... args) {
        res();
    }

    public static void res() {
    }

    public static boolean anagrams(String first, String second) {
        Map<Character, Integer> fMap = new HashMap<Character, Integer>();
        Map<Character, Integer> sMap = new HashMap<Character, Integer>();
        for (var c : first.toCharArray()) {
            fMap.put(c, fMap.getOrDefault(c, 0) + 1);
        }
        for (var c : second.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        return fMap.equals(sMap);
    }

    public static boolean equals(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static boolean contains(String s, String find) {
        return s.contains(find);
    }

    public static String replaceNumber(String s) {
        return s.replaceAll("[^a-zA-Z]", "");
    }

    public static String replaceStirng(String s) {
        return s.replaceAll("[^0-9]", "");
    }


    public static void count(String s) {
        var map = new HashMap<Character, Integer>();
        IntStream.range(0, s.length())
                .forEach(index -> {
                    map.put(s.charAt(index), map.getOrDefault(s.charAt(index), 0) + 1);
                });
        print(map);
    }

    public static void compare() {
        int[] arr = {3, 1, 4};
        var res = Arrays.stream(arr)
                .boxed()
                .sorted(Algos::compare)
                .toArray();
    }

    public static String compare(int val1) {
        return String.valueOf(val1);
    }

    public static int compare(String val1, String val2) {
        return val1.compareTo(val2);
    }

    public static int compare(int val1, int val2) {
        if (val1 == val2) {
            return 0;
        }
        return val1 - val2;
    }

    public static void permutation() {
        List<String> answers = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();
        Boolean[] visited = new Boolean[4];
        Arrays.fill(visited, false);
        permutation("", new String[]{"a", "b", "c", "d"}, 0, answers, 2, visited);
        print(answers);
    }

    public static void permutation(String current, String[] arr, int currentIndex, List<String> answers, int count, Boolean[] visited) {
        if (current.length() == count) {
            answers.add(current);
            return;
        }

        if (!current.isEmpty()) {
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) continue;
                permutation(current.concat(arr[i]), arr, i, answers, count, visited);
                visited[i] = false;
            }
        }

        if (current.isEmpty()) {
            for (int i = 0; i < arr.length; i++) {
                visited[i] = true;
                permutation(current.concat(arr[i]), arr, i, answers, count, visited);
                visited[i] = false;
            }
        }
    }

    public static void combination(String current, String[] arr, int currentIndex, List<String> answers, int count) {
        if (current.length() == count) {
            answers.add(current);
        }
        for (int i = currentIndex; i < arr.length; i++) {
            combination(current.concat(arr[i]), arr, i + 1, answers, count);
        }
    }

    public static void combination(int sum, int[] arr, int currentIndex, List<Integer> answers, int count) {
        if (count == 2) {
            answers.add(sum);
        }
        for (int i = currentIndex; i < arr.length; i++) {
            combination(sum + arr[i], arr, i + 1, answers, count + 1);
        }
    }


    public static int fibo(int index) {
        if (index == 0 || index == 1) return 1;
        return fibo(index - 1) + fibo(index - 2);
    }

    public static List<Integer> fiboList(int size) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 1));
        for (int i = 2; i <= size; i++) {
            list.add(list.get(i - 1) + list.get(i - 2));
        }
        return list;
    }

    public static int add(String s) {
        AtomicInteger sum = new AtomicInteger();
        s.chars().forEach(val -> sum.addAndGet(val - '0'));
        return sum.get();
    }

    public static int add(int number) {
        return add(String.valueOf(number));
    }

    public static String sort(String s) {
        return s.chars()
                .boxed()
                .sorted((val1, val2) -> val2 - val1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void add() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
            call += 1;
        }
        var res = String.format("%s, %s", sum, call);
        print(res);
    }

    public static int getMinValue(int... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty");
        }

        var absMinWrapper = new Object() {
            int value = numbers[0];
        };

        Arrays.stream(numbers).skip(1)
                .filter(number -> Math.abs(number)
                        < Math.abs(absMinWrapper.value))
                .forEach(number -> absMinWrapper.value = number);

        print(absMinWrapper.value);
        return absMinWrapper.value;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int swap = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = swap;
    }

    public static int getMedian(int[] arr, int start, int end) {
        var median = (start + end) >>> 1;
        return arr[median];
    }

    public static int parent(int[] arr, int index) {
        var parentIndex = (index - 1) / 2;
        return arr[parentIndex];
    }

    public static boolean contains(int[] arr, int findVal) {
        var res = Arrays.stream(arr)
                .filter(val -> val == findVal).toArray();
        return res.length >= 1;
    }

    public static List<Integer> findIndex(int[] arr, int findVal) {
        List<Integer> indexes = new ArrayList<>();
        IntStream.range(0, arr.length)
                .forEach(index -> {
                    if (arr[index] == findVal) {
                        indexes.add(index);
                    }
                });
        return indexes;
    }

    public static void print(int val) {
        System.out.println(val);
    }

    public static <T> void print(T val) {
        System.out.println(val);
    }

    public static <T> void print(T val1, T val2) {
        System.out.println(val1 + "," + val2);
    }

    public static <T> void print(T[] arr) {
        System.out.print(Arrays.toString(arr));
    }

    public static <T> void print(T[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }

    public static <K, V> void print(Map<K, V> map) {
        map.forEach((key, val) -> {
            System.out.println(key + ":" + val);
        });
    }


}
