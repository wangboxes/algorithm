package com.wbx._03_graph;

import org.junit.Test;

/**
 * @describe：
 * @Date：2019/10/21 14:35
 * @author：wbx
 */
public class ListGraphTest {

/*    static void testTopo() {
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }

    static void testDfs() {
        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
        graph.dfs("a", (Object v) -> {
            System.out.println(v);
            return false;
        });
    }

    static void testBfs() {
        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
        graph.bfs(0, (Object v) -> {
            System.out.println(v);
            return false;
        });
    }*/

    @Test
    public void testAddAndRemove() {
        ListGraph<String, Integer> graph = new ListGraph<>();
//		graph.addEdge("V0", "V1");
//		graph.addEdge("V1", "V0");
//
//		graph.addEdge("V0", "V2");
//		graph.addEdge("V2", "V0");
//
//		graph.addEdge("V0", "V3");
//		graph.addEdge("V3", "V0");
//
//		graph.addEdge("V1", "V2");
//		graph.addEdge("V2", "V1");
//
//		graph.addEdge("V2", "V3");
//		graph.addEdge("V3", "V2");
//
//		graph.print();


        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);

//		graph.removeEdge("V0", "V4");
		graph.removeVertex("V0");

		graph.print();


    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
