package com.hjk.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

public class TestOne<T> {

    private static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String... args) {
        list.add(1);
        list.add(2);

        Iterator<Integer> iter = list.iterator();
        while(iter.hasNext()) {
            var test = iter.next();
            System.out.println(test);
        }

        var test = Collections.max(list);
        System.out.println(test);
    }

    public void test () {
    }
}
