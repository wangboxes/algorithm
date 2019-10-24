package com.wbx._03_graph;

import org.junit.Test;

import java.util.List;

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
    public void testTopologicalSort() {
        Graph<Object, Double> graph = Data.directedGraph(Data.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }





}
