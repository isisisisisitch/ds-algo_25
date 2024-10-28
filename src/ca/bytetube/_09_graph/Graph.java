package ca.bytetube._09_graph;

import java.util.List;
import java.util.Set;

public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;
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

    abstract List<V> topologicalSort(V begin);

    abstract Set<EdgeInfo<V, E>> mst();


    public static class EdgeInfo<V, E> {
        E weight;
        V from;
        V to;

        public EdgeInfo(E weight, V from, V to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }


        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        @Override
        public String toString() {
            return
                    "{from=" + from + ", to=" + to +
                            ",weight=" + weight + "}";
        }
    }

    public abstract static class VertexVisitor<V> {

        public abstract boolean visit(V val);
    }

    public interface WeightManager<E> {
        public int compare(E w1, E w2);

        public E add(E w1, E w2);

        public E zero();
    }

}
