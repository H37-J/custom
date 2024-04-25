package com.hjk.custom.utils.algorithms.etc.math;

public class MaximumProductSubArray {

    public static void main(String... args) {
        maxProduct(new int[]{2,-1,6,2});
    }

    public static int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int result = nums[0];
        int max = nums[0];
        int min = nums[0];

        for(int i = 1; i < nums.length; i++) {
            int temp = max;
            System.out.println(max + "," + min + "," + nums[i]);
            max = Math.max(Math.max(nums[i] * max, nums[i] * min), nums[i]);
            min = Math.min(Math.min(nums[i] * temp, nums[i] * min), nums[i]);

            if(max > result) {
                result = max;
            }
        }
        System.out.println(result);
        return result;
    }
}
