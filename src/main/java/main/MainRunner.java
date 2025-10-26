package main;

import algorithm.KruskalMST;
import algorithm.PrimMST;
import graph.Graph;
import util.*;
import util.Timer;

import java.util.*;

public class MainRunner {

    public static void main(String[] args) {
        String baseInput = "src/main/resources/graphs/";
        String baseOutput = "src/main/resources/output/";

        Map<String, String> categories = Map.of(
                "small", "small_graphs.json",
                "medium", "medium_graphs.json",
                "large", "large_graphs.json",
                "extralarge", "extralarge_graphs.json"
        );

        for (String type : categories.keySet()) {
            String inputPath = baseInput + categories.get(type);
            String csvPath = baseOutput + "results_" + type + ".csv";

            System.out.println("🚀 Processing " + type + " graphs...");
            List<Graph> graphs = JSONReader.readGraphs(inputPath);
            List<MSTResult> results = new ArrayList<>();

            for (Graph g : graphs) {
                Timer timer = new Timer();

                timer.start();
                PrimMST prim = new PrimMST(g.toEdgeWeightedGraph());
                long primTime = timer.stop();

                timer.start();
                KruskalMST kruskal = new KruskalMST(g.toEdgeWeightedGraph());
                long kruskalTime = timer.stop();

                results.add(new MSTResult(g.V(), g.E(), prim.weight(), kruskal.weight(), primTime, kruskalTime));
            }

            CSVExporter.exportResults(csvPath, results);
            System.out.println("! Results saved to " + csvPath);
        }

        System.out.println("* All experiments completed!");
    }
}
