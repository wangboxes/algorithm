package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 插入排序(类似扑克牌的排序)
 * @Date：2019-09-24 21:13
 * @author：wbx
 *
 *  执行流程
 * ① 在执行过程中，插入排序会将序列分为2部分
 * ✓ 头部是已经排好序的，尾部是待排序的
 * ② 从头开始扫描每一个元素
 * ✓ 每当扫描到一个元素，就将它插入到头部合适的位置，使得头部数据依然保持有序
 *
 */
public class _04_InsertionSort1<T extends Comparable> extends Sort<T> {


    @Override
    protected void sort() {

        for (int begin = 1; begin < array.length; begin++) {

            for (int end = begin; end > 0; end--) {
                if (cmp(array[end], array[end - 1]) < 0) {
                    //通过交换元素的方式完成排序
                    swap(end, end - 1);
                }
            }

        }
    }




    /*@Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur - 1);
                cur--;
            }
        }
    }*/
}
