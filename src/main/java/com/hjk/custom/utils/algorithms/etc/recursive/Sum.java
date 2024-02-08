package com.hjk.custom.utils.algorithms.etc.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Sum {
    static AtomicInteger sum = new AtomicInteger();
    static List<Integer> list = new ArrayList<>();

    public static void main(String... args) {
            var res = factorial(10);
    }

    private static int factorial(int number) {
        if(number == 1) {
            list.add(1);
            return 1;
        }
        list.add(number * factorial(number - 1));
        return list.get(number - 1);
    }

}
