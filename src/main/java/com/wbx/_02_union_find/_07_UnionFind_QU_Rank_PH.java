package com.wbx._02_union_find;

/**
 * @describe： Quick Union - 基于rank的优化 - 路径减半(Path Halving)
 * @Date：2019/10/12 15:02
 * @author：wbx
 */
public class _07_UnionFind_QU_Rank_PH extends _04_UnionFind_QU_Rank {

    public _07_UnionFind_QU_Rank_PH(int capacity) {
        super(capacity);
    }

    /**
     * 路径减半：使路径上每隔一个节点就指向其祖父节点（parent的parent）
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }

        return v;
    }


    /**
     * 总结:
     * 使用路径压缩、分裂或减半 + 基于rank或者size的优化
     * ✓ 可以确保每个操作的均摊时间复杂度为 O(α(n))， α(n) < 5
     *
     * ◼ 个人建议的搭配
     * ✓ Quick Union
     * ✓ 基于rank的优化
     * ✓ Path Halving 或 Path Spliting
     */
}
