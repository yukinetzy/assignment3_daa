package graph;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int vertices;
    private List<EdgeData> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, double weight) {
        edges.add(new EdgeData(src, dest, weight));
    }

    public int V() {
        return vertices;
    }

    public int E() {
        return edges.size();
    }

    public List<EdgeData> getEdges() {
        return edges;
    }

    public EdgeWeightedGraph toEdgeWeightedGraph() {
        EdgeWeightedGraph G = new EdgeWeightedGraph(vertices);
        for (EdgeData e : edges) {
            G.addEdge(new Edge(e.src, e.dest, e.weight));
        }
        return G;
    }

    public static class EdgeData {
        int src;
        int dest;
        double weight;

        public EdgeData(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
