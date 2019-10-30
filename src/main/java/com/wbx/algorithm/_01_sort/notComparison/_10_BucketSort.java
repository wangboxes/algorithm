package com.wbx.algorithm._01_sort.notComparison;

import com.wbx.algorithm._01_sort.Sort;

import java.util.LinkedList;
import java.util.List;

/**
 * @describe： 桶排序（Bucket Sort）
 * @Date：2019/10/12 17:17
 * @author：wbx
 *
 * ◼ 执行流程
 * ① 创建一定数量的桶（比如用数组、链表作为桶）
 * ② 按照一定的规则（不同类型的数据，规则不同），将序列中的元素均匀分配到对应的桶. 这里演示的是排序小数
 * ③ 分别对每个桶进行单独排序
 * ④ 将所有非空桶的元素合并成有序序列
 *
 * ◼ 元素在桶中的索引 : 元素值 * 元素数量
 *
 * 空间复杂度： O(n + m)， m 是桶的数量
 * 时间复杂度：O(n + k), k 为 n ∗ logn − n ∗ logm
 * 属于稳定排序
 */
public class _10_BucketSort extends Sort<Double> {

    //Double[] arr = new Double[]{0.34,0.47,0.29,0.84,0.45,0.38,0.35,0.76};

    @Override
    protected void sort() {
        //桶数组
        List<Double>[] buckets = new List[array.length];
        for (int i = 0; i < array.length; i++) {
            //例如:如果有10个元素,其中一个元素为0.34,计算后结果为3,放到索引为3的桶中
            int bucketIndex = (int)(array[i] * array.length);
            List<Double> bucket = buckets[bucketIndex];

            if (bucket == null) {
                bucket = new LinkedList<>();
                buckets[bucketIndex] = bucket;
            }
            bucket.add(array[i]);
        }

        //对每个桶进行排序
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) {
                continue;
            }
            buckets[i].sort(null);

            for (Double d : buckets[i]) {
                array[index++] = d;
            }
        }



    }
}
