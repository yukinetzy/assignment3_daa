package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {
    public static void exportResults(String filePath, List<MSTResult> results) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Vertices,Edges,PrimCost,KruskalCost,PrimTime(ms),KruskalTime(ms)\n");
            for (MSTResult r : results) {
                writer.write(String.format("%d,%d,%.2f,%.2f,%d,%d\n",
                        r.vertices, r.edges, r.primCost, r.kruskalCost, r.primTime, r.kruskalTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
