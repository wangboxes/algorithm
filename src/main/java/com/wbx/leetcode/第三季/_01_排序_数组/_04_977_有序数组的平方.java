package com.wbx.leetcode.第三季._01_排序_数组;

/**
 * @describe： https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 * @Date：2020/2/21 16:33
 * @author：wbx
 */
public class _04_977_有序数组的平方 {

    //双指针，大的值放到新的数组的后面
    public int[] sortedSquares(int[] A) {

        //左边的指针
        int left = 0;
        //右边的指针
        int right = A.length - 1;
        int[] result = new int[A.length];

        //用于存放平方值到新数组的指针
        int i = A.length - 1;
        while (i >= 0) {
            int v1 = A[left] * A[left];
            int v2 = A[right] * A[right];

            if (v1 > v2) {
                result[i] = v1;
                left++;
            } else {
                result[i] = v2;
                right--;
            }

            i--;
        }

        return result;
    }
}
