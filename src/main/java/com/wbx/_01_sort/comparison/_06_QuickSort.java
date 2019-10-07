package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 快速排序
 * 1960年由查尔斯·安东尼·理查德·霍尔（Charles Antony Richard Hoare，缩写为C. A. R. Hoare） 提出
 * 昵称为东尼·霍尔（Tony Hoare）
 * @Date：2019/10/7 23:00
 * @author：wbx
 *
 * 快速排序 – 执行流程
 *
 * ① 从序列中选择一个轴点元素（pivot）
 * ✓ 假设每次选择 0 位置的元素为轴点元素
 * ② 利用 pivot 将序列分割成 2 个子序列
 * ✓ 将小于 pivot 的元素放在pivot前面（左侧）
 * ✓ 将大于 pivot 的元素放在pivot后面（右侧）
 * ✓ 等于pivot的元素放哪边都可以
 * ③ 对子序列进行 ① ② 操作
 * ✓ 直到不能再分割（子序列中只剩下1个元素）
 *
 *
 * ◼ 快速排序的本质
 * 逐渐将每一个元素都转换成轴点元素
 */
public class _06_QuickSort<T extends Comparable> extends Sort<T> {

    @Override
    protected void sort() {

    }

}
