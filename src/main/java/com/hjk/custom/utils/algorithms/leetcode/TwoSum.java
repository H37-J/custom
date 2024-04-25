package com.hjk.custom.utils.algorithms.leetcode;

import java.util.*;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class TwoSum {

    public static void main(String... args) {
        twoSum(new int[]{3,2,4}, 6);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        var map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return answer;
    }
}
