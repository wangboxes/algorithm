package com.wbx._02_union_find;

/**
 * @describe：
 * @Date：2019-10-10 20:35
 * @author：wbx
 */
public class UnionFind {

    private int[] parents;

    public UnionFind (int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity 不能小于0");
        }

        parents = new int[capacity];
        //初始化数据，让value==index
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }


    /**
     * 查找v所属的集合
     * @param v
     * @return
     */
    public int find(int v) {
        rangeCheck(v);

        return parents[v];
    }

    private void rangeCheck(int v) {
        if (v <0 || v>parents.length-1) {
            throw new IllegalArgumentException("v 越界");
        }
    }

}
