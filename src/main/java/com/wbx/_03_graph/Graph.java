package com.wbx._03_graph;

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
}
