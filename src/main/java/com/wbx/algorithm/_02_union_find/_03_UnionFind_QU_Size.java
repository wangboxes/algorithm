package com.wbx.algorithm._02_union_find;

/**
 * @describe： Quick Union - 基于size的优化
 * @Date：2019/10/12 14:27
 * @author：wbx
 *
 * 在Union的过程中，可能会出现树不平衡的情况，甚至退化成链表
 *
 * ◼ 有2种常见的优化方案
 * 基于size的优化：元素少的树 嫁接到 元素多的树
 * 基于rank的优化：矮的树 嫁接到 高的树
 */
public class _03_UnionFind_QU_Size extends _02_UnionFind_QU {


    /**
     * 根节点存储该树的元素数量
     */
    private int[] sizes;

    public _03_UnionFind_QU_Size(int capacity) {
        super(capacity);

        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }

    /**
     * 元素少的树 嫁接到 元素多的树
     */
    @Override
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 != root2) {

            if (sizes[root1] < sizes[root2]) {
                parents[root1] = root2;
                sizes[root2] += sizes[root1];

            } else {

                parents[root2] = root1;
                sizes[root1] += sizes[root2];
            }

        }


    }


}
