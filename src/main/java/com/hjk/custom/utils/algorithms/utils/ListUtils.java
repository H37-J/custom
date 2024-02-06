package com.hjk.custom.utils.algorithms.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtils<T> {

    public List<T> list = new ArrayList<>();

    public static void main(String... args) {
        var list = new ListUtils<Integer>();
        list.addAll(new Integer[]{1,2,3});
        list.print();
    }

    public List<T> get() {
        return this.list;
    }

    public void add(T val) {
        list.add(val);
    }

    public void addAll(T... val) {
        list.addAll(new ArrayList<>(Arrays.asList(val)));
    }

    public void remove(T val) {
        list.remove(val);
    }

    public void print() {
        System.out.println(this.list);
    }

}
