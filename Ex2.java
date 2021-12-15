import Interfaces.Directed_Weighted_Graph;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;

/**
 * This class is the main class for api.Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    private static final String path = Paths.get("").toAbsolutePath() + "/src/main/java/assignment2/data/";
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static Directed_Weighted_Graph getGrapg(String json_file) throws IOException, ParseException {
        Directed_Weighted_Graph ans = new DirectedWeightedGraph();
        JSONParser parser=new JSONParser();
        JSONObject ob= (JSONObject) parser.parse(new FileReader(json_file));
        JSONArray edge= (JSONArray) ob.get("Edges");
        JSONArray node=(JSONArray) ob.get("Nodes");
        for(Object i:node){
            JSONObject o=(JSONObject) i;
            String str=o.get("pos").toString();
            String [] Xyz=str.split(",");
            GeoLocation geo=new GeoLocation(Double.parseDouble(Xyz[0]),Double.parseDouble(Xyz[1]),Double.parseDouble(Xyz[2]));
            Node_Data n=new NodeData(Integer.parseInt(o.get("id").toString()),geo);
            ans.addNode(n);
        }
        for(Object j:edge){
            JSONObject o=(JSONObject) j;
            int src=Integer.parseInt(o.get("src").toString());
            double weight= Double.parseDouble(o.get("w").toString());
            int dest=Integer.parseInt(o.get("dest").toString());
            ans.connect(src,dest, weight);
        }

        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) throws IOException, ParseException {
        Directed_Weighted_Graph g=getGrapg(json_file);
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithms();
        ans.init(g);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) throws IOException, ParseException {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new GUIGraph(alg);
    }
    public static void main(String[] args) throws IOException, ParseException {
        Ex2.runGUI("Data\\G1.json");
    }
}