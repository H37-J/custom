package com.hjk.custom.utils.algorithms.etc.array;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String... args) {
            var res = productExceptSelf(new int[]{1,2,3,4});
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 1;

        for(int i = 0; i < nums.length; i++) {
            if(i > 0) {
                left *= nums[i - 1];
            }

            result[i] = left;
        }

        System.out.println(Arrays.toString(result));

        int right = 1;

        for(int i = n - 1; i >= 0; i--) {
            if(i < n - 1) {
                right *= nums[i + 1];
            }

            result[i] *= right;
        }

        return result;
    }
}
