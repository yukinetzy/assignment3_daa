package main;

import algorithm.KruskalMST;
import algorithm.PrimMST;
import graph.Graph;
import util.*;
import util.Timer;

import java.util.*;

public class MainRunner {

    public static void main(String[] args) {
        String inputPath = "src/main/resources/graphs/generated_graphs.json";
        String csvPath = "src/main/resources/output/results.csv";

        List<Graph> graphs = JSONReader.readGraphs(inputPath);
        List<MSTResult> results = new ArrayList<>();

        for (Graph g : graphs) {
            System.out.println("Running on graph with " + g.V() + " vertices and " + g.E() + " edges...");

            Timer timer = new Timer();

            // Run Prim
            timer.start();
            PrimMST prim = new PrimMST(g.toEdgeWeightedGraph());
            long primTime = timer.stop();

            // Run Kruskal
            timer.start();
            KruskalMST kruskal = new KruskalMST(g.toEdgeWeightedGraph());
            long kruskalTime = timer.stop();

            results.add(new MSTResult(g.V(), g.E(), prim.weight(), kruskal.weight(), primTime, kruskalTime));
        }

        CSVExporter.exportResults(csvPath, results);
        System.out.println("✅ Results written to " + csvPath);
    }
}
