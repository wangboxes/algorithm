package com.wbx._02_union_find;

/**
 * @describe： Quick Union - 基于rank的优化
 * @Date：2019/10/12 14:45
 * @author：wbx
 *
 * 基于size的优化，也可能会存在树不平衡的问题,基于rank的优化树高会比较平衡一点
 */
public class _04_UnionFind_QU_Rank extends _02_UnionFind_QU {

    /**
     * 根节点存储该树的树高
     */
    private int[] ranks;

    public _04_UnionFind_QU_Rank(int capacity) {
        super(capacity);

        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }


    /**
     * 矮的树 嫁接到 高的树
     */
    @Override
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 != root2) {
            if (ranks[root1] < ranks[root2]) {
                parents[root1] = root2;

            } else if (ranks[root1] > ranks[root2]){
                parents[root2] = root1;

            } else {

                parents[root1] = root2;
                //注意:树高一样的时候,需要改变ranks数组里的值
                ranks[root2] += 1;
            }
        }
    }

}
