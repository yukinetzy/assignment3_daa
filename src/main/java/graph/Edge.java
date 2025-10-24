package graph;

public class Edge implements Comparable<Edge> {
    private final int u;
    private final int v;
    private final double weight;

    public Edge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    public int either() { return u; }
    public int other(int x) { return x == u ? v : u; }
    public int getU(){ return u; }
    public int getV(){ return v; }
    public double getWeight(){ return weight; }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString(){
        return String.format("(%d-%d: %.2f)", u, v, weight);
    }
}
