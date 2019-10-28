package com.wbx._03_graph;

import com.wbx._03_graph.Graph.PathInfo;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @Date：2019/10/21 14:35
 * @author：wbx
 */
public class ListGraphTest {

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

    //------------广度优先搜索--------------
    @Test
    public void testBFS_01() {
        //无向图
        Graph<Object, Double> graph = Data.undirectedGraph(Data.BFS_01);
        graph.bfs("A", (Object v) -> {
            System.out.println(v);
            return false;
        });
    }

    @Test
    public void testBFS_02() {
        //有向图
        Graph<Object, Double> graph = Data.directedGraph(Data.BFS_02);
        graph.bfs(0, (Object v) -> {
            System.out.println(v);
            return false;
        });
    }
    @Test
    public void testBFS_03() {
        //无向图
        Graph<Object, Double> graph = Data.undirectedGraph(Data.BFS_03);
        graph.bfs(0, (Object v) -> {
            System.out.println(v);
            return false;
        });
    }

    @Test
    public void testBFS_04() {
        //有向图
        Graph<Object, Double> graph = Data.directedGraph(Data.BFS_04);
        graph.bfs(5, (Object v) -> {
            System.out.println(v);
            return false;
        });
    }

    //------------深度优先搜索--------------
    @Test
    public void testDFS_01() {
        Graph<Object, Double> graph = Data.undirectedGraph(Data.DFS_01);
        Integer begin = 1;
        graph.dfsWithStack(begin, (Object v) -> {
            System.out.println(v);
            return false;
        });

        System.out.println("------------");
        graph.dfsWithRecursion(begin);

    }

    @Test
    public void testDFS_02() {
        Graph<Object, Double> graph = Data.directedGraph(Data.DFS_02);
        String begin = "a";
        graph.dfsWithStack(begin, (Object v) -> {
            System.out.println(v);
            return false;
        });

        System.out.println("------------");
        graph.dfsWithRecursion(begin);

    }


    @Test
    public void testTopologicalSort1() {
        Graph<Object, Double> graph = Data.directedGraph(Data.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }

    @Test
    public void testTopologicalSort2() {
        //测试有环的情况
        Graph<Object, Double> graph = Data.directedGraph(Data.TOPO_LOOP);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }


    @Test
    public void testMst1() {
        Graph<Object, Double> graph = Data.undirectedGraph(Data.MST_01);
        graph.mstWithPrim().forEach(System.out::println);
        System.out.println("------------------------");
        graph.mstWithKruskal().forEach(System.out::println);



    }

    @Test
    public void testMst2() {
        Graph<Object, Double> graph = Data.undirectedGraph(Data.MST_02);
        graph.mstWithPrim().forEach(System.out::println);
        System.out.println("------------------------");
        graph.mstWithKruskal().forEach(System.out::println);
    }

    @Test
    public void testShortestPath1() {
        Graph<Object, Double> graph = Data.directedGraph(Data.SP);
        //测试shortestPathWithBellmanFord的正常情况
        Map<Object, PathInfo<Object, Double>> bellmanFordSp = graph.shortestPathWithBellmanFord("A");
        if (bellmanFordSp == null) return;
        bellmanFordSp.forEach((Object v, PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });

        System.out.println("---------------------");

        //测试shortestPathWithDijkstra的正常情况
        Map<Object, PathInfo<Object, Double>> Dijkstra = graph.shortestPathWithDijkstra("A");
        Dijkstra.forEach((Object v, PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });
    }

    @Test
    public void testShortestPath2() {
        Graph<Object, Double> graph = Data.directedGraph(Data.NEGATIVE_WEIGHT1);
        //测试shortestPathWithBellmanFord,有负全边的情况-----结果正确
        Map<Object, PathInfo<Object, Double>> bellmanFordSp = graph.shortestPathWithBellmanFord("A");
        if (bellmanFordSp == null) return;
        bellmanFordSp.forEach((Object v, PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });

        System.out.println("---------------------");

        //测试shortestPathWithDijkstra,有负全边的情况-----结果错误
        Map<Object, PathInfo<Object, Double>> Dijkstra = graph.shortestPathWithDijkstra("A");
        Dijkstra.forEach((Object v, PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });
    }



    @Test
    public void testShortestPath3() {
        //用Bellman-Ford检测负权环
        Graph<Object, Double> graph = Data.directedGraph(Data.NEGATIVE_WEIGHT2);
        Map<Object, PathInfo<Object, Double>> sp = graph.shortestPathWithBellmanFord(0);
        if (sp == null) return;
        sp.forEach((Object v, PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });
    }



}
