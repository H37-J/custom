package com.hjk.custom.utils.algorithms.etc.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static void main(String... args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(4,1,5,2));
        sort(list);
    }

    public static List<Integer> sort(List<Integer> list) {
        if(list.size() == 1) {
            return list;
        }

        int size = list.size();
        int median = size >>> 1;

        var listA = list.subList(0, median);
        var listB = list.subList(median, size);
        listA = sort(listA);
        listB = sort(listB);

        var newList = merge(listA, listB);
        return newList;
    }

    public static List<Integer> merge(List<Integer> listA, List<Integer> listB) {

        return listA;
    }
}
