package com.hjk.custom.utils.algorithms.etc.backtracking;

import java.util.*;

public class Permutations {

    public static void main(String... args) {
        System.out.println(1 ^ 3);
    }

    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        for (int n: nums) {
            int size = result.size();
            while(size > 0) {
                List<Integer> current = result.pollFirst();
                System.out.println(current);
                for (int i = 0; i <= current.size(); i++) {
                    List<Integer> temp = new ArrayList<Integer>(current);
                    temp.add(i, n);
                    result.add(temp);
                }
                size--;
            }
        }

        return result;
    }
}
