import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class seri {

    public static void main(String[] args) {
		serialize();
    }

    private static void serialize() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph("Ariel University Parking");
        NodeData a0 = new NodeData(0, new GeoLocation(0, 0, 0));
        NodeData a1 = new NodeData(1, new GeoLocation(1, 6, 0));
        NodeData a2 = new NodeData(2, new GeoLocation(4, 5, 0));
        NodeData a3 = new NodeData(3, new GeoLocation(-1, 4, 0));
        NodeData a4 = new NodeData(4, new GeoLocation(3, 0, 0));
        NodeData a5 = new NodeData(5, new GeoLocation(2, -2, 0));
        NodeData a6 = new NodeData(6, new GeoLocation(-1, -8, 0));
        NodeData a7 = new NodeData(7, new GeoLocation(-6, -6, 0));
        NodeData a8 = new NodeData(8, new GeoLocation(-7, -1, 0));
        NodeData a9 = new NodeData(9, new GeoLocation(-4, -1, 0));
        graph.addNode(a0);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.addNode(a4);
        graph.addNode(a5);
        graph.addNode(a6);
        graph.addNode(a7);
        graph.addNode(a8);
        graph.addNode(a9);
        graph.connect(0, 1, 1.5);
        graph.connect(0, 2, 20);
        graph.connect(0, 3, 8.2);
        graph.connect(0, 4, 5.2);
        graph.connect(0, 5, 4.9);
        graph.connect(0, 6, 2.3);
        graph.connect(0, 7, 1.3);
        graph.connect(0, 8, 0.4);
        graph.connect(0, 9, 1.2);
        graph.connect(9, 1, 4.2);
        graph.connect(1, 2, 6.3);
        graph.connect(2, 3, 1.2);
        graph.connect(3, 4, 0.3);
        graph.connect(4, 5, 5.3);
        graph.connect(5, 6, 6.1);
        graph.connect(6, 7, 4.3);
        graph.connect(7, 8, 3.9);
        graph.connect(8, 9, 9.2);
        graph.connect(5, 0, 10);

        //Make JSON!!
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(graph);
        System.out.println(json);

        //Write JSON to file
        try {
            PrintWriter pw = new PrintWriter(new File("Data\\TestGson.json"));
            pw.write(json);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }
}

