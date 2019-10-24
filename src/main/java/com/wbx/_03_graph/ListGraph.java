package com.wbx._03_graph;

import java.util.*;

/**
 * @describe： 用邻接表(Adjacency List)实现的有向带权图
 * @Date：2019/10/21 10:56
 * @author：wbx
 */
@SuppressWarnings("unchecked")
public class ListGraph<V, E> implements Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();

    private Set<Edge<V, E>> edges = new HashSet<>();


    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public void addVertex(V v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, new Vertex<>(v));
        }
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //起始顶点
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        //终止顶点
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        //先移除,再添加
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);

    }


    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            return;
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            return;
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }


    @Override
    public void removeVertex(V v) {
        //删除顶点
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) {
            return;
        }

        //从被删除顶点的出度的边,找对应的终止顶点,删除终止顶点入度的边
        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();

            //删除终止顶点入度的边
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        //从被删除顶点的入度的边,找对应的起始顶点,删除起始顶点出度的边
        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            //删除起始顶点出度的边
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null || visitor == null) {
            return;
        }
        //存储已经入队列的顶点,防止重复遍历
        Set<Vertex<V, E>> enqueueVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        queue.offer(beginVertex);
        enqueueVertices.add(beginVertex);

        //每次出队后,把出队顶点的出度边的终止顶点放入队列中
        while (!queue.isEmpty()) {
            Vertex<V, E> pollVertex = queue.poll();

            if (visitor.visit(pollVertex.value)) {
                return;
            }

            for (Edge<V, E> outEdge : pollVertex.outEdges) {
                if (!enqueueVertices.contains(outEdge.to)) {
                    queue.offer(outEdge.to);
                    enqueueVertices.add(outEdge.to);
                }
            }
        }


    }


    @Override
    public void dfsWithRecursion(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }

        dfsWithRecursion(beginVertex, new HashSet<>());
    }


    private void dfsWithRecursion(Vertex<V, E> vertex, HashSet<Vertex<V, E>> visitedVertices) {
        System.out.println(vertex.value);
        visitedVertices.add(vertex);

        for (Edge<V, E> outEdge : vertex.outEdges) {
            if (!visitedVertices.contains(outEdge.to)) {
                dfsWithRecursion(outEdge.to, visitedVertices);
            }
        }
    }

    @Override
    public void dfsWithStack(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null || visitor == null) {
            return;
        }
        //存储已经访问过的顶点
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        // 先访问起点
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        if (visitor.visit(beginVertex.value)) {
            return;
        }

        while (!stack.isEmpty()) {
            Vertex<V, E> popVertex = stack.pop();

            for (Edge<V, E> outEdge : popVertex.outEdges) {
                if (visitedVertices.contains(outEdge.to)) {
                    continue;
                }
                stack.push(outEdge.from);
                stack.push(outEdge.to);
                visitedVertices.add(outEdge.to);
                if (visitor.visit(outEdge.to.value)) {
                    return;
                }

                break;
            }

        }

    }


    /**
     * 思路:
     * ◼ 可以使用卡恩算法（Kahn于1962年提出）完成拓扑排序
     *
     * 假设 L 是存放拓扑排序结果的列表
     * ① 把所有入度为 0 的顶点放入 L 中，然后把这些顶点从图中去掉(本实现中用一个hashMap来存储每个顶点的入度)
     * ② 重复操作 ①，直到找不到入度为 0 的顶点
     *
     * 如果此时 L 中的元素个数和顶点总数相同，说明拓扑排序完成
     * 如果此时 L 中的元素个数少于顶点总数，说明原图中存在环，无法进行拓扑排序
     *
     * @return
     */
    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        //用来存储每个节点的入度
        Map<Vertex<V, E>, Integer> ins = new HashMap<>();

        // 初始化（将度为0的节点都放入队列）
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int inSize = vertex.inEdges.size();
            if (inSize == 0) {
                queue.offer(vertex);
            } else {
                ins.put(vertex, inSize);
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> pollVertex = queue.poll();
            // 放入返回结果中
            list.add(pollVertex.value);
            for (Edge<V, E> outEdge : pollVertex.outEdges) {
                int toIn = ins.get(outEdge.to) - 1;
                if (toIn == 0) {
                    queue.offer(outEdge.to);
                } else {
                    //更新减一后的入度
                    ins.put(outEdge.to, toIn);
                }
            }
        }

        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return null;
    }


    /**
     * 顶点
     *
     * @param <V> 顶点
     * @param <E> 权值
     */
    private static class Vertex<V, E> {
        /**
         * 顶点值
         */
        V value;

        /**
         * 入度
         */
        Set<Edge<V, E>> inEdges = new HashSet<>();

        /**
         * 出度
         */
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            return Objects.equals(value, ((Vertex<V, E>) obj).value);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }


    private static class Edge<V, E> {
        /**
         * 边的起始顶点
         */
        Vertex<V, E> from;

        /**
         * 边的结束顶点
         */
        Vertex<V, E> to;

        /**
         * 权值
         */
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V, E> otherEdge = (Edge<V, E>) obj;
            return Objects.equals(from, otherEdge.from) && Objects.equals(to, otherEdge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge{from=" + from + ", to=" + to + ", weight=" + weight + '}';
        }
    }

    public void print() {
        System.out.println("[顶点]-------------------");
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
            System.out.println("out-----------");
            System.out.println(vertex.outEdges);
            System.out.println("in-----------");
            System.out.println(vertex.inEdges);
        });

        System.out.println("[边]-------------------");
        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });
    }

}
