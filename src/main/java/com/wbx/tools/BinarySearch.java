package com.wbx.tools;

/**
 * @describe： 二分搜索
 * @Date：2019/9/27 10:40
 * @author：wbx
 *
 * 如何确定一个元素在数组中的位置？（假设数组里面全都是整数）
 * 如果是无序数组，从第 0 个位置开始遍历搜索，平均时间复杂度： O(n)
 * 如果是有序数组，可以使用二分搜索，最坏时间复杂度： O(logn)
 */
public class BinarySearch {


    /**
     * 查找v在有序数组array中的位置
     *
     *
     * 思路:
     * 假设在 [begin, end) 范围内搜索某个元素 v， mid == (begin + end) / 2
     * ◼ 如果 v < m，去 [begin, mid) 范围内二分搜索
     * ◼ 如果 v > m，去 [mid + 1, end) 范围内二分搜索
     * ◼ 如果 v == m，直接返回 mid
     *
     * 思考:
     * 如果存在多个重复的值，返回的是哪一个？
     * ✓ 不确定
     *
     */
    public static int indexOf(int[] array, int v) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            //用移位代替除法
            int mid = (end + begin) >> 1;
            //int mid = end - ((end - begin) >> 1);//不行
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找v在有序数组array中待插入位置
     * 要求二分搜索返回的插入位置：第1个大于 v 的元素位置
     *
     * 思路:
     * ◼ 假设在 [begin, end) 范围内搜索某个元素 v， mid == (begin + end) / 2
     * ◼ 如果 v < m，去 [begin, mid) 范围内二分搜索
     * ◼ 如果 v ≥ m，去 [mid + 1, end) 范围内二分搜索
     */
    public static int search(int[] array, int v) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int mid = (begin + end) >> 1;

            if (v < array[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }




    public static void main(String[] args) {
        int end = 6;
        int begin = 5;
        int mid1 = (end + begin) >> 1;
        System.out.println("mid1 = " + mid1);

        int mid2 = end - ((end - begin) >> 1);
        System.out.println("mid2 = " + mid2);
        int mid3 = begin - ((begin - end) >> 1);
        System.out.println("mid3 = " + mid3);
    }

}
