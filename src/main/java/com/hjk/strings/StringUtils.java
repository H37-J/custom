package com.hjk.strings;

import java.util.List;

public class StringUtils {

    public static void main(String... args) {
        System.out.println("test");
    }

    public static<T> void print(T val) {
        System.out.println(val);
    }

    public static<T> void print(T[] val) {
        for(T v : val) {
            print(v);
        }
    }

    public static<T> void print(List<T> list) {
        list.forEach(StringUtils::print);
    }
}
