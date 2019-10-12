package com.wbx._02_union_find;

/**
 * @describe： Quick Union
 * @Date：2019/10/12 11:16
 * @author：wbx
 */
public class _02_UnionFind_QU extends UnionFind{

    public _02_UnionFind_QU(int capacity) {
        super(capacity);
    }

    /**
     * 将v1的根节点嫁接到v2的根节点上(parents里面存的是:每个元素的父点)
     *
     * 时间复杂度： O(logn). 查找导致的
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        //Quick Union 的 union(v1, v2)：让 v1 的根节点指向 v2 的根节点(只改变根节点的指向)
        if (p1 != p2) {
            parents[p1] = p2;
        }

    }


    /*
     * 通过parent链条不断地向上找，直到找到根节点
     *
     * 时间复杂度： O(logn).
     */
    public int find(int v) {
        rangeCheck(v);

        while (v != parents[v]) {
            v = parents[v];
        }

        return v;
    }

}
