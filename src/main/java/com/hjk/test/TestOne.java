package com.hjk.test;

import java.util.ArrayList;
import java.util.Collections;

public class TestOne {

    private static final ArrayList<Integer> list = new ArrayList<>();

    public static void main(String... args) {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        for (Integer test : list) {
            System.out.println(test);
        }

        var test = Collections.max(list);
        System.out.println(test);
    }

}
