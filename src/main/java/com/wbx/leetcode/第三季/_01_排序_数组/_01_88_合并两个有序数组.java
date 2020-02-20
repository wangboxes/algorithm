package com.wbx.leetcode.第三季._01_排序_数组;

/**
 * @describe： https://leetcode-cn.com/problems/merge-sorted-array/
 * @Date：2020/2/17 21:18
 * @author：wbx
 */
public class _01_88_合并两个有序数组 {

    //总体思路： 两个数组从后开始比较，比较大元素放到nums1后面
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int cur = nums1.length - 1;

        //把nums2的元素都移到num1，就代表合并完成
        while (i2 >= 0) {

            if (i1 >= 0 && nums1[i1] > nums2[i2]) {
                nums1[cur--] = nums1[i1--];

            } else { //i1 < 0 || nums1[i1] <= nums2[i2]
                nums1[cur--] = nums2[i2--];
            }
        }

    }

}
