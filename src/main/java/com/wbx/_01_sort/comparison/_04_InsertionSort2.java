package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 插入排序(类似扑克牌)
 * @Date：2019-09-24 21:13
 * @author：wbx
 */
public class _04_InsertionSort2<T extends Comparable> extends Sort<T> {


    @Override
    protected void sort() {

        for (int begin = 1; begin < array.length; begin++) {

            for (int end = begin; end > 0; end--) {
                if (cmp(array[end], array[end - 1]) < 0) {
                    //通过移位来完成排序,可以减少交换次数
                    swap(end, end - 1);
                }
            }

        }
    }
}
