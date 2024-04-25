package com.hjk.custom.utils.algorithms.etc.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Subsets {

    public static void main(String... args) {
        subsets(new int[]{1,2,3});
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        recurse(result, nums, new Stack<>(), 0);
        System.out.println(result);
        return result;
    }

    private static void recurse(List<List<Integer>> result, int[] nums, Stack path, int position) {
        if(position == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        path.push(nums[position]);

        recurse(result, nums, path, position + 1);

        path.pop();

        recurse(result, nums, path, position + 1);
    }
}
