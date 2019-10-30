package com.wbx.data_structure._10_堆;


import com.wbx.data_structure._06_二叉树.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author WANGBOXIONG297
 * @decription:
 * @date 2019-5-28
 */
public class TestHeap {

    public static void main(String[] args) {
        testTopK();
    }

    /**
     * 求top k
     */
    public static void testTopK() {
        int k = 3;
        // 创建小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Integer[] aa = {1,3,4,5,6,74,32,11,2,4,66,5,22};
        for (int i = 0; i < aa.length; i++) {
            if (heap.size()<k ) {
                heap.add(aa[i]);
            }else if (heap.get() < aa[i]) {
                heap.replace(aa[i]);
            }

        }

        BinaryTrees.println(heap);



    }


    public static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(12);
        heap.add(34);
        heap.add(11);
        heap.add(88);
        heap.add(33);
        heap.add(99);//测试添加
        BinaryTrees.println(heap);
        System.out.println(heap.get());
        heap.remove();//测试删除
        BinaryTrees.println(heap);

        System.out.println(heap.replace(10));//测试替换
        BinaryTrees.println(heap);

        Integer[] aa = {1,3,4,5,6,74,32,11,2,4,66,5,22};
        BinaryHeap<Integer> integerBinaryHeap = new BinaryHeap<>(aa);//测试批量建堆
        BinaryTrees.println(integerBinaryHeap);
    }
}
