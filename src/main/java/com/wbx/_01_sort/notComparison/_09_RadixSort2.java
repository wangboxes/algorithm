package com.wbx._01_sort.notComparison;

import com.wbx._01_sort.Sort;

/**
 * @describe： 基数排序 – 基于二维数组
 * @Date：2019/10/12 16:33
 * @author：wbx
 *
 * ◼ 空间复杂度是 O(kn + k) ，时间复杂度是 O(dn), 其中: (d 是最大值的位数， k 是进制)
 */
public class _09_RadixSort2 extends Sort<Integer> {

    @Override
    protected void sort() {

        // 找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        //桶数组(二维数组)
        int[][] buckets = new int[10][array.length];

        //每个桶的元素数量
        int[] bucketSizes = new int[buckets.length];

        //1.将array中的数据放入二维数组中
        for (int divider = 1; divider <= max; divider *= 10) {
            for (int i = 0; i < array.length; i++) {
                int no = array[i] / divider % 10;
                //将元素放到桶数组中,并统计桶的元素数量
                buckets[no][bucketSizes[no]++] = array[i];
            }

            //2.将二维数组中的数据按顺序放到array中
            int index = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < bucketSizes[i]; j++) {
                    array[index++] = buckets[i][j];
                }
                bucketSizes[i] = 0;
            }

        }





    }



    public static void main(String[] args) {
        //数据类型[][] 数组名 = new 数据类型[二维数组的长度/包含的一维数组的个数][每个一维数组的长度];
        //定义了一个整型的二维数组，其中包含3个一维数组，每个一维数组可以存储5个整数
        int[][] arr = new int[3][5];

        for(int i = 0; i < arr.length; i++){ //遍历二维数组，遍历出来的每一个元素是一个一维数组
            for(int j = 0; j < arr[i].length; j++){ //遍历对应位置上的一维数组
                System.out.println(arr[i][j]);
            }
            System.out.println("----------------");
        }
    }
}
