import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;


    public class Deserialize implements JsonDeserializer<DirectedWeightedGraph> {
        @Override
        public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            HashMap<Integer, NodeData> n = new HashMap<>();
            HashMap<Integer, HashMap<Integer, EdgeData>>e = new HashMap<>();
            DirectedWeightedGraph g = new DirectedWeightedGraph();

            JsonArray nodesJson = jsonObject.get("Nodes").getAsJsonArray();
            JsonArray edgesJson = jsonObject.get("Edges").getAsJsonArray();
            for (JsonElement node : nodesJson) {
                int key = node.getAsJsonObject().get("id").getAsInt();
                String[] pos = node.getAsJsonObject().get("pos").getAsString().split(",");
                GeoLocation location = new GeoLocation(Double.parseDouble(pos[0]), Double.parseDouble(pos[1]), Double.parseDouble(pos[2]));
                g.addNode(new NodeData(key,location));

            }
            for (JsonElement edge : edgesJson) {
                int src = edge.getAsJsonObject().get("src").getAsInt();
                int dest = edge.getAsJsonObject().get("dest").getAsInt();
                double w = edge.getAsJsonObject().get("w").getAsDouble();
                g.connect(src, dest, w);
            }
            return g;
        }

    }

