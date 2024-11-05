package ca.bytetube._09_graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    private Comparator<Edge<V, E>> edgeComparator = (o1, o2) -> weightManager.compare(o1.weight, o2.weight);


    public ListGraph() {
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
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

        return prim();
    }


    @Override
    public Map<V, E> shortestPathWithoutPathInfo(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        /**
         * Map<V,E> paths = new HashMap<>();
         *         paths.put("B",10);
         *         paths.put("C",50);
         *         paths.put("D",30);
         *         需要从paths中找到weight最小的，准备起飞的点，第一次找到B点
         *         如果我们继续把B点留在map中，可能会被重复选，
         *         因此我们需要再增加一个map，用来存储已经起飞的顶点信息
         */

        Map<Vertex<V, E>, E> paths = new HashMap<>();//red
        Map<V, E> selectedPaths = new HashMap<>();//green

        //1.初始化paths：将B,D,E放入到paths中
        for (Edge<V, E> edge : beginVertex.outEdges) {
            paths.put(edge.to, edge.weight);
        }

        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minEntry = getMinPathWithoutPathInfo(paths);
            Vertex<V, E> minVertex = minEntry.getKey();
            E minWeight = minEntry.getValue();
            selectedPaths.put(minVertex.value, minWeight);//B点起飞
            paths.remove(minVertex);

            //Relaxation
            //对起飞点所有的outEdges所对应的顶点到达起点的weight值，计算更新后weight和之前weight的大小关系
            //如果newWeight < oldWeight,说明找到了A点到达其他顶点的更小的路径走法，则更新path
            //否则，不更新
            for (Edge<V, E> edge : minVertex.outEdges) {
                //计算newWeight
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);

                //计算oldWeight
                E oldWeight = paths.get(edge.to);
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }

            }
        }


        return selectedPaths;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();

        //paths初始化 默认图中所有点之间直接相连的path是最短路径
        for (Edge<V, E> edge : edges) {
            //其他点到达A点的最短路径信息
            Map<V, PathInfo<V, E>> map = paths.get(edge.from.value);
            if (map == null) {
                map = new HashMap<>();
                paths.put(edge.from.value, map);
            }

            PathInfo<V, E> pathInfo = new PathInfo<>(edge.weight);
            pathInfo.edgeInfos.add(edge.info());
            map.put(edge.to.value, pathInfo); // 对于A点来说：（AB,10）,(AE,8) 对于B点来说：（BE,-5）,(BC,8)

        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
                    if (v1.equals(v2) || v2.equals(v3) || v1.equals(v3)) return;
                    //v1 ->v2
                    PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);
                    if (path12 == null) return;
                    //v2->v3
                    PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) return;

                    E newWeight = weightManager.add(path12.weight, path23.weight);
                    PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);
                    if (path13 != null && weightManager.compare(newWeight, path13.weight) >= 0) return;

                    if (path13 == null) {
                        path13 = new PathInfo<>();
                        paths.get(v1).put(v3, path13);
                    } else path13.edgeInfos.clear();
                    path13.weight = newWeight;
                    path13.edgeInfos.addAll(path12.edgeInfos);
                    path13.edgeInfos.addAll(path23.edgeInfos);
                });
            });
        });


        return paths;
    }

    private PathInfo<V, E> getPathInfo(V v1, V v2, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> map = paths.get(v1);
        if (map == null) return null;
        return map.get(v2);
    }

    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();

        selectedPaths.put(beginVertex.value, new PathInfo<>(weightManager.zero()));

        for (int i = 0; i < vertices.size() - 1; i++) {
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath == null) continue;
                relaxationForBellmanFord(edge, fromPath, selectedPaths);
            }

        }

        for (Edge<V, E> edge : edges) {
            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
            if (fromPath == null) continue;
            if (relaxationForBellmanFord(edge, fromPath, selectedPaths)) {
                throw new RuntimeException("there is a negative cycle in graph !");
            }
        }


        selectedPaths.remove(beginVertex.value);

        return selectedPaths;
    }

    private boolean relaxationForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> selectedPaths) {
        //edge = DC, fromPath = AD, AC = AD + DC
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        PathInfo<V, E> oldPath = selectedPaths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            selectedPaths.put(edge.to.value, oldPath);
        } else oldPath.edgeInfos.clear();

        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());

        return true;

    }


    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();//red
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();//green

        //1.初始化paths：将B,D,E放入到paths中
        for (Edge<V, E> edge : beginVertex.outEdges) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = edge.weight;
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);
        }

        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);//B点起飞
            paths.remove(minVertex);

            //Relaxation
            //对起飞点所有的outEdges所对应的顶点到达起点的weight值，计算更新后weight和之前weight的大小关系
            //如果newWeight < oldWeight,说明找到了A点到达其他顶点的更小的路径走法，则更新path
            //否则，不更新
            for (Edge<V, E> edge : minVertex.outEdges) {
                if (selectedPaths.containsKey(edge.to.value)) continue;
                relaxationForDijkstra(minPath, edge, paths);
            }
        }


        return selectedPaths;
    }

    /**
     * @param minPath 起点到达某个顶点的最短路径信息（这个顶点已经从桌面起飞）
     * @param edge    已经找到最短路径信息的顶点的outEdge边
     * @param paths   红色容器
     */
    private void relaxationForDijkstra(PathInfo<V, E> minPath, Edge<V, E> edge, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        //Relaxation
        //对起飞点所有的outEdges所对应的顶点到达起点的weight值，计算更新后weight和之前weight的大小关系
        //如果newWeight < oldWeight,说明找到了A点到达其他顶点的更小的路径走法，则更新path
        //否则，不更新

        //计算newWeight
        E newWeight = weightManager.add(minPath.weight, edge.weight);

        //计算oldWeight
        PathInfo<V, E> oldPath = paths.get(edge.to);
        if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = newWeight;
            //A->E = A-C(A-D-C) + C-E
            path.edgeInfos.addAll(minPath.edgeInfos);
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);
        }


    }


    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {

        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = nextEntry;
            }
        }

        return minEntry;
    }


    private Map.Entry<Vertex<V, E>, E> getMinPathWithoutPathInfo(Map<Vertex<V, E>, E> paths) {
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, E> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue(), minEntry.getValue()) < 0) {
                minEntry = nextEntry;
            }
        }

        return minEntry;
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
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(edges, edgeComparator);

        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });

        while (!minHeap.isEmpty() && edgeInfos.size() < vertices.size() - 1) {
            Edge<V, E> edge = minHeap.remove();
            if (uf.isSame(edge.to, edge.from)) continue;
            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }


        return edgeInfos;
    }

}
