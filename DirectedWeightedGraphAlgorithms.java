import Interfaces.Directed_Weighted_Graph;
import Interfaces.Directed_Weighted_Graph_Algorithms;
import Interfaces.Edge_Data;
import Interfaces.Node_Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgorithms implements Directed_Weighted_Graph_Algorithms {
    private Directed_Weighted_Graph G;

    public DirectedWeightedGraphAlgorithms() {

    }


    public DirectedWeightedGraphAlgorithms(Directed_Weighted_Graph g) {
        init(g);
    }


    @Override
    public void init(Directed_Weighted_Graph g) {
        this.G =  g;
    }

    @Override
    public Directed_Weighted_Graph getGraph() {

        return this.G;
    }

    @Override
    public Directed_Weighted_Graph copy() {

        Directed_Weighted_Graph copy = new DirectedWeightedGraph();
        Iterator<Node_Data> NodePoint1 = this.G.nodeIter();
        while (NodePoint1.hasNext()) {
            Node_Data cur_node = NodePoint1.next();
            NodeData temp = new NodeData(cur_node.getKey(), cur_node.getLocation());
            copy.addNode(temp);
        }
        Iterator<Node_Data> NodePoint2 = this.G.nodeIter();
        while (NodePoint2.hasNext()) {
            Iterator<Edge_Data> EdgePoint2 = this.G.edgeIter(NodePoint2.next().getKey());
                while (EdgePoint2.hasNext()) {
                    Edge_Data nextEdge = EdgePoint2.next();
                    copy.connect(nextEdge.getSrc(),nextEdge.getDest(),nextEdge.getWeight());
                }
            }

        return copy;
    }

    @Override
    public boolean isConnected() {
        Iterator <Node_Data> nodesIterator = this.G.nodeIter();
        while(nodesIterator.hasNext()){
            int curr = nodesIterator.next().getKey();
            if (!BFS(curr))
                return false;
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        List<Node_Data> path = shortestPath(src, dest);
        if (path == null)
            return -1;
        return pathCost(path);
    }

    @Override
    public List<Node_Data> shortestPath(int src, int dest) {
        HashMap<Integer, Integer> pointers = dijkstra(src);
        List<Node_Data> path = getPath(pointers, src, dest);
        return path;
    }
    private List<Node_Data> getPath(HashMap<Integer, Integer> pointers, int src, int dest) {
            List<Node_Data> path = new ArrayList<>();
            int previous = dest;
            while (previous != src) {
                path.add(0, this.G.getNode(previous));
                previous = pointers.get(previous);
            }
            path.add(0, this.G.getNode(src));
            return path;
    }
    private double pathCost(List<Node_Data> path) {
        double sum = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            sum += G.getEdge(path.get(i).getKey(), path.get(i + 1).getKey()).getWeight();
        }
        return sum;
    }


    @Override
    public Node_Data center() {
        if (!this.isConnected()){return null;}//if this graph isnt connected, there is no center
        Node_Data ans = null;
        double min = Double.MAX_VALUE;//minimum for the begining
        Iterator<Node_Data> itr_node1 = G.nodeIter();
        while (itr_node1.hasNext()) {//while its not the end
            Iterator<Node_Data> itr_node2 = G.nodeIter();//
            Node_Data node1 = itr_node1.next();
            double temp = 0;
            while (itr_node2.hasNext()) {
                Node_Data node2 = itr_node2.next();
                if (temp > min) {
                    break;
                }
                if (temp < this.shortestPathDist(node1.getKey(), node2.getKey()))
                    temp = this.shortestPathDist(node1.getKey(), node2.getKey());
            }
            if (min > temp) {
                min = temp;
                ans = node1;
            }

        }
        return ans;
    }


    @Override
    public List<Node_Data> tsp(List<Node_Data> cities) {
        if(cities.size()==0){return null;}

        Node_Data first = cities.get(0);//first node in given list
        List<Node_Data> AllPathes = new ArrayList<>();//new list for pathes
        List<Node_Data> TempPath;//temporary list for each start node path
        int minIndex=0;//saves the index(node) for minimum node path
        cities.remove(0);//we saved the first node and remove him for easy searching

        ///////each node to anothers////////
        while (cities.size() > 0) {//every iteration "kwech" the cities size untill 0.
            double min = Double.MAX_VALUE;

            //////from one node to every else//////
            for (int i = 0; i < cities.size(); i++) {
                double temp = shortestPathDist(first.getKey(), cities.get(i).getKey());
                //checks the shortest path bet. first node to others
                if (temp < min) {//if this path shorter then prev.-->
                    min = temp;//-->switch.
                    minIndex = i;//save the minimum path node index
                }
            }
            //save the minimum path from this iteration
            TempPath=shortestPath(first.getKey(), cities.get(minIndex).getKey());
            AllPathes.addAll(TempPath);//refresh the answer
            AllPathes.remove(AllPathes.size()-1);
            first = cities.get(minIndex);//now we will start from this best (till now) node
            cities.remove(cities.get(minIndex));
        }
        AllPathes.add(first);//adds the node that was removed
        return AllPathes;
    }

    @Override
    public boolean save(String file) {
        Directed_Weighted_Graph SaveG = new DirectedWeightedGraph();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsons = gson.toJson(SaveG);
        try{
            FileWriter fw = new FileWriter("TestGson.json");
            fw.write(gson.toJson(SaveG));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String file) {
        boolean result = false;
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(DirectedWeightedGraph.class, new Deserialize());
            Gson gson = builder.create();
            FileReader reader = new FileReader(file);
            this.G = gson.fromJson(reader, DirectedWeightedGraph.class);
            result = true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
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
    private HashMap<Integer, Integer> dijkstra(int src){
        PriorityQueue<Node_Data> pQueue = new PriorityQueue<>();
        HashMap<Integer, Integer> previous_pointer = new HashMap<>();
        for (Iterator<Node_Data> it = this.G.nodeIter(); it.hasNext(); ) {
            Node_Data node = it.next();
            if (node.getKey() == src){
                node.setWeight(0.0);
                previous_pointer.put(node.getKey(), null);
            }
            else
            {
                node.setWeight(Double.MAX_VALUE);
                previous_pointer.put(node.getKey(), -1);
            }
            pQueue.add(node);
        }

        // The main loop
        while (!pQueue.isEmpty()){
            Node_Data curr_node = pQueue.remove(); // Remove and return best vertex
            for (Iterator<Edge_Data> it = this.G.edgeIter(curr_node.getKey()); it.hasNext(); )  // only v that are still in Q
            {
                Edge_Data e_neighbour =  it.next();
                Node_Data node_neighbour = this.G.getNode(e_neighbour.getDest());

                double alternative_weight = curr_node.getWeight() + e_neighbour.getWeight();
                if(alternative_weight < node_neighbour.getWeight()){
                    node_neighbour.setWeight(alternative_weight);
                    previous_pointer.replace(node_neighbour.getKey(), curr_node.getKey());
                    pQueue.remove(node_neighbour);
                    pQueue.add(node_neighbour);
                }
            }
        }

        return previous_pointer;
    }

    }

