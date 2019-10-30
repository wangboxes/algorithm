package com.wbx.algorithm._01_sort.comparison;

import com.wbx.algorithm._01_sort.Sort;

/**
 * @describe：
 * @Date：2019-09-24 21:13
 * @author：wbx
 *
 * 优化1 :思路是将【交换】 转为【挪动】
 * ① 先将待插入的元素备份
 * ② 头部有序数据中比待插入元素大的，都朝尾部方向挪动1个位置
 * ③ 将待插入元素放到最终的合适位置
 */
public class _04_InsertionSort2<T extends Comparable> extends Sort<T> {


    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            //① 先将待插入的元素备份
            T v = array[cur];
            while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
                //通过移位来完成排序,可以减少交换次数
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = v;
        }
    }

    /*@Override
    protected void sort() {

        for (int begin = 1; begin < array.length; begin++) {
            T cur = array[begin];
            int insertIndex = begin;

            for (int end = begin; end > 0; end--) {
                if (cmp(cur, array[end - 1]) < 0) {
                    //通过移位来完成排序,可以减少交换次数
                    array[end] = array[end-1];
                    insertIndex = end -1;
                }
            }
            array[insertIndex] = cur;

        }
    }*/


}
