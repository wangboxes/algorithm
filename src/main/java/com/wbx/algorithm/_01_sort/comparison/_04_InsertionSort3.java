package com.wbx.algorithm._01_sort.comparison;

import com.wbx.algorithm._01_sort.Sort;

/**
 * @describe：
 * @Date：2019-09-24 21:13
 * @author：wbx 优化2
 * 思路是:在元素 v 的插入过程中，可以先二分搜索出合适的插入位置，然后再将元素 v 插入
 * 要求二分搜索返回的插入位置：第1个大于 v 的元素位置
 *
 * 需要注意的是，使用了二分搜索后，只是减少了比较次数，但插入排序的平均时间复杂度依然是 O(n2)
 */
public class _04_InsertionSort3<T extends Comparable> extends Sort<T> {

    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            insert(begin, search(begin));
        }
    }

    /**
     * 将source位置的元素插入到dest位置
     *
     * @param source
     * @param dest
     */
    private void insert(int source, int dest) {
        T v = array[source];
        //移位(从后往前移)
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = v;
    }

    /**
     * 利用二分搜索找到 index 位置元素的待插入位置
     * 已经排好序数组的区间范围是 [0, index)
     *
     * @param index
     * @return
     */
    private int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }


    /*    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            T v = array[begin];
            int insertIndex = search(begin);
            // 将 [insertIndex, begin) 范围内的元素往右边挪动一个单位
//			for (int i = begin - 1; i >= insertIndex; i--) {
//
//			}
            for (int i = begin; i > insertIndex; i--) {
                array[i] = array[i - 1];
            }
            array[insertIndex] = v;
        }
    }*/

}
