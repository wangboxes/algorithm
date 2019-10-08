package com.wbx._01_sort.comparison;

import com.wbx._01_sort.Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @describe： 希尔排序
 * 1959年由唐纳德·希尔（Donald Shell） 提出
 * @Date：2019/10/7 23:00
 * @author：wbx 
 * 
 * ◼ 希尔排序把序列看作是一个矩阵，分成 m列，逐列进行排序
 *  m从某个整数逐渐减为1
 *  当 m为1时，整个序列将完全有序
 * ◼ 因此，希尔排序也被称为递减增量排序（Diminishing Increment Sort）
 * 
 * 
 * ◼ 矩阵的列数取决于步长序列（step sequence）
 * ✓ 比如，如果步长序列为{1,5,19,41,109,...}，就代表依次分成109列、 41列、 19列、 5列、 1列进行排序
 * ✓ 不同的步长序列，执行效率也不同
 * 
 * 
 * ◼ 希尔本人给出的步长序列是 n/2^k, 比如 n为16时，步长序列是{1, 2, 4, 8}
 * ◼ 不难看出来，从8列 变为 1列的过程中，逆序对的数量在逐渐减少
 *  因此希尔排序底层一般使用插入排序对每一列进行排序，也很多资料认为希尔排序是插入排序的改进版
 * 
 * 
 * ◼ 假设有11个元素，步长序列是{1, 2, 5}
 * 11 10 9 8 7 6 5 4 3 2 1
 *
 * 
 * 11 10 9 8 7
 * 6  5  4 3 2
 * 1
 *
 * 
 * 1  5 4 3 2
 * 6 10 9 8 7
 * 11
 * ◼ 假设元素在第 col 列、第 row 行，步长（总列数）是 step
 *  那么这个元素在数组中的索引是 col + row * step
 *  比如 9 在排序前是第 2 列、第 0 行，那么它排序前的索引是 2 + 0 * 5 = 2
 *  比如 4 在排序前是第 2 列、第 1 行，那么它排序前的索引是 2 + 1 * 5 = 7
 * 
 * 
 * 
 * ◼ 最好情况是步长序列只有1，且序列几乎有序，时间复杂度为 O(n)
 * ◼ 空间复杂度为O(1)
 * ◼ 属于不稳定排序
 */
public class _07_ShellSort<T extends Comparable> extends Sort<T> {

    @Override
    protected void sort() {
        //List<Integer> stepSequence = shellStepSequence(array.length);
        List<Integer> stepSequence = sedgewickStepSequence(array.length);
        for (Integer step : stepSequence) {
            sort(step);
        }
    }


    private void sort(Integer step) {
        for (int col = 0; col < step; col++) {

            //插入排序
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    //通过交换元素的方式完成排序
                    swap(cur, cur - step);
                    cur -= step;
                }
            }

        }

    }

    /**
     * 希尔本人给出的步长序列，最坏情况时间复杂度是 O(n^2)
     *
     * @param count
     * @return
     */
    private List<Integer> shellStepSequence(int count) {
        //希尔本人给出的步长序列是 n/2^k, 比如 n为16时，步长序列是{1, 2, 4, 8}
        List<Integer> stepSequence = new ArrayList<>();
        int step = count;
        //等价于:(step /= 2) > 0
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    /**
     * 目前已知的最好的步长序列，最坏情况时间复杂度是 O(n^ 4/3) ， 1986年由Robert Sedgewick提出
     * 1,5,19,41,109...
     * @param count
     * @return
     */
    private List<Integer> sedgewickStepSequence(int count) {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0;
        int step = 0;

        while (true) {
            //k为偶数和奇数时,分别套用不同的公式
            if (k % 2 == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= count) {
                break;
            }

            stepSequence.add(0, step);
            k++;
        }


        return stepSequence;
    }


    public static void main(String[] args) {
        _07_ShellSort<Comparable> shellSort = new _07_ShellSort<>();
        System.out.println("shellStepSequence = " + shellSort.shellStepSequence(16));
        System.out.println("sedgewickStepSequence = " + shellSort.sedgewickStepSequence(1000));
    }
}
