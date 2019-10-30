package com.wbx.algorithm._01_sort.comparison;

import com.wbx.algorithm._01_sort.Sort;

/**
 * 优化2：如果序列尾部已经局部有序，可以记录最后1次交换的位置，减少比较次数
 * @Date：2019/9/24 17:36
 * @author：wbx
 */
public class _01_BubbleSort3<T extends Comparable> extends Sort<T> {

    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {
            //针对尾部数据部分有序的优化,减少遍历范围
            int sortedIndex = 1;

            for (int begin = 1; begin <= end; begin++) {
                //稳定排序
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);

                    sortedIndex = begin;
                }
            }

            end = sortedIndex;
        }
    }

}
