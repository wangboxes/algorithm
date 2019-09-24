package com.wbx._01_sort;

public class _01_BubbleSort2 extends Sort {

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
