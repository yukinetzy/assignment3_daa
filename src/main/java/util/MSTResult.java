package util;

public class MSTResult {
    public int vertices;
    public int edges;
    public double primCost;
    public double kruskalCost;
    public long primTime;
    public long kruskalTime;

    public MSTResult(int v, int e, double pC, double kC, long pT, long kT) {
        this.vertices = v;
        this.edges = e;
        this.primCost = pC;
        this.kruskalCost = kC;
        this.primTime = pT;
        this.kruskalTime = kT;
    }
}
