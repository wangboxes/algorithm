package com.wbx._01_sort.notComparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 计数排序
 * @Date：2019-10-08 20:57
 * @author：wbx
 *
 * 改进思路：
 * ◼假设array中的最小值是 min
 * ◼array中的元素 k 对应的 counts 索引是 k – min
 * ◼array中的元素 k 在有序序列中的索引 : counts[k – min] – p , p 代表着是倒数第几个 k
 *
 * ◼ 最好、最坏、平均时间复杂度： O(n + k)
 * ◼ 空间复杂度： O(n + k)
 * ◼ k 是整数的取值范围
 * ◼ 属于稳定排序
 */
public class _08_CountingSort2 extends Sort<Integer> {

    @Override
    protected void sort() {
        //查找最大值，最小值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 开辟内存空间，存储次数(左闭右闭[min,max])-->优化点1：可以对负整数排序；优化点2：减少内存浪费；
        int[] counts = new int[max - min + 1];

        //统计元素出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }

        // 累加次数(注意：i从1开始)
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从后往前遍历元素（这样做是为了稳定性，如果是从前往后就会失去稳定性），将它放到有序数组中的合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            //关键代码
            newArray[--counts[array[i] - min]] = array[i];
        }


        // 将有序数组复制到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }

    }

    /**
     * ◼ 这个版本的实现 解决 以下问题:
     * 1.无法对负整数进行排序
     * 2.极其浪费内存空间
     * 3.是个不稳定的排序
     */
}
