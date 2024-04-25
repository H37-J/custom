package com.hjk.custom.utils.algorithms.etc.math;

public class MaxSubArray {

    public static void main(String... args) {
        maxsub(new int[]{4,-1,1,2});
    }

    public static int maxsub(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];

        int max = dp[0];

        for(int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (Math.max(dp[i - 1], 0));
            max = Math.max(dp[i], max);
        }

        return max;
    }
}
