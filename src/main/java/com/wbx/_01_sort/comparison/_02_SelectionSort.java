package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

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