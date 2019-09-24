package com.wbx._01_sort;

public class _01_BubbleSort1 extends Sort {

    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {

            for (int begin = 1; begin <= end; begin++) {
                //稳定排序
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }

        }
    }

}
