package com.wbx._03_graph;

import com.wbx._02_union_find._08_GenericUnionFind;

import java.util.*;
import java.util.Map.Entry;

/**
 * @describe： 用邻接表(Adjacency List)实现的有向带权图
 * @Date：2019/10/21 10:56
 * @author：wbx
 */
@SuppressWarnings("unchecked")
public class ListGraph<V, E> extends Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();

    private Set<Edge<V, E>> edges = new HashSet<>();

    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    public ListGraph() {}

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

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
     * 如果此时 L 中的元素个数少于顶点总数，说明原图中存在环，无法进行拓扑排序(可以用来检测图中是否存在环)
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
                //如果存在环的话,那么换中的顶点的入度不会减为0,那么就不会入队列
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


    /**
     * 最小生成树-Prim实现
     *
     * 切分定理
     * ◼ 切分（Cut）：把图中的节点分为两部分，称为一个切分
     * ◼ 下图有个切分 C = (S, T)， 顶点集S = {A, B, D}， 顶点集T = {C, E}
     * ◼ 横切边（Crossing Edge）：如果一个边的两个顶点，分别属于切分的两部分S和T，这个边称为横切边
     * ◼ 切分定理：给定任意切分，横切边中权值最小的边必然属于最小生成树
     *
     * Prim算法 – 执行过程:
     * ◼ 假设 G = (V， E) 是有权的连通图（无向）， A 是 G 中最小生成树的边集
     *   算法从顶点S = { u0 }（u0 ∈ V）， 边A = { } 开始，重复执行下述操作，直到 S = V 为止
     * ✓ 找到切分 C = (S， V – S) 的最小横切边 (u0， v0) 并入集合 A，同时将 v0 并入集合 S
     *
     * 复杂度:O(ElogE)
     */
    @Override
    public Set<EdgeInfo<V, E>> mstWithPrim() {
        //随机取出一个顶点
        Iterator<Vertex<V, E>> it = vertices.values().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Vertex<V, E> vertex = it.next();

        //边集(存放组成最小生成树的边集信息)
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //顶点集(存放已经确定的顶点)
        Set<Vertex<V, E>> addedVertices = new HashSet<>();

        addedVertices.add(vertex);

        //使用最小堆方便获取最小边
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);

        int verticesSize = vertices.size();
        while (!heap.isEmpty() && addedVertices.size() < verticesSize) {
            Edge<V, E> minEdge = heap.remove();
            //去除重复的边
            if (addedVertices.contains(minEdge.to)) {
                continue;
            }
            addedVertices.add(minEdge.to);
            edgeInfos.add(minEdge.info());
            //这里会有重复的边添加进去
            heap.addAll(minEdge.to.outEdges);
        }

        return edgeInfos;
    }


    /**
     * 最小生成树-Kruskal实现
     *
     * Kruskal算法 – 执行过程
     * ◼ 按照边的权重顺序（从小到大）将边加入生成树中，直到生成树中含有 V – 1 条边为止（V 是顶点数量）
     * 若加入该边会与生成树形成环，则不加入该边
     * 从第3条边开始，可能会与生成树形成环
     *
     * 复杂度:O(ElogE)
     */
    @Override
    public Set<EdgeInfo<V, E>> mstWithKruskal() {
        //如果没有边的话就不用执行
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) {
            return null;
        }

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();

        // 所有边都加入最小堆中--O(E)
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);

        //借助并查集来判断是否有环
        _08_GenericUnionFind<Vertex<V, E>> uf = new _08_GenericUnionFind<>();
        // O(V)
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });

        // O(ElogE)
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) { // E
            Edge<V, E> minEdge = heap.remove(); // O(logE)

            //如果两个顶点属于同一个并查集,那么不取这条边,因为会造成环
            if (uf.isSame(minEdge.from, minEdge.to)) {
                continue;
            }

            edgeInfos.add(minEdge.info());
            uf.union(minEdge.from, minEdge.to);
        }

        return edgeInfos;
    }


   /* public Map<V, E> shortestPath(V begin) {
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) {
		    return null;
        }

		Map<V, E> selectedPaths = new HashMap<>();
		Map<Vertex<V, E>, E> paths = new HashMap<>();
		// 初始化paths
		for (Edge<V, E> edge : beginVertex.outEdges) {
			paths.put(edge.to, edge.weight);
		}

		while (!paths.isEmpty()) {
			Entry<Vertex<V, E>, E> minEntry = getMinPath(paths);
			// minVertex离开桌面
			Vertex<V, E> minVertex = minEntry.getKey();
			selectedPaths.put(minVertex.value, minEntry.getValue());
			paths.remove(minVertex);
			// 对它的minVertex的outEdges进行松弛操作
			for (Edge<V, E> edge : minVertex.outEdges) {
				// 如果edge.to已经离开桌面，就没必要进行松弛操作
				if (selectedPaths.containsKey(edge.to.value)){
				    continue;
                }
				// 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
				E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
				// 以前的最短路径：beginVertex到edge.to的最短路径
				E oldWeight = paths.get(edge.to);
				if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
					paths.put(edge.to, newWeight);
				}
			}
		}

		selectedPaths.remove(begin);
		return selectedPaths;
	}*/


    @Override
    public Map<V, PathInfo<V, E>> shortestPathWithDijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        //存放选择好的路径
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();

        // 初始化paths
        for (Edge<V, E> edge : beginVertex.outEdges) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = edge.weight;
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);
        }

        while (!paths.isEmpty()) {
            Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);

            // minVertex离开桌面
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);

            paths.remove(minVertex);

            // 对它的minVertex的outEdges进行松弛操作
            for (Edge<V, E> edge : minVertex.outEdges) {
                // 如果edge.to已经离开桌面，就没必要进行松弛操作
                if (selectedPaths.containsKey(edge.to.value)) {
                    continue;
                }
                relaxForDijkstra(edge, minPath, paths);
            }
        }

        selectedPaths.remove(begin);
        return selectedPaths;
    }


    /*
     * ◼ 松弛操作（Relaxation）：更新2个顶点之间的最短路径
     * 这里一般是指：更新源点到另一个点的最短路径
     * 松弛操作的意义：尝试找出更短的最短路径
     *
     * ◼ 如: 确定A到D的最短路径后，对DC、 DE边进行松弛操作，更新了A到C、 A到E的最短路径
     */

    /**
     * 松弛
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
     */
    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        // 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);

        // 以前的最短路径：beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to);

        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) {
            return;
        }

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }

        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
    }

    /**
     * 从paths中挑一个最小的路径出来(循环遍历--这里可以用堆来进行优化(未做))
     * @param paths
     * @return
     */
    private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();

        Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();

        while (it.hasNext()) {
            Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }


    @Override
    public Map<V, PathInfo<V, E>> shortestPathWithBellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        PathInfo<V, E> beginPath = new PathInfo<>();
        beginPath.weight = weightManager.zero();
        selectedPaths.put(begin, beginPath);

        //算法原理：对所有的边进行 V – 1 次松弛操作（V 是节点数量），得到所有可能的最短路径
        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) {
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath == null) {
                    continue;
                }
                relaxForBellmanFord(edge, fromPath, selectedPaths);
            }
        }

        //再进行一次松弛操作,如果还能松弛成功,说明存在负权环
        for (Edge<V, E> edge : edges) {
            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
            if (fromPath == null) {
                continue;
            }
            if (relaxForBellmanFord(edge, fromPath, selectedPaths)) {
                System.out.println("有负权环");
                return null;
            }
        }

        selectedPaths.remove(begin);
        return selectedPaths;
    }


    /**
     * 松弛
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
     */
    private boolean relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {
        // 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);

        // 以前的最短路径：beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to.value);

        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) {
            return false;
        }

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }

        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());

        return true;
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

        public EdgeInfo<V, E> info() {
            return new EdgeInfo<V, E>(from.value, to.value, weight);
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
