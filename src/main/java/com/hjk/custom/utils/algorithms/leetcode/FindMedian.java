package com.hjk.custom.utils.algorithms.leetcode;
import java.util.ArrayList;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;
public class FindMedian {

    public static void main(String... args) {
        findMedianSortedArrays(new int[]{1,3}, new int[]{2,4});
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        var list = new ArrayList<Integer>();
        int size1 = nums1.length, size2 = nums2.length;
        int index1 = 0, index2 = 0;
        while(index1 < size1 && index2 < size2) {
           if(nums1[index1]<= nums2[index2]) {
               list.add(nums1[index1++]);
           } else {
               list.add(nums2[index2++]);
           }
        }
        if(index1 < size1) {
            for(int i = index1; i < size1; i++) {
                list.add(nums1[i]);
            }
        }
        if(index2 < size2) {
            for(int i = index2; i < size2; i++) {
                list.add(nums2[i]);
            }
        }
        return list.size() % 2 == 1 ? list.get(list.size() / 2) : (double) (list.get(list.size() / 2) + list.get((list.size() / 2) - 1)) / 2;
    }
}
