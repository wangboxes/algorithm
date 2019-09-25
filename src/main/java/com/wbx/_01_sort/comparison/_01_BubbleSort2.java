package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

/**
 * 优化：如果序列已经完全有序，可以提前终止冒泡排序
 * @Date：2019/9/24 17:36
 * @author：wbx
 */
public class _01_BubbleSort2<T extends Comparable> extends Sort<T> {

    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {

            //针对数据全部有序的优化
            boolean sorted = true;

            for (int begin = 1; begin <= end; begin++) {
                //稳定排序
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }

            if (sorted) {
                break;
            }
        }
    }

}
