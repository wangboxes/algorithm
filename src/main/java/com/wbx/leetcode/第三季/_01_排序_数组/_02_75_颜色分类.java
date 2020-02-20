package com.wbx.leetcode.第三季._01_排序_数组;

/**
 * @describe： https://leetcode-cn.com/problems/sort-colors/
 * @Date：2020/2/20 15:20
 * @author：wbx
 *
 * 有叫做：荷兰国旗问题
 */
public class _02_75_颜色分类 {

    /*
     * 一个只包含0、1、2的整型数组，要求对它进行【原地】排序
     * 你能想出一个仅使用常数空间的一趟扫描算法吗？
     *
     * 空间复杂度O(1)，时间复杂度O(n)
     */
    public void sortColors(int[] nums) {

        //采用三指针
        int i = 0;
        int l = 0;
        int r = nums.length - 1;

        while (i <= r){
            int v = nums[i];

            if (v == 0) {
                swap(nums, i++, l++);
            } else if (v == 1) {
                i ++;
            } else { //v == 2
                swap(nums, i, r--);
            }
        }

    }


    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
