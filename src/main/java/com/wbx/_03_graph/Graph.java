package com.wbx._03_graph;

import java.util.List;
import java.util.Set;

/**
 * @describe： V表示顶点,E表示带全图的权值
 * @Date：2019/10/21 10:53
 * @author：wbx
 *
 * ◼ 图由顶点（vertex）和边（edge）组成，通常表示为 G = (V, E)
 * G表示一个图， V是顶点集， E是边集
 * 顶点集V有穷且非空
 * 任意两个顶点之间都可以用边来表示它们之间的关系，边集E可以是空的
 */
public interface Graph<V, E> {

    /**
     * 顶点数
     * @return
     */
    int verticesSize();

    /**
     * 边数
     * @return
     */
    int edgesSize();

    /**
     * 添加顶点
     * @param v
     */
    void addVertex(V v);

    /**
     * 删除顶点
     * @param v
     */
    void removeVertex(V v);

    /**
     * 添加边
     * @param from
     * @param to
     */
    void addEdge(V from, V to);

    /**
     * 添加带权值的边
     * @param from
     * @param to
     * @param weight
     */
    void addEdge(V from, V to, E weight);

    /**
     * 删除边
     * @param from
     * @param to
     */
    void removeEdge(V from, V to);


    //图的遍历 : 从图中某一顶点出发访问图中其余顶点，且每一个顶点仅被访问一次

    /**
     * 广度优先搜索（Breadth First Search， BFS）（有向图、无向图都适用）
     *
     * 二叉树层序遍历就是一种广度优先搜索
     * @param begin 搜索的起始顶点
     * @param visitor 对遍历到的每个顶点的访问方式
     */
    void bfs(V begin, VertexVisitor<V> visitor);

    /**
     * 递归方式
     * 深度优先搜索（Depth First Search， DFS）,又称为宽度优先搜索、横向优先搜索（有向图、无向图都适用）
     *
     * 二叉树前序遍历就是一种深度优先搜索
     * @param begin 搜索的起始顶点
     * @param visitor 对遍历到的每个顶点的访问方式
     */
    void dfsWithRecursion(V begin, VertexVisitor<V> visitor);


    /*
     *          AOV网（Activity On Vertex Network）
     *
     * ◼ 一项大的工程常被分为多个小的子工程
     * ✓ 子工程之间可能存在一定的先后顺序，即某些子工程必须在其他的一些子工程完成后才能开始
     *
     * ◼ 在现代化管理中，人们常用"有向图"来描述和分析一项工程的计划和实施过程，子工程被称为活动（Activity）
     * ✓ 以顶点表示活动、有向边表示活动之间的先后关系，这样的图简称为 AOV 网
     *
     * ◼ 标准的AOV网必须是一个"有向无环图"（Directed Acyclic Graph，简称 DAG）
     */


    /**
     * 拓扑排序 : 将 AOV 网中所有活动排成一个序列，使得每个活动的前驱活动都排在该活动的前面
     *
     * 前驱活动：有向边 起点的活动称 为 终点的前驱活动, 只有当一个活动的前驱全部都完成后，这个活动才能进行
     * 后继活动：有向边终点的活动称为起点的后继活动
     * @return
     */
    List<V> topologicalSort();


    /**
     * 最小生成树（Minimum Spanning Tree，简称MST）
     *
     * 也称为最小权重生成树（Minimum Weight Spanning Tree）、最小支撑树
     * 是所有生成树中，总权值最小的那棵
     * 适用于有权的连通图（无向）
     * @return
     */
    Set<EdgeInfo<V, E>> mst();


    /**
     * 用于表达遍历的时候对元素的访问方式
     * @param <V>
     */
    interface VertexVisitor<V> {

        /**
         * 自定义的访问节点的方式
         * @param v
         * @return
         */
        boolean visit(V v);
    }


    class EdgeInfo<V, E> {
        V from;
        V to;
        E weight;
        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


}
