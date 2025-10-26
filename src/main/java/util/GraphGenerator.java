package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphGenerator {

    private static final Random rand = new Random();

    public static void generateAllGraphs(String basePath) {
        generateCategory(basePath + "/small_graphs.json", 5, 6, 29);
        generateCategory(basePath + "/medium_graphs.json", 10, 30, 299);
        generateCategory(basePath + "/large_graphs.json", 10, 300, 999);
        generateCategory(basePath + "/extralarge_graphs.json", 3, 1000, 1999);
    }

    private static void generateCategory(String outputPath, int count, int minV, int maxV) {
        List<Map<String, Object>> allGraphs = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int vertices = rand.nextInt(maxV - minV + 1) + minV;
            allGraphs.add(createGraphData(vertices, "graph_" + (i + 1)));
        }

        Map<String, Object> root = new HashMap<>();
        root.put("graphs", allGraphs);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(outputPath)) {
            gson.toJson(root, writer);
            System.out.println("✅ " + outputPath + " created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> createGraphData(int vertices, String name) {
        Map<String, Object> graphData = new HashMap<>();
        graphData.put("name", name);
        graphData.put("vertices", vertices);

        List<Map<String, Object>> edges = new ArrayList<>();
        int edgeCount = Math.min(vertices * 3, vertices * (vertices - 1) / 4);

        for (int i = 0; i < edgeCount; i++) {
            int src = rand.nextInt(vertices);
            int dest = rand.nextInt(vertices);
            if (src == dest) continue;
            int weight = rand.nextInt(100) + 1;

            Map<String, Object> edge = Map.of(
                    "src", src,
                    "dest", dest,
                    "weight", weight
            );
            edges.add(edge);
        }

        graphData.put("edges", edges);
        return graphData;
    }

    public static void main(String[] args) {
        generateAllGraphs("src/main/resources/graphs");
    }
}
