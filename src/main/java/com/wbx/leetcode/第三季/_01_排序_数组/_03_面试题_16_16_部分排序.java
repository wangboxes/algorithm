package com.wbx.leetcode.第三季._01_排序_数组;

/**
 * @describe： https://leetcode-cn.com/problems/sub-sort-lcci/
 * @Date：2020/2/20 15:52
 * @author：wbx
 *
 * 两次循环
 * 第一次：从左扫描到右寻找逆序对（正序：逐渐变大），找到最右的那个逆序值所在的index
 * 第二次：从右扫描到左寻找逆序对（正序：逐渐变小），找到最左的那个逆序值所在的index
 */
public class _03_面试题_16_16_部分排序 {


    public int[] subSort(int[] array) {
        if (array.length == 0) {
            return new int[]{-1, -1};
        }

        // 从左扫描到右寻找逆序对（正序：逐渐变大）
        int max = array[0];
        // 用来记录最右的那个逆序对位置
        int r = -1;


        for (int i = 1; i < array.length; i++) {
            int v = array[i];
            if (v >= max) {
                max = v;
            } else {
                r = i;
            }
        }

        //证明数组本来就是有序的，提前结束
        if (r == -1) {
            return new int[]{-1, -1};
        }


        // 从右扫描到左寻找逆序对（正序：逐渐变小）
        int min = array[array.length - 1];
        // 用来记录最左的那个逆序对位置
        int l = -1;

        for (int i = array.length - 2; i >= 0; i--){
            int v = array[i];
            if (v <= min){
                min = v;
            } else {
            //注意：当前值和最值相等，不要记录
                l = i;
            }
        }

        return new int[]{l, r};
    }

}
