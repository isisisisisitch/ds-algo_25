package ca.bytetube._09_graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    private Comparator<Edge<V, E>> edgeComparator = (o1, o2) -> weightManager.compare(o1.weight, o2.weight);


    public ListGraph() {
    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value) &&
                    Objects.equals(inEdges, vertex.inEdges) &&
                    Objects.equals(outEdges, vertex.outEdges);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return "vertex = " + value;
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
            weight = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to) &&
                    Objects.equals(weight, edge.weight);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        public EdgeInfo<V, E> info() {
            return new EdgeInfo<V, E>(weight, from.value, to.value);
        }
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
    public void addVertex(V vertex) {
        if (vertices.containsKey(vertex)) return;
        vertices.put(vertex, new Vertex<>(vertex));
    }


    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //1.判断fromVertex，toVertex是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        //代码能够来到这里，说明这条边的起点终点一定是存在的
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

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
        //1.判断fromVertex，toVertex是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        //代码能够来到这里，说明这条边的起点终点一定是存在的
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);

        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;
        //对于集合set元素的删除，不能一边遍历一边删除，会有ConcurrentModificationException，
        //解决方法：use Iterator to iterate and remove !!!

//        for (Edge<V, E> edge : vertex.outEdges) {
//            removeEdge(edge.from.value,edge.to.value);
//        }
//
//        for (Edge<V, E> edge : vertex.inEdges) {
//            removeEdge(edge.to.value,edge.from.value);
//        }

        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }


    }


    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        queue.add(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            System.out.println(poll);
            for (Edge<V, E> outEdge : poll.outEdges) {
                if (visitedVertices.contains(outEdge.to)) continue;
                queue.add(outEdge.to);
                visitedVertices.add(outEdge.to);

            }
        }

    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        queue.add(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
//            System.out.println(poll);
            if (visitor.visit(poll.value)) return;
            for (Edge<V, E> outEdge : poll.outEdges) {
                if (visitedVertices.contains(outEdge.to)) continue;
                queue.add(outEdge.to);
                visitedVertices.add(outEdge.to);

            }
        }

    }

    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        dfs(beginVertex, new HashSet<>());

    }

    private void dfs(Vertex<V, E> beginVertex, Set<Vertex<V, E>> visitedVertices) {
        System.out.println(beginVertex.value + " ");
        visitedVertices.add(beginVertex);
        for (Edge<V, E> outEdge : beginVertex.outEdges) {
            if (visitedVertices.contains(outEdge.to)) continue;
            dfs(outEdge.to, visitedVertices);
        }
    }

    /**
     * 步骤：
     * 1.先将起点压栈并遍历（弹栈顶），再存入set中
     * 2.如果栈顶元素的outEdge所对应的顶点之前没有存入过set中，则要将outEdge的from和to顶点依次顺序压栈
     * 3.再次遍历（弹栈顶），并存入set中
     */
    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        stack.push(beginVertex);//1
//        System.out.println();
        if (visitor.visit(beginVertex.value)) return;
        visitedVertices.add(beginVertex);//1

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();//1 //3 //7
//            System.out.println(vertex.value + " ");//1 //3  //7

            for (Edge<V, E> outEdge : vertex.outEdges) {//vertex = 1, 3
                if (visitedVertices.contains(outEdge.to)) continue;
                stack.push(outEdge.from);//1, 3
                stack.push(outEdge.to);//3, 7
                if (visitor.visit(outEdge.to.value)) return;
                visitedVertices.add(outEdge.to);//3 ,7

                //能够让迭代过程继续深入，去访问更深一层的点
                break;

            }
        }

    }

    /**
     * 1.需要准备一个map（用来存图的inDegree信息），一个queue（缓冲区），一个list（存排序结果）
     * 2.将graph中inDegree=0的顶点放入queue，inDegree !=0 放入map中
     * 3.出队头元素，放入list中，并且更新map中的inDegree信息
     * 4.从map中找到inDegree=0的顶点，并放入queue中
     * 5.不断重复3，4的过程，直到queue为空
     */
    @Override
    List<V> topologicalSort(V begin) {
        //1. 需要准备一个map（用来存图的inDegree信息），一个queue（缓冲区），一个list（存排序结果）
        Map<Vertex<V, E>, Integer> map = new HashMap<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        List<V> list = new LinkedList<>();
        //2.将graph中inDegree=0的顶点放入queue，inDegree !=0 放入map中
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int size = vertex.inEdges.size();
            if (size == 0) queue.offer(vertex);
            else map.put(vertex, size);
        });

        //5.不断重复3，4的过程，直到queue为空
        while (!queue.isEmpty()) {
            //3.出队头元素，放入list中，并且更新map中的inDegree信息
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = map.get(edge.to) - 1;
                //  4.从map中找到inDegree=0的顶点，并放入queue中
                if (toIn == 0) queue.offer(edge.to);
                else map.put(edge.to, toIn);
            }
        }

        return list;
    }

    @Override
    Set<EdgeInfo<V, E>> mst() {

        return kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        //A mst边集
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //S mst点集
        Set<Vertex<V, E>> addedVertices = new HashSet<>();
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        Vertex<V, E> vertex = iterator.next();//A点
        addedVertices.add(vertex);
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(vertex.outEdges, edgeComparator);

        while (!minHeap.isEmpty() && addedVertices.size() < vertices.size()) {
            Edge<V, E> edge = minHeap.remove();
            if (addedVertices.contains(edge.to)) continue;
            //AB--->A mst边集
            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);

            //将B点所有的outEdges放到heap中，继续寻找A mst边集中的最小的crossing edge
            minHeap.addAll(edge.to.outEdges);
        }

        return edgeInfos;
    }

    private Set<EdgeInfo<V, E>> kruskal() {

        return null;
    }

}
