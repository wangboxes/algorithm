package com.wbx._01_sort;

import com.wbx._01_sort.comparison._04_InsertionSort1;
import com.wbx.tools.Asserts;
import com.wbx.tools.Integers;

import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		Integer[] array = Integers.random(10000, 1, 20000);
		
		testSorts(array, 
				/*new _01_BubbleSort1(),
				new _01_BubbleSort2(),
				new _02_SelectionSort(),
				new _03_HeapSort(),
				new _01_BubbleSort3(),*/
				new _04_InsertionSort1());
	}
	
	static void testSorts(Integer[] array, Sort... sorts) {

		for (Sort sort : sorts) {
			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);
			Asserts.test(Integers.isAscOrder(newArray));
		}
		
		Arrays.sort(sorts);
		
		for (Sort sort : sorts) {
			System.out.println(sort);
		}
	}




	static void selectionSort(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (array[maxIndex] <= array[begin]) {
					maxIndex = begin;
				}
			}
			int tmp = array[maxIndex];
			array[maxIndex] = array[end];
			array[end] = tmp;
		}
		
		// 8 10 9 10 
	}
	
	static void bubbleSort1(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
				}
			}
		}
	}
	
	static void bubbleSort2(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
					sorted = false;
				}
			}
			if (sorted) break;
		}
	}

	static void bubbleSort3(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			// sortedIndex的初始值在数组完全有序的时候有用
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}
}
