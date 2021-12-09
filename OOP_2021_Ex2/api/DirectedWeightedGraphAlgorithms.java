import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DirectedWeightedGraphAlgorithms implements Directed_Weighted_Graph_Algorithms {
    private Directed_Weighted_Graph G;

    enum Operation {Save, Load}

    public DirectedWeightedGraphAlgorithms(Directed_Weighted_Graph g) {
        init(g);
    }


    @Override
    public void init(Directed_Weighted_Graph g) {
        this.G = g;
    }

    @Override
    public Directed_Weighted_Graph getGraph() {

        return this.G;
    }

    @Override
    public Directed_Weighted_Graph copy() {
        DirectedWeightedGraph GraphClone = new DirectedWeightedGraph();
        for (Iterator<Node_Data> it = G.nodeIter(); it.hasNext(); ) {
            Node_Data run = it.next();
            GraphClone.addNode(run);
        }
        for (Iterator<Node_Data> it = G.nodeIter(); it.hasNext(); ) {
            Node_Data run = it.next();
            if (GraphClone.edgeSize() == G.edgeSize()) break;
            for (Iterator<Edge_Data> iter = G.edgeIter(run.getKey()); iter.hasNext(); ) {
                EdgeData run1 = (EdgeData) iter.next();
                GraphClone.connect(run1.getSrc(), run1.getDest(), run1.getWeight());
            }
        }
        return GraphClone;
    }

    @Override
    public boolean isConnected() {
        for (Iterator<Node_Data> it = G.nodeIter(); it.hasNext(); ) {
            Node_Data nodes = it.next();
            if (!BFS(nodes.getKey())) {
                return false;
            }
        }
        for (Iterator<Node_Data> it = G.nodeIter(); it.hasNext(); ) {
            Node_Data nodes = it.next();
            nodes.setTag(-1);
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        Node_Data start = this.G.getNode(src);
        Node_Data end = this.G.getNode(dest);
        if (end == null || start == null) {
            return -1;
        } //if src or dest not in graph there is no path
        if (src == dest) {
            return 0;
        } //the path from node to itself is 0
        Dijkstra(src, dest);
        if (end.getWeight() != 0) {
            return end.getWeight();
        }
        return -1;
    }

    private double Dijkstra(int start, int end) {
        Node_Data Start = G.getNode(start);
        Node_Data End = G.getNode(end);

        PriorityQueue<Node_Data> q = new PriorityQueue<>(Comparator.comparingDouble(Node_Data::getWeight));

        for (Iterator<Node_Data> it = G.nodeIter(); it.hasNext(); ) {
            Node_Data node = it.next();
            node.setInfo("null");
            node.setWeight(Double.MAX_VALUE);
        }
        Start.setWeight(0);
        q.add(Start);
        while (!q.isEmpty()) {

            Node_Data temp = q.remove();
            if (!temp.getInfo().contains("visited")) {
                temp.setInfo(temp.getInfo() + " visited");
                if (temp.getKey() == End.getKey()) {
                    return temp.getWeight();
                }

                for (Iterator<Edge_Data> it = G.edgeIter(temp.getKey()); it.hasNext(); ) {
                    Edge_Data e = it.next();
                    Node_Data neighbor = G.getNode(e.getDest());

                    if (!neighbor.getInfo().contains("visited")) {
                        double distance = temp.getWeight() + G.getEdge(temp.getKey(), neighbor.getKey()).getWeight();
                        if (distance < neighbor.getWeight()) {
                            neighbor.setWeight(distance);
                            neighbor.setInfo(temp.getKey() + "");
                            q.add(neighbor);
                        }
                    }
                }
            }
        }

        return -1;
    }


    @Override
    public List<Node_Data> shortestPath(int src, int dest) {
        Node_Data start = this.G.getNode(src);
        Node_Data end = this.G.getNode(dest);
        if (end == null || start == null) {
            return null;
        }//if src or dest not in graph there is no path
        LinkedList<Node_Data> list = new LinkedList<>();
        if (src == dest) {
            list.add(start);
            return list;
        }//the path from node to itself
        Dijkstra(src, dest); //if the weight of node dest is 0: there is no path
        if (end.getWeight() != 0) {//go through all previous nodes from dest to src and add to list
            while (end.getTag() != end.getKey()) { //the tag of src is src
                list.addFirst(end);
                end = this.G.getNode(end.getTag());
            }
            list.addFirst(start);
            return list;
        }
        return null;
    }


    @Override
    public Node_Data center() {

        return null;
    }


    @Override
    public List<Node_Data> tsp(List<Node_Data> cities) {
        Node_Data first = cities.get(0);
        ArrayList<Node_Data> shorter = new ArrayList<>();
        while (cities.size() > 0) {
            double min = Double.MAX_VALUE;
            for (int i = 1; i < cities.size() - 1; i++) {

                double temp = shortestPathDist(first.getKey(), cities.get(i).getKey());
                if (temp < min) {
                    min = temp;
                    shorter.addAll(shortestPath(first.getKey(), cities.get(i).getKey()));
                }
            }
            return cities;
        }
    }

        @Override
        public boolean save(String file){
            try {
                GsonBuilder gson = new GsonBuilder();
                gson.registerTypeAdapter(DirectedWeightedGraph.class, new DirectedWeightedGraph.DWGraph_DSJson());
                Gson g = gson.create();
                PrintWriter gFile = new PrintWriter(new File(file));
                gFile.write(g.toJson(G));
                gFile.close();
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("can't write the graph to a file ");
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean load (String file){
            try {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(DirectedWeightedGraph.class, new DirectedWeightedGraph.DWGraph_DSJson());
                Gson gson = builder.create();
                BufferedReader gFile = new BufferedReader(new FileReader(file));
                Directed_Weighted_Graph g = gson.fromJson(gFile, DirectedWeightedGraph.class);
                init(g);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("can't read the graph from the file");
                e.printStackTrace();
            }
            return false;
        }


        private boolean BFS ( int src){
            if (G.nodeSize() == 0 || G.nodeSize() == 1) {
                return true;
            }
            if (this.G.nodeSize() > this.G.edgeSize() + 1) {
                return false;
            }
            for (Iterator<Node_Data> it = G.nodeIter(); it.hasNext(); ) {
                Node_Data nodes = it.next();
                nodes.setTag(-1);
            }
            int counter = 0;
            Queue<Node_Data> q = new LinkedList<>();
            Node_Data v = G.getNode(src);
            v.setTag(0);

            q.add(v);
            while (!q.isEmpty()) {
                Node_Data current = q.poll();
                counter++;
                for (Iterator<Edge_Data> it = G.edgeIter(current.getKey()); it.hasNext(); ) {
                    Edge_Data e = it.next();
                    Node_Data node = G.getNode(e.getDest());
                    if (node.getTag() == -1) {
                        q.add(node);
                        node.setTag(0);
                    }
                }
            }
            if (counter == G.nodeSize()) {
                return true;
            }
            return false;
        }


        private void setWeights ( double weights){
            for (node_data v : algo.getV()) {
                v.setWeight(weights);
                v.setTag(-1);
            }
        }
    }
}
