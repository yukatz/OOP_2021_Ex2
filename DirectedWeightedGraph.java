import Interfaces.Directed_Weighted_Graph;
import Interfaces.Edge_Data;
import Interfaces.Node_Data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DirectedWeightedGraph implements Directed_Weighted_Graph {
    private HashMap<Integer, Node_Data> Nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> Edges;
    private int NodeSize;
    private int EdgSize;
    private int MC;

    public DirectedWeightedGraph() {
        this.NodeSize = 0;
        this.EdgSize = 0;
        this.MC = 0;
        this.Nodes = new HashMap<>();
        this.Edges = new HashMap<>();
    }
    public DirectedWeightedGraph(String file) {
        this.NodeSize = 0;
        this.EdgSize = 0;
        this.MC = 0;
        this.Nodes = new HashMap<>();
        this.Edges = new HashMap<>();
    }




    @Override
    public Node_Data getNode(int key) {
            return this.Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return this.Edges.get(src).get(dest);
    }

    @Override
    public void addNode(Node_Data n) {
        if (this.Nodes.containsKey(n.getKey())) {
            return;
        }
        else {
            this.Nodes.put(n.getKey(), n);
            this.Edges.put(n.getKey(), new HashMap<>());
            this.NodeSize++;
            this.MC++;
        }
    }


    @Override
    public void connect(int src, int dest, double w) {
        if (src == dest || !this.Nodes.containsKey(src) || !this.Nodes.containsKey(dest)) {
            return;
        }
        EdgeData E = new EdgeData(src, dest, w);
        this.Edges.get(src).put(dest, E);
        EdgSize++;
        this.MC++;
    }



    @Override
    public Iterator<Node_Data> nodeIter() {
        return new Iterator<>() {
             Iterator<Node_Data> itr_N = Nodes.values().iterator();
            int itr_N_counter = getMC();
            @Override
            public Node_Data next() {
                if (getMC() != itr_N_counter) {
                    throw new RuntimeException("graph was changed");
                }
                if(hasNext()) {
                    return itr_N.next();
                }
                else throw new NullPointerException("does not have next");
            }
            @Override
            public boolean hasNext() {
                if (itr_N_counter != getMC()) {
                    throw new RuntimeException("graph was changed");
                }
                return itr_N.hasNext();
            }
            @Override
            public void remove() {
                if (itr_N_counter != getMC() ) {
                    throw new RuntimeException("graph was changed");
                }
                Node_Data next_node = itr_N.next();
                removeNode(next_node.getKey());
                itr_N_counter=getMC();
            }
        };
    }
    public Iterator<Edge_Data> edgeIter () {
        return new Iterator<>() {
             Iterator<HashMap<Integer, EdgeData>> itr_E = Edges.values().iterator();
            Iterator<EdgeData> this_edge = itr_E.next().values().iterator();
            int itr_E_counter = getMC();
            @Override
            public EdgeData next() {
                if (itr_E_counter != getMC()) {
                    throw new RuntimeException("graph was changed");
                }
                if (!this_edge.hasNext() ) {
                    if (itr_E.hasNext()) {
                        this_edge = itr_E.next().values().iterator();
                        return this_edge.next();
                    }
                }
                return this_edge.next();
            }
            @Override
            public boolean hasNext() {
                if (itr_E_counter != getMC()) {
                    throw new RuntimeException("graph was changed");
                }
                if (!this_edge.hasNext()) {
                    if (itr_E.hasNext()) {
                        this_edge = itr_E.next().values().iterator();
                        return this_edge.hasNext();
                    }
                    return false;
                }
                return true;
            }

            @Override
            public void remove() {
                if (itr_E_counter != getMC()) {
                    throw new RuntimeException("graph was changed");
                }
                EdgeData edge_cur = this_edge.next();
                removeEdge(edge_cur.getSrc(), edge_cur.getDest());
                itr_E_counter=getMC();
            }
        };
    }

    @Override
    public Iterator<Edge_Data> edgeIter(int node_id) {
if (!Nodes.containsKey(node_id)){
    throw new IllegalArgumentException("does not contain the node");
}
return new Iterator<>(){
     Iterator<EdgeData> itr_e = Edges.get(node_id).values().iterator();
    int E_counter = getMC();
    @Override
    public EdgeData next() {
        if( getMC() != E_counter) {
            throw new RuntimeException("graph was changed");
        }
        if (hasNext()) {
                EdgeData this_edge = itr_e.next();
                return this_edge;
            }
        else throw new NoSuchElementException("has not edge");
    }
    @Override
    public boolean hasNext() {
        if (getMC() != E_counter) {
            throw new RuntimeException("graph was changed");
        }
        return itr_e.hasNext();
    }
    @Override
    public void remove() {
        if (E_counter != getMC()) {
            throw new RuntimeException("graph was changed");
        }
        EdgeData this_edge = itr_e.next();
        removeEdge(this_edge.getSrc(), this_edge.getDest());
        E_counter=getMC();
    }
};
    }


    @Override
    public Node_Data removeNode(int key) {
Node_Data cur_Node = Nodes.get(key);
HashMap<Integer,EdgeData> new_edges =this.Edges.get(key);
if(cur_Node==null){
    return null;
}
this.Nodes.remove(key,cur_Node);
this.MC++;
if(new_edges==null){
    return cur_Node;
}
int size = this.Edges.get(key).size();
this.EdgSize-= size;
this.Edges.remove(key);
for(Integer i : new_edges.keySet()){
    this.Edges.get(i).remove(key);
    EdgSize--;
}
this.NodeSize--;
return cur_Node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
       EdgeData cur_edge = this.getEdge(src, dest);
       if(cur_edge != null){
           Edges.get(src).remove(dest , cur_edge);
           EdgSize--;
           MC++;
       }
       return cur_edge;
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
