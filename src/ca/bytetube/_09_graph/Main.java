package ca.bytetube._09_graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        test5();
//        Graph<String, Integer> graph = new ListGraph<>();
//        graph.addEdge("v1", "v0", 9);
//        graph.addEdge("v1", "v2", 3);
//        graph.addEdge("v0", "v4", 6);
//        graph.addEdge("v2", "v0", 2);
//        graph.addEdge("v2", "v3", 5);
//        graph.addEdge("v3", "v4", 1);
//        graph.dfs("v1", new Graph.VertexVisitor<String>() {
//            @Override
//            public boolean visit(String val) {
//                System.out.println(val + " ");
//                return false;
//            }
//        });
    }

    public static void test5() {
        Graph<Object, Double> directGraph = directGraph(Data.SP);
        Map<Object, Double> paths = directGraph.shortestPath("A");
        System.out.println(paths);
    }

    public static void test4() {
        Graph<Object, Double> unDirectGraph = unDirectGraph(Data.MST_01);
        Set<Graph.EdgeInfo<Object, Double>> mst = unDirectGraph.mst();
        for (Graph.EdgeInfo<Object, Double> ele : mst) {
            System.out.println(ele);
        }
    }

    public static void test3() {
        Graph<Object, Double> directGraph = directGraph(Data.TOPO);
        List<Object> list = directGraph.topologicalSort(3);
        System.out.println(list);
    }

    public static void test2() {
        Graph<Object, Double> unDirectGraph = unDirectGraph(Data.DFS_01);
        unDirectGraph.dfs(1);
    }

    public static void test1() {
        Graph<Object, Double> unDirectGraph = unDirectGraph(Data.BFS_03);
        unDirectGraph.bfs(0);
    }

    private static Graph<Object, Double> directGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager1);
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

    static Graph.WeightManager<Double> weightManager1 = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }
    };

    private static Graph<Object, Double> unDirectGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager1);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);//离散的点
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
