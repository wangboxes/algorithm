package com.wbx.algorithm._02_union_find;

/**
 * @describe： Quick Find
 * @Date：2019/10/12 11:16
 * @author：wbx
 */
public class _01_UnionFind_QF extends UnionFind {

    public _01_UnionFind_QF(int capacity) {
        super(capacity);
    }

    /**
     * 将v1所在集合的所有元素，都嫁接到v2的父节点上(parents里面存的是:每个元素对应的根节点)
     * 时间复杂度： O(n)
     */
    @Override
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 != root2) {
            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == root1){
                    //Quick Find 的 union(v1, v2)：让 v1 所在集合的所有元素都指向 v2 的根节点
                    parents[i] = root2;
                }
            }
        }

    }


    /**
     * 父节点就是根节点
     *
     * 时间复杂度： O(1)
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        return parents[v];
    }

}
