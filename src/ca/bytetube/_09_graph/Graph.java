package ca.bytetube._09_graph;

public abstract class Graph<V, E> {

    public Graph() {
    }


    public abstract int verticesSize();

    public abstract int edgesSize();

    public abstract void addVertex(V vertex);

    public abstract void removeVertex(V vertex);

    public abstract void addEdge(V fromVertex, V toVertex);

    public abstract void addEdge(V fromVertex, V toVertex, E weight);

    public abstract void removeEdge(V fromVertex, V toVertex);

    public abstract void bfs(V begin);

    public abstract void bfs(V begin, VertexVisitor<V> visitor);

    public abstract void dfs(V begin);

    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    public abstract static class VertexVisitor<V> {

        public abstract boolean visit(V val);
    }
}
