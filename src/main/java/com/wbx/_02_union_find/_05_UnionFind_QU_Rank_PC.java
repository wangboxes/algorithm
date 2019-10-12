package com.wbx._02_union_find;

/**
 * @describe： Quick Union - 基于rank的优化 - 路径压缩(Path Compression)
 * @Date：2019/10/12 15:02
 * @author：wbx
 *
 * ◼ 虽然有了基于rank的优化，树会相对平衡一点
 * ◼ 但是随着Union次数的增多，树的高度依然会越来越高
 *    导致find操作变慢，尤其是底层节点（因为find是不断向上找到根节点）
 *
 */
public class _05_UnionFind_QU_Rank_PC extends _04_UnionFind_QU_Rank {

    public _05_UnionFind_QU_Rank_PC(int capacity) {
        super(capacity);
    }

    /**
     * ◼ 什么是路径压缩？
     *   在find时使 "同一路径上" 的所有节点都指向根节点，从而降低树的高度
     *
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        //TODO learn
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];


    }


    /**
     * ◼ 路径压缩使路径上的所有节点都指向根节点，所以实现成本稍高
     * ◼ 还有2种更优的做法，不但能降低树高，实现成本也比路径压缩低:
     *    路径分裂（Path Spliting）
     *    路径减半（Path Halving）
     *
     * ◼ 路径分裂、路径减半的效率差不多，但都比路径压缩要好
     */

}
