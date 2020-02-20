package com.wbx.algorithm._01_sort.comparison;

import com.wbx.algorithm._01_sort.Sort;

/**
 * @describe： 快速排序
 * 1960年由查尔斯·安东尼·理查德·霍尔（Charles Antony Richard Hoare，缩写为C. A. R. Hoare） 提出
 * 昵称为东尼·霍尔（Tony Hoare）
 * @Date：2019/10/7 23:00
 * @author：wbx 快速排序 – 执行流程
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
 * ◼ 快速排序的本质
 * 逐渐将每一个元素都转换成轴点元素
 *
 *
 *
 */
public class _06_QuickSort<T extends Comparable> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * @param begin
     * @param end   [begin,end)
     */
    private void sort(int begin, int end) {
        //至少要两个元素(递归结束条件)
        if (end - begin < 2) {
            return;
        }
        int pivotIndex = pivotIndex(begin, end);
        sort(begin, pivotIndex);
        sort(pivotIndex + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        //优化1:为了降低最坏情况的出现概率，一般采取的做法是:随机选择一个元素当轴点元素(而不是固定采用begin当轴点元素)
        //强转是ceil(向下取整)
        swap(begin, begin + (int)(Math.random() * (end - begin)));

        T pivot = array[begin];
        end--;

        //当begin==end的时候就结束
        while (begin < end) {

            //左右遍历的时候,可以采用这种写法(3个while + break)
            while (begin < end) {
                /*优化2:
                思考： cmp 位置的判断分别改为 ≥、 ≤ 会起到什么效果？
                ◼ 轴点元素分割出来的子序列极度不均匀(如果序列中的所有元素都与轴点元素相等的情况下)
                 导致出现最坏时间复杂度 O(n2)

                如果序列中的所有元素都与轴点元素相等，利用目前的算法实现，轴点元素可以将序列分割成 2 个均匀的子序列
                */
                if (cmp(array[end], pivot) > 0) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }

            while (begin < end) {
                if (cmp(array[begin], pivot) < 0) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }

        array[begin] = pivot;
        return begin;
    }

    /**
     * 总结:
     * ◼ 在轴点左右元素数量比较均匀的情况下，同时也是最好的情况
     * T(n) = 2 ∗ T(n/2) + O(n) = O(nlogn)
     *
     *
     * ◼ 如果轴点左右元素数量极度不均匀，最坏情况
     *  T(n) = T (n − 1) + O(n) = O(n2)
     * 7 1 2 3 4 5 6
     * 6 1 2 3 4 5 7
     * 5 1 2 3 4 6 7
     * 4 1 2 3 5 6 7
     * 3 1 2 4 5 6 7
     * 2 1 3 4 5 6 7
     * 1 2 3 4 5 6 7
     * ◼ 为了降低最坏情况的出现概率，一般采取的做法是
     *  随机选择轴点元素
     *
     *
     * ◼ 最好、平均时间复杂度： O(nlogn)
     * ◼ 最坏时间复杂度： O(n2)
     * ◼ 由于递归调用的缘故，空间复杂度： O(logn)
     * ◼ 属于不稳定排序
     */

}
