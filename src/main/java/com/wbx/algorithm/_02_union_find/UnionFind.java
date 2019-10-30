package com.wbx.algorithm._02_union_find;

/**
 * @describe： 并查集
 * @Date：2019-10-10 20:35
 * @author：wbx
 */
public abstract class UnionFind {

    /**
     * Quick Find 存的是根节点
     * Quick Union 存的是父节点
     */
    protected int[] parents;

    protected UnionFind (int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must >= 1");
        }

        parents = new int[capacity];

        //初始化数据，让value==index(初始化时，每个元素各自属于一个单元素集合)
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     *
     * 合并v1、v2所在的集合
     * @param v1
     * @param v2
     * @return
     */
    public abstract void union(int v1, int v2);


    /**
     * 查找v所属的集合（根节点） --- Quick Find 和 Quick Union 找的都是根节点
     * @param v
     * @return
     */
    public abstract int find(int v);


    /**
     * 检查v1、v2是否属于同一个集合
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }



    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

}
