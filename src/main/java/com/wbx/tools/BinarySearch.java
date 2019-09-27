package com.wbx.tools;

/**
 * @describe： 二分搜索
 * @Date：2019/9/27 10:40
 * @author：wbx
 */
public class BinarySearch {


    /**
     * 查找value在有序数组orderedArray中的位置
     *
     * @param orderedArray
     * @param v
     * @return value在有序数组orderedArray中的index
     *
     * 思路:
     * 假设在 [begin, end) 范围内搜索某个元素 v， mid == (begin + end) / 2
     * ◼ 如果 v < m，去 [begin, mid) 范围内二分搜索
     * ◼ 如果 v > m，去 [mid + 1, end) 范围内二分搜索
     * ◼ 如果 v == m，直接返回 mid
     *
     */
    public static int indexOf(int[] orderedArray, int v) {
        if (orderedArray == null || orderedArray.length == 0) {
            return -1;
        }
        int begin = 0;
        int end = orderedArray.length;

        while (end > begin) {
            //用移位代替除法
            int mid = (end + begin) >> 1;
            //int mid = end - ((end - begin) >> 1);
            if (v < orderedArray[mid]) {
                end = mid;

            } else if (v > orderedArray[mid]) {
                begin = mid + 1;

            } else {
                return mid;

            }
        }

        return -1;
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
