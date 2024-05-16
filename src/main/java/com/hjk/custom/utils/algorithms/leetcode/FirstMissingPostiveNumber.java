package com.hjk.custom.utils.algorithms.leetcode;
import java.util.HashMap;
import java.util.HashSet;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class FirstMissingPostiveNumber {

    public static void main(String... args) {
        var fun = new FirstMissingPostiveNumber();
        fun.firstMissingPositive(new int[]{3,4,-1,1});
    }

    public int firstMissingPositive(int[] nums) {
        var min = 1;
        var map = new HashMap<Integer, Integer>();
        for(var num : nums) {
            map.put(num, num);
        }

        for(int i = 1; true; i++) {
            var num = map.getOrDefault(i, 0);
            if(num == 0) {
                min = i;
                break;
            }
        }
        return min;
    }
}
