package com.wbx.algorithm._01_sort.notComparison;

import com.wbx.algorithm._01_sort.Sort;

/**
 * @describe： 基数排序（Radix Sort）--->排序的时候使用 计数排序
 * @Date：2019/10/12 16:14
 * @author：wbx
 *
 * ◼ 基数排序非常适合用于整数排序（尤其是非负整数），因此本这里只演示对非负整数进行基数排序
 * ◼ 执行流程：依次对个位数、十位数、百位数、千位数、万位数...进行排序（从低位到高位,注意:不能从高位到低位）
 * 例子:
 * 126 69 593 23 6 89 54 8
 *
 * 593 23 54 126 6 8 69 89  对个位排序
 * 6 8 23 126 54 69 89 593  对十位排序
 * 6 8 23 54 69 89 126 593  对百位排序
 *
 * ◼ 个位数、十位数、百位数的取值范围都是固定的0~9，可以使用计数排序对它们进行排序
 *
 *
 * ◼ 最好、最坏、平均时间复杂度： O(d ∗ (n + k)) ， d 是最大值的位数， k 是进制。属于稳定排序
 * ◼ 空间复杂度： O(n + k)， k 是进制
 */
public class _09_RadixSort1 extends Sort<Integer> {

    @Override
    protected void sort() {
        // 找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        //比如:593
        // 个位数: array[i] / 1 % 10 = 3
        // 十位数：array[i] / 10 % 10 = 9
        // 百位数：array[i] / 100 % 10 = 5
        // 千位数：array[i] / 1000 % 10 = ...

        //divider 除数
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider);
        }

    }

    private void countingSort(int divider) {
        // 开辟内存空间，存储次数
        int[] counts = new int[10];

        // 统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            //关键代码
            counts[array[i] / divider % 10]++;
        }

        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从后往前遍历元素，将它放到有序数组中的合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] / divider % 10]] = array[i];
        }

        // 将有序数组赋值到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }


}
