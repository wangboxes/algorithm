package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 归并排序
 * 1945年由约翰·冯·诺伊曼（John von Neumann） 首次提出
 * @Date：2019/10/7 23:00
 * @author：wbx
 *
 * ◼ 执行流程
 * ① 不断地将当前序列平均分割成2个子序列
 * ✓ 直到不能再分割（序列中只剩1个元素）
 * ② 不断地将2个子序列合并成一个有序序列
 * ✓ 直到最终只剩下1个有序序列
 */
@SuppressWarnings("unchecked")
public class _05_MergeSort<T extends Comparable> extends Sort<T> {

    //额外的存储空间,所以不是原地排序算法
    private T[] leftArray;

    @Override
    protected void sort() {
        //准备一段临时的数组空间,在merge操作中使用  (多用了一个额外的空间,所以不是原地排序算法)
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }


    /**
     * 对 [begin, end) 范围的数据进行归并排序
     *
     * T(n) = T(n/2) + T(n/2) + O(n)
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
     */
    private void merge(int begin, int mid, int end) {
        //左边数组(基于leftArray), li-->left index  le-->left end
        int li = 0, le = mid - begin;

        //右边数组(基于array的右半边), lri-->right index  re-->right end
        int ri = mid, re = end;

        //array数组的索引(array index)
        int ai = begin;

        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        // 如果左边还没有结束
        while (li < le) {
            //如果右边数组先结束,左边数据的元素还要依次拷贝到右边数组; 如果左边数据先结束,直接结束.
            //注意:如果cmp位置改为 <= 会失去稳定性
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                //拷贝右边数组到array
                array[ai++] = array[ri++];
            } else {
                //拷贝左边数组到array
                array[ai++] = leftArray[li++];
            }
        }
    }

    /**
     * 归并排序 – 复杂度分析:
     * ◼ 由于归并排序总是平均分割子序列，所以最好、最坏、平均时间复杂度都是 O(nlogn) ，属于稳定排序
     * ◼ 从代码中不难看出：归并排序的空间复杂度是 O (n/2 + logn) = O(n)
     *  n/2 用于临时存放左侧数组， logn 是因为递归调用
     */

}
