import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DirectedWeightedGraph implements Directed_Weighted_Graph {
    private HashMap<Integer, Node_Data> vert;
    private HashMap<String, Edge_Data> edg;
    private int NodeSize;
    private int EdgSize;
    private int MC;

    public DirectedWeightedGraph() {
        this.NodeSize = 0;
        this.EdgSize = 0;
        this.MC = 0;
        this.vert = new HashMap<>();
        this.edg = new HashMap<>();
    }


    @Override
    public Node_Data getNode(int key) {
        return this.vert.get(key);
    }

    @Override
    public Edge_Data getEdge(int src, int dest) {
        String Vec = src + "-" + dest;
        return this.edg.get(Vec);
    }

    @Override
    public void addNode(Node_Data n) {
        if (!this.vert.containsKey(n.getKey())) {
            this.vert.put(n.getKey(), n);
            this.NodeSize++;
            this.MC++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (src == dest || !this.vert.containsKey(src) || !this.vert.containsKey(dest) || getEdge(src, dest) != null)
            return;
        Edge_Data E = new EdgeData(src, dest, w);
        String Vec = src + "-" + dest;
        edg.put(Vec, E);

        this.EdgSize++;
        this.MC++;

    }

    @Override
    public Iterator<Node_Data> nodeIter() {
        return this.vert.values().iterator();
    }

    @Override
    public Iterator<Edge_Data> edgeIter() {
        return this.edg.values().iterator();

    }

    @Override
    public Iterator<Edge_Data> edgeIter(int node_id) {
      ArrayList<Edge_Data> ThisNodeV = new ArrayList<>();
        if (this.vert.containsKey(node_id)) {
            for (Node_Data N : this.vert.values()) {
                String Vec = node_id + "-" + N.getKey();
                if (this.edg.containsKey(Vec))
                    ThisNodeV.add(this.edg.get(Vec));

            }
        }
        return (Iterator<Edge_Data>) ThisNodeV;

    }


    @Override
    public Node_Data removeNode(int key) {
        if (!this.vert.containsKey(key))
            return null;
        if (!this.vert.isEmpty()) {
            for (Node_Data N : this.vert.values()) {
                if (N.getKey() == key)
                    continue;

                String Vec1 = key + "-" + N.getKey();
                String Vec2 = N.getKey() + "-" + key;

                if (this.edg.containsKey(Vec1))
                    removeEdge(key, N.getKey());

                if (this.edg.containsKey(Vec2))
                    removeEdge(N.getKey(), key);

            }
        }

        this.NodeSize--;
        this.MC++;
        return this.vert.remove(key);
    }

    @Override
    public Edge_Data removeEdge(int src, int dest) {
        String Vec = src + "-" + dest;
        if (!this.edg.containsKey(Vec))
            return null;

        this.EdgSize--;
        this.MC++;
        return this.edg.remove(Vec);
    }

    @Override
    public int nodeSize() {

        return this.NodeSize;
    }

    @Override
    public int edgeSize() {

        return this.EdgSize;
    }

    @Override
    public int getMC() {

        return this.MC;
    }


}
