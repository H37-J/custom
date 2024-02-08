package com.hjk.custom.utils.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main (String... args) {
        var res = hanoi(3,1,3);
        for(var r : res) {
            System.out.println(Arrays.toString(r));
        }
    }

    public static String t1(String s) {
        return s.chars()
                .boxed()
                .sorted((v1, v2) -> v2 - v1)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public static void t2() {
        String[] arr = {"test", "test2"};
        Arrays.stream(arr).mapToInt(Integer::parseInt)
                .forEach(System.out::println);
    }

    public static void t3() {
        String s = "BACDE";
        s = s.replaceAll("[^" + "CBD" + "]", "");
        System.out.println(s);
    }

    public static List<int[]> hanoi(int n, int from, int to) {
        if (n == 1) return List.of(new int[] {from, to});

        int empty = 6 - from - to;

        List<int[]> result = new ArrayList<>();
        result.addAll(hanoi(n - 1, from, empty));
        result.addAll(hanoi(1, from, to));
        result.addAll(hanoi(n - 1, empty, to));
        return result;
    }


}
