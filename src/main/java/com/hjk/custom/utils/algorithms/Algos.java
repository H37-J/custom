package com.hjk.custom.utils.algorithms;

import java.util.Arrays;
import java.util.Collections;

public class Algos {

    public static void main(String... args) {
        var res = t1("test1, test2");
        System.out.println(res);
    }

    public static String t1(String s) {
        var words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static int[] rotateLefts(int[] arr, int index) {
        int size = arr.length;
        int[] result = new int[size];
        index = index % size;

        for(int i = 0; i < size; i++) {
            result[i] = arr[index];
            index = (index + 1) % size;
        }

        return result;
    }

}
