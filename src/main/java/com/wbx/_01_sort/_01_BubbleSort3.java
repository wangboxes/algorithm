package com.wbx._01_sort;

public class _01_BubbleSort3 extends Sort {

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
