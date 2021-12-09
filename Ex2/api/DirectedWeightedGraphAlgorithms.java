import java.util.Iterator;
import java.util.List;



public class DirectedWeightedGraphAlgorithms implements Directed_Weighted_Graph_Algorithms {
      private Directed_Weighted_Graph G;
      enum Operation{Save, Load}

    public DirectedWeightedGraphAlgorithms(Directed_Weighted_Graph g){
          init(g);
    }


    @Override
    public void init(Directed_Weighted_Graph g) {
       this.G=g;
    }

    @Override
    public Directed_Weighted_Graph getGraph() {

        return this.G;
    }

    @Override
    public Directed_Weighted_Graph copy() {
        Directed_Weighted_Graph NewG = new DirectedWeightedGraph();

        Iterator<Node_Data> vert = this.G.nodeIter();

        if(!vert.)
           for(Node_Data N: vert){
               Node_Data TNode = new NodeData(N.getKey(), N.getTag(),N.getInfo(),N.getWeight(), N.getLocation());

               NewG.addNode(TNode);

        }
        Iterator<Edge_Data> edg = this.G.edgeIter();
        for(Node_Data N: edg){
            Node_Data TEdge = new NodeData(N.getKey(), N.getTag(),N.getInfo(),N.getWeight(), N.getLocation());

            NewG.addNode(TNode);

        }




        Iterator<Edge_Data> edg = this.G.edgeIter();



        return NewG = this.G ;
    }

    @Override
    public boolean isConnected() {

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        return 0;
    }

    @Override
    public List<Node_Data> shortestPath(int src, int dest) {

        return null;
    }

    @Override
    public Node_Data center() {

        return null;
    }

    @Override
    public List<Node_Data> tsp(List<Node_Data> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
