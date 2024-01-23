package com.hjk.custom.utils.algorithms.clazz;

import java.util.ArrayList;

public class Box<T> {
    private final ArrayList<T> list = new ArrayList<>();

    public void add(T element) {
        list.add(element);
    }

    public T get (int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public String toString() {
        return list.toString();
    }
}
