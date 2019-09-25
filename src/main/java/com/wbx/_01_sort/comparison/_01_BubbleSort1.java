package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 冒泡排序
 * 最好       最坏      平均      额外空间复杂的     in-place        稳定性
 * O（n）     O（n2）   O（n2）       O(1)            是             是
 *
 * 1 从头开始比较每一对相邻元素，如果第1个比第2个大，就交换它们的位置
 * ✓ 执行完一轮后，最末尾那个元素就是最大的元素
 * 2 忽略 1 中曾经找到的最大元素，重复执行步骤 1，直到全部元素有序
 *
 * @Date：2019/9/24 17:36
 * @author：wbx
 */
public class _01_BubbleSort1<T extends Comparable> extends Sort<T> {

    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {

            for (int begin = 1; begin <= end; begin++) {
                //稳定排序，如果写成 cmp(begin, begin - 1) <= 0 就是不稳定的排序算法
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }

        }
    }

}

/**
 *名称解释：
 * 稳定性（Stability）：如果相等的2个元素，在排序前后的相对位置保持不变，那么这是稳定的排序算法
 *
 * 原地算法（In-place Algorithm）：不依赖额外的资源或者依赖少数的额外资源，仅依靠输出来覆盖输入
 * 空间复杂度为 𝑂(1) 的都可以认为是原地算法
 *
 * 非原地算法，称为 Not-in-place 或者 Out-of-place
 *
 *
 * */
