package com.hjk.custom.utils.algorithms.leetcode;

import java.util.HashMap;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class LongestSubstring {

    public static void main(String... args) {
        lengthOfLongestSubstring("dvdf");
    }

    public static int lengthlengthOfLongestSubstringTwo(String s) {
        if(s.isEmpty()) return 0;
        var arr = s.split("");
        var dp = new int[arr.length];

        return 1;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        int index = 0;
        int max = Integer.MIN_VALUE;
        var arr = s.split("");
        var map = new HashMap<String, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                max = Math.max(max, i - index);
                i = index++;
                map.clear();
            } else {
                map.put(arr[i], i);
            }
        }
        max = Math.max(max, arr.length - index);
        return max;
    }
}
