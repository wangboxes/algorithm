package com.wbx.algorithm._02_union_find;

/**
 * @describe： Quick Union - 基于rank的优化 - 路径分裂(Path Spliting)
 * @Date：2019/10/12 15:02
 * @author：wbx
 *
 *
 *
 */
public class _06_UnionFind_QU_Rank_PS extends _04_UnionFind_QU_Rank {

    public _06_UnionFind_QU_Rank_PS(int capacity) {
        super(capacity);
    }


    /**
     * 路径分裂：使路径上的每个节点都指向其祖父节点（parent的parent）
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }

        return v;
    }

}
