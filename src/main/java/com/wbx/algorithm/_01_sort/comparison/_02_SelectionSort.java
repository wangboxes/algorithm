package com.wbx.algorithm._01_sort.comparison;

import com.wbx.algorithm._01_sort.Sort;

/**
 * 选择排序:
 * 执行流程
 * 		① 从序列中找出最大的那个元素，然后与最末尾的元素交换位置
 * 		✓ 执行完一轮后，最末尾的那个元素就是最大的元素
 *
 * 		② 忽略 ① 中曾经找到的最大元素，重复执行步骤 ①
 *
 *
 * 	优化:遇到找最大最小值,想到堆,所以可以使用堆来选择最大值---->堆排序
 *
 * 	总结:
 * 	选择排序的交换次数要远远少于冒泡排序，平均性能优于冒泡排序
 * 	最好、最坏、平均时间复杂度： O(n2)，空间复杂度： O(1)，属于不稳定排序(如: 3,5,10,2,1,2)
 */
public class _02_SelectionSort<T extends Comparable> extends Sort<T> {

	@Override
	protected void sort() {

		for (int end = array.length - 1; end > 0; end--) {
			int maxIndex = 0;

			for (int begin = 1; begin <= end; begin++) {
				// if (array[maxIndex] <= array[begin]) {
				if (cmp(maxIndex, begin) <= 0) {
					maxIndex = begin;
				}
			}

			swap(maxIndex, end);
		}
	}

}
