package com.wbx._03_graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
public abstract class Graph<V, E> {

    protected WeightManager<E> weightManager;

    public Graph() {}

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    /**
     * 顶点数
     * @return
     */
    public abstract int verticesSize();

    /**
     * 边数
     * @return
     */
    public abstract int edgesSize();

    /**
     * 添加顶点
     * @param v
     */
    public abstract void addVertex(V v);

    /**
     * 删除顶点
     * @param v
     */
    public abstract void removeVertex(V v);

    /**
     * 添加边
     * @param from
     * @param to
     */
    public abstract void addEdge(V from, V to);

    /**
     * 添加带权值的边
     * @param from
     * @param to
     * @param weight
     */
    public abstract void addEdge(V from, V to, E weight);

    /**
     * 删除边
     * @param from
     * @param to
     */
    public abstract void removeEdge(V from, V to);





    //图的遍历 : 从图中某一顶点出发访问图中其余顶点，且每一个顶点仅被访问一次

    /**
     * 广度优先搜索（Breadth First Search， BFS）（有向图、无向图都适用）
     *
     * 二叉树层序遍历就是一种广度优先搜索
     * @param begin 搜索的起始顶点
     * @param visitor 对遍历到的每个顶点的访问方式
     */
    public abstract void bfs(V begin, VertexVisitor<V> visitor);



    /**
     * 递归方式
     * 深度优先搜索（Depth First Search， DFS）,又称为宽度优先搜索、横向优先搜索（有向图、无向图都适用）
     *
     * 二叉树前序遍历就是一种深度优先搜索
     * @param begin 搜索的起始顶点
     */
    public abstract void dfsWithRecursion(V begin);


    /**
     * 用栈实现的dfs
     * @param begin
     * @param visitor
     */
    public abstract void dfsWithStack(V begin, VertexVisitor<V> visitor);





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
     * 拓扑排序 (前提"有向无环图"): 将 AOV 网中所有活动排成一个序列，使得每个活动的前驱活动都排在该活动的前面（结果并不一定是唯一的）
     *
     * 前驱活动：有向边 起点的活动称 为 终点的前驱活动, 只有当一个活动的前驱全部都完成后，这个活动才能进行
     * 后继活动：有向边终点的活动称为起点的后继活动
     *
     * @return
     */
    public abstract List<V> topologicalSort();

    /*
     * 生成树（Spanning Tree），也称为支撑树
     * 连通图的极小连通子图，它含有图中全部的 n 个顶点，恰好只有 n – 1 条边
     */


    /**
     * 最小生成树（Minimum Spanning Tree，简称MST）
     *
     * 也称为最小权重生成树（Minimum Weight Spanning Tree）、最小支撑树
     * 是所有生成树中，总权值最小的那棵
     * 适用于有权的连通图（无向）
     *
     * 如果图的每一条边的权值都互不相同，那么最小生成树将只有一个，否则可能会有多个最小生成树
     *
     * ◼ 求最小生成树的2个经典算法
     * Prim（普里姆算法）
     * Kruskal（克鲁斯克尔算法）
     * @return
     */
    public abstract Set<EdgeInfo<V, E>> mstWithPrim();

    public abstract Set<EdgeInfo<V, E>> mstWithKruskal();


    /*
     * 最短路径
     *
     * 最短路径的典型应用之一：路径规划问题
     * ◼ 求解最短路径的3个经典算法 :
     *
     * 单源最短路径算法
     * ✓ Dijkstra（迪杰斯特拉算法）
     * ✓ Bellman-Ford（贝尔曼-福特算法）
     *
     * 多源最短路径算法
     * ✓ Floyd（弗洛伊德算法）
     */


    /**
     * 用Dijkstra求解最短路径
     *
     *◼ Dijkstra 属于单源最短路径算法，用于计算一个顶点到其他所有顶点的最短路径
     * 使用前提：不能有负权边
     * 时间复杂度：可优化至 O ElogV ， E 是边数量， V 是节点数量
     *
     * ◼ 由荷兰的科学家 Edsger Wybe Dijkstra 发明，曾在1972年获得图灵奖
     *
     * 后离开桌面的小石头,都是被先离开桌面的小石头拉起来的
     * @param begin
     * @return
     */
    public abstract Map<V, PathInfo<V, E>> shortestPathWithDijkstra(V begin);

    /**
     * 用Bellman-Ford求解最短路径
     *
     * ◼ Bellman-Ford 也属于单源最短路径算法，支持负权边，还能检测出是否有负权环
     * 算法原理：对所有的边进行 V – 1 次松弛操作（V 是节点数量），得到所有可能的最短路径
     * 时间复杂度： O(EV) ， E 是边数量， V 是节点数量
     * @param begin
     * @return
     */
    public abstract Map<V, PathInfo<V, E>> shortestPathWithBellmanFord(V begin);


    public static class PathInfo<V, E> {

        protected E weight;

        protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();

        public E getWeight() {
            return weight;
        }
        public void setWeight(E weight) {
            this.weight = weight;
        }
        public List<EdgeInfo<V, E>> getEdgeInfos() {
            return edgeInfos;
        }
        public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
            this.edgeInfos = edgeInfos;
        }
        @Override
        public String toString() {
            return "PathInfo [weight=" + weight + ", edgeInfos=" + edgeInfos + "]";
        }
    }




    /**
     * 用于表达遍历的时候对元素的访问方式
     * @param <V>
     */
    public interface VertexVisitor<V> {

        /**
         * 自定义的访问节点的方式
         * @param v
         * @return 返回值为true时,代表结束访问
         */
        boolean visit(V v);
    }

    public interface WeightManager<E> {
        /**
         * 用来比较权值大小
         * @param w1
         * @param w2
         * @return
         */
        int compare(E w1, E w2);


        /**
         * 用来相加权值
         * @param w1
         * @param w2
         * @return
         */
        E add(E w1, E w2);

        /**
         * 用来确定0值是啥
         * @return
         */
        E zero();
    }

    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;
        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public V getFrom() {
            return from;
        }
        public void setFrom(V from) {
            this.from = from;
        }
        public V getTo() {
            return to;
        }
        public void setTo(V to) {
            this.to = to;
        }
        public E getWeight() {
            return weight;
        }
        public void setWeight(E weight) {
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "EdgeInfo [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }
    }


}
