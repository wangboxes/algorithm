package com.wbx._01_sort.notComparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 计数排序
 * 计数排序于1954年由Harold H. Seward提出，适合对 "一定范围内" 的 "整数" 进行排序(双引号中的字是重点)
 * @Date：2019-10-08 20:57
 * @author：wbx
 *
 *
 * ◼ 之前学习的冒泡、选择、插入、归并、快速、希尔、堆排序，都是基于比较的排序
 * 平均时间复杂度目前最低是 O(nlogn)
 * ◼ 计数排序、桶排序、基数排序，都不是基于比较的排序
 * 它们是典型的用空间换时间，在某些时候，平均时间复杂度可以比 O(nlogn) 更低
 *
 *
 * ◼ 计数排序的核心思想
 * 统计每个整数在序列中出现的次数，进而推导出每个整数在有序序列中的索引
 */
public class _08_CountingSort1 extends Sort<Integer> {

    @Override
    protected void sort() {
        //查找最大值 O(n)
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        //统计元素出现的次数 O(n)
        int[] counts = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }

        //按顺序赋值 O(k)
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                array[index++] = i;
            }

        }

    }

    /**
     * ◼ 这个版本的实现 存在 以下问题:
     * 1.无法对负整数进行排序
     * 2.极其浪费内存空间
     * 3.是个不稳定的排序
     */
}
