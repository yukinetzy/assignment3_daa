package graph;
import java.util.*;

public class Graph {
    private final int V;
    private int E;
    private final List<List<Edge>> adj;
    private final List<Edge> edges;

    public Graph(int V) {
        this.V = V;
        this.adj = new ArrayList<>();
        for(int i=0;i<V;i++) adj.add(new ArrayList<>());
        this.edges = new ArrayList<>();
        this.E = 0;
    }
    public void addEdge(int u, int v, double w) {
        Edge e = new Edge(u, v, w);
        adj.get(u).add(e);
        adj.get(v).add(e);
        edges.add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) { return adj.get(v); }
    public Iterable<Edge> edges() { return edges; }
    public int V() { return V; }
    public int E() { return E; }
}
