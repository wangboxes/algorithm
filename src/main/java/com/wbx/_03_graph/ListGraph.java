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
        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();

            //删除终止顶点入度的边
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        //从被删除顶点的入度的边,找对应的起始顶点,删除起始顶点出度的边
        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
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
        Set<Vertex<V,E>> enqueueVertices = new HashSet<>();
        Queue<Vertex<V,E>> queue = new LinkedList<>();

        queue.offer(beginVertex);
        enqueueVertices.add(beginVertex);

        //每次出队后,把出队顶点的出度边的终止顶点放入队列中
        while (!queue.isEmpty()) {
            Vertex<V,E> pollVertex = queue.poll();

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
    public void dfsWithRecursion(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null || visitor == null) {
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
    public List<V> topologicalSort() {
        return null;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return null;
    }


    /**
     * 顶点
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
