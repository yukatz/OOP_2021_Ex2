import Interfaces.Edge_Data;
import Interfaces.Node_Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DWGATest {

    DirectedWeightedGraph gr = GraphBuilder();
    DirectedWeightedGraphAlgorithms graph = new DirectedWeightedGraphAlgorithms(gr);
    //////checks time for each function (1000/10000/100000 Nodes)//////
    DirectedWeightedGraph grTimeTest = GraphBuilderJson();
    DirectedWeightedGraphAlgorithms grAlgoTimeTest = new DirectedWeightedGraphAlgorithms(grTimeTest);//checks time for each function (1000/10000/100000 Nodes)


    public DirectedWeightedGraph GraphBuilderJson(){
        DirectedWeightedGraph ourGraph = new DirectedWeightedGraph();
        DirectedWeightedGraphAlgorithms  ourGraph1 = new DirectedWeightedGraphAlgorithms();
        ourGraph1.load("Data\\100000.json");
        ourGraph = (DirectedWeightedGraph) ourGraph1.getGraph();
        return ourGraph;
    }

    public DirectedWeightedGraph GraphBuilder() {
        DirectedWeightedGraph ourGraph = new DirectedWeightedGraph();
        NodeData a0 = new NodeData(0, new GeoLocation(0,0,0));
        NodeData a1 = new NodeData(1, new GeoLocation(1,6,0));
        NodeData a2 = new NodeData(2, new GeoLocation(4,5,0));
        NodeData a3 = new NodeData(3, new GeoLocation(-1,4,0));
        NodeData a4 = new NodeData(4, new GeoLocation(3,0,0));
        NodeData a5 = new NodeData(5, new GeoLocation(2,-2,0));
        NodeData a6 = new NodeData(6, new GeoLocation(-1,-8,0));
        NodeData a7 = new NodeData(7, new GeoLocation(-6,-6,0));
        NodeData a8 = new NodeData(8, new GeoLocation(-7,-1,0));
        NodeData a9 = new NodeData(9, new GeoLocation(-4,-1,0));
        ourGraph.addNode(a0);
        ourGraph.addNode(a1);
        ourGraph.addNode(a2);
        ourGraph.addNode(a3);
        ourGraph.addNode(a4);
        ourGraph.addNode(a6);
        ourGraph.addNode(a5);
        ourGraph.addNode(a7);
        ourGraph.addNode(a8);
        ourGraph.addNode(a9);
        ourGraph.connect(0,1,1.5);
        ourGraph.connect(0,2,20);
        ourGraph.connect(0,3,8.2);
        ourGraph.connect(0,4,5.2);
        ourGraph.connect(0,5,4.9);
        ourGraph.connect(0,6,2.3);
        ourGraph.connect(0,7,1.3);
        ourGraph.connect(0,8,0.4);
        ourGraph.connect(0,9,1.2);
        ourGraph.connect(9,1,4.2);
        ourGraph.connect(1,2,6.3);
        ourGraph.connect(2,3,1.2);
        ourGraph.connect(3,4,0.3);
        ourGraph.connect(4,5,5.3);
        ourGraph.connect(5,6,6.1);
        ourGraph.connect(6,7,4.3);
        ourGraph.connect(7,8,3.9);
        ourGraph.connect(8,9,9.2);
        ourGraph.connect(5, 0, 10);
        return ourGraph;
    }


    @Test
    void init() {
        DirectedWeightedGraphAlgorithms graph1 = new DirectedWeightedGraphAlgorithms();
        graph1.init(gr);
        Iterator<Edge_Data> original = gr.edgeIter();
        Iterator<Edge_Data> initgraph = graph1.getGraph().edgeIter();
        while (original.hasNext()){
            assertEquals(original.next().getWeight(),initgraph.next().getWeight());
        }
    }

    @Test
    void getGraph() {
        Iterator<Edge_Data> original = gr.edgeIter();
        Iterator<Edge_Data> initgraph = graph.getGraph().edgeIter();
        while (original.hasNext()){
            assertEquals(original.next().getWeight(),initgraph.next().getWeight());
        }
    }

    @Test
    void copy() {
        DirectedWeightedGraph copy = (DirectedWeightedGraph) graph.copy();
        assertEquals(copy.edgeSize(),gr.edgeSize());
        assertEquals(copy.nodeSize(), gr.nodeSize());
        Iterator<Node_Data> iterator = copy.nodeIter();
        Iterator<Node_Data> iterator1 = gr.nodeIter();
        while (iterator.hasNext()){
            assertEquals(iterator.next().getLocation(), iterator1.next().getLocation());
        }
        Iterator<Edge_Data> iterator2 = copy.edgeIter();
        Iterator<Edge_Data> iterator3 = gr.edgeIter();
        while (iterator.hasNext()){
            assertEquals(iterator2.next().getSrc(),iterator3.next().getSrc());
        }
    }

    @Test
    void isConnected() {
        assertEquals(true, graph.isConnected());




    }

    @Test
    void shortestPathDist() {
        assertEquals(graph.shortestPathDist(3,4), 0.3, 0.0001);
        assertEquals(graph.shortestPathDist(0,9),1.2, 0.0001);
        assertEquals(graph.shortestPathDist(1,5),13.1, 0.0001);
        graph.getGraph().connect(2,0,0.2);
        graph.getGraph().removeEdge(0,4);
        graph.getGraph().connect(0,4,0.3);
        assertEquals(graph.shortestPathDist(2,4),0.5, 0.0001);
    }

    @Test
    void shortestPath() {
        List<Node_Data> route = graph.shortestPath(2,4);
        Iterator<Node_Data> iterator = route.iterator();
        assertEquals(iterator.next(),graph.getGraph().getNode(2));
        assertEquals(iterator.next(),graph.getGraph().getNode(3));
        assertEquals(iterator.next(),graph.getGraph().getNode(4));

    }
    @Test
    void center() {
        assertEquals(graph.center().getKey(),0);
    }

    @Test
    void tsp() {
        List<Node_Data> check = new ArrayList<>();
        for (int i = 0; i < 9; i=i+2) {
            check.add(this.gr.getNode(i));
        }
        List<Node_Data> ans = new ArrayList<>();
        check.add(this.gr.getNode(0));
        check.add(this.gr.getNode(5));
        check.add(this.gr.getNode(4));
        ans.add(this.gr.getNode(0));
        ans.add(this.gr.getNode(8));
        ans.add(this.gr.getNode(9));
        ans.add(this.gr.getNode(1));
        ans.add(this.gr.getNode(2));
        ans.add(this.gr.getNode(3));
        ans.add(this.gr.getNode(4));
        ans.add(this.gr.getNode(5));
        ans.add(this.gr.getNode(6));



        List<Node_Data> test3 = this.graph.tsp(check);
        assertEquals(test3, ans);
    }

    @Test
    void save() {
        graph.save("Data\\TestGson.json");
        DirectedWeightedGraphAlgorithms graph2 = new DirectedWeightedGraphAlgorithms();
        graph2.load("Data\\TestGson.json");
        assertEquals(graph.getGraph().edgeSize(),graph2.getGraph().edgeSize());
    }

    @Test
    void load() {
        graph.save("Data\\G1.json");
        DirectedWeightedGraphAlgorithms graph2 = new DirectedWeightedGraphAlgorithms();
        graph2.load("Data\\G1.json");
        assertEquals(graph.getGraph().edgeSize(),graph2.getGraph().edgeSize());
    }


}