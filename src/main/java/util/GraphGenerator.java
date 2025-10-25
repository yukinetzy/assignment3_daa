package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graph.Graph;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphGenerator {

    private static Random rand = new Random();

    public static void generateGraphs(String outputPath) {
        List<Map<String, Object>> allGraphs = new ArrayList<>();

        // Small (4–6)
        for (int i = 0; i < 5; i++)
            allGraphs.add(createGraphData("small_" + (i+1), rand.nextInt(3)+4));

        // Medium (10–15)
        for (int i = 0; i < 10; i++)
            allGraphs.add(createGraphData("medium_" + (i+1), rand.nextInt(6)+10));

        // Large (20–30)
        for (int i = 0; i < 10; i++)
            allGraphs.add(createGraphData("large_" + (i+1), rand.nextInt(11)+20));

        // Extra Large (35–50)
        for (int i = 0; i < 3; i++)
            allGraphs.add(createGraphData("extralarge_" + (i+1), rand.nextInt(16)+35));

        Map<String, Object> root = new HashMap<>();
        root.put("graphs", allGraphs);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(outputPath)) {
            gson.toJson(root, writer);
            System.out.println("✅ Graphs successfully generated at: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> createGraphData(String name, int vertices) {
        Map<String, Object> graphData = new HashMap<>();
        graphData.put("name", name);
        graphData.put("vertices", vertices);

        List<Map<String, Object>> edges = new ArrayList<>();
        int density = vertices + rand.nextInt(vertices * 2); // количество рёбер

        for (int i = 0; i < density; i++) {
            int src = rand.nextInt(vertices);
            int dest = rand.nextInt(vertices);
            if (src == dest) continue;
            int weight = rand.nextInt(50) + 1;
            Map<String, Object> edge = Map.of("src", src, "dest", dest, "weight", weight);
            edges.add(edge);
        }

        graphData.put("edges", edges);
        return graphData;
    }

    public static void main(String[] args) {
        generateGraphs("src/main/resources/graphs/generated_graphs.json");
    }
}
