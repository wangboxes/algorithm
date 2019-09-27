package com.wbx._01_sort;

import com.wbx._01_sort.comparison.*;
import com.wbx.tools.Integers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @describe： 排序性能对比测试
 * @Date：2019-09-26 23:58
 * @author：wbx
 */
public class SortPerformanceTest {


	@Test
	public void testSortPerformance() {
		Integer[] array = Integers.random(20000, 1, 20000);
		
		testSorts(array, 
				new _01_BubbleSort1(),
				new _01_BubbleSort2(),
				new _02_SelectionSort(),
				new _03_HeapSort(),
				new _01_BubbleSort3(),
				new _04_InsertionSort1(),
				new _04_InsertionSort2());
	}
	
	static void testSorts(Integer[] array, Sort... sorts) {

		for (Sort sort : sorts) {

			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);

			//是否有进行升序排序
			Assert.assertTrue(Integers.isAscOrder(newArray));

		}

		//覆写了compareTo方法
		Arrays.sort(sorts);
		
		for (Sort sort : sorts) {
			System.out.println(sort);
		}
	}



}
