package com.wbx._01_sort;

import com.wbx.tools.Student;

import java.text.DecimalFormat;

/**
 * @describe：
 * @Date：2019/9/24 17:36
 * @author：wbx
 */
public abstract class Sort<T extends Comparable> implements Comparable<Sort<T>> {

	protected T[] array;

	//比较次数
	private int cmpCount;

	//交换次数
	private int swapCount;

	//花费时间
	private long time;

	private DecimalFormat fmt = new DecimalFormat("#.00");
	
	public void sort(T[] array) {
		if (array == null || array.length < 2) {
			return;
		}
		
		this.array = array;
		
		long begin = System.currentTimeMillis();
		sort();
		time = System.currentTimeMillis() - begin;
	}

	protected abstract void sort();
	
	@Override
	public int compareTo(Sort o) {
		int result = (int)(time - o.time);
		if (result != 0) {
			return result;
		}
		
		result = cmpCount - o.cmpCount;
		if (result != 0) {
			return result;
		}
		
		return swapCount - o.swapCount;
	}
	


	/**
	 * 返回值等于0，代表 array[i1] == array[i2]
	 * 返回值小于0，代表 array[i1] < array[i2]
	 * 返回值大于0，代表 array[i1] > array[i2]
	 */
	protected int cmp(int i1, int i2) {
		cmpCount++;
		return array[i1].compareTo(array[i2]);
	}
	
	protected int cmp(T v1, T v2) {
		cmpCount++;
		return v1.compareTo(v2);
	}
	
	protected void swap(int i1, int i2) {
		swapCount++;
		T tmp = array[i1];
		array[i1] = array[i2];
		array[i2] = tmp;
	}
	
	@Override
	public String toString() { 
		String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
		String compareCountStr = "比较：" + numberString(cmpCount);
		String swapCountStr = "交换：" + numberString(swapCount);
		String stableStr = "稳定性：" + isStable();
		return "【" + getClass().getSimpleName() + "】\n"
				+ stableStr + " \t"
				+ timeStr + " \t"
				+ compareCountStr + " \t "
				+ swapCountStr + "\n"
				+ "------------------------------------------------------------------";

	}

	private String numberString(int number) {
		if (number < 10000) {
			return "" + number;
		}
		
		if (number < 100000000) {
			return fmt.format(number / 10000.0) + "万";
		}
		return fmt.format(number / 100000000.0) + "亿";
	}

	private boolean isStable() {
		Student[] students = new Student[20];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(i * 10, 10);
		}

		//根据age来排序
		sort((T[]) students);

		for (int i = 1; i < students.length; i++) {
			int score = students[i].getScore();
			int prevScore = students[i - 1].getScore();
			if (score != prevScore + 10) {
				return false;
			}
		}
		return true;
	}
}
