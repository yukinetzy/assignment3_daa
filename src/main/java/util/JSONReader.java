package util;

import com.google.gson.*;
import graph.Graph;
import java.io.FileReader;
import java.util.*;

public class JSONReader {

    public static List<Graph> readGraphs(String filePath) {
        List<Graph> graphs = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray graphsArray = root.getAsJsonArray("graphs");

            for (JsonElement gElement : graphsArray) {
                JsonObject g = gElement.getAsJsonObject();
                int vertices = g.get("vertices").getAsInt();
                Graph graph = new Graph(vertices);

                JsonArray edges = g.getAsJsonArray("edges");
                for (JsonElement eElement : edges) {
                    JsonObject e = eElement.getAsJsonObject();
                    int src = e.get("src").getAsInt();
                    int dest = e.get("dest").getAsInt();
                    int weight = e.get("weight").getAsInt();
                    graph.addEdge(src, dest, weight);
                }

                graphs.add(graph);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return graphs;
    }
}
