import Interfaces.Node_Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Graph_AlgorithmsTest {
    DirectedWeightedGraph g=new DirectedWeightedGraph();
    DirectedWeightedGraphAlgorithms g2 = new DirectedWeightedGraphAlgorithms();
    @BeforeEach
    void testBuild(){
        g=new DirectedWeightedGraph();
        for (int i=0;i<5;i++){
            GeoLocation geo= new GeoLocation(i*1,i*2,i*3);
            NodeData n = new NodeData(i,geo);
            g.addNode(n);
        }
        g.connect(0,1,2.0);
        g.connect(0,4,1.0);
        g.connect(1,0,2.0);
        g.connect(1,4,6.0);
        g.connect(1,2,5.0);
        g.connect(2,3,3.0);
        g.connect(3,1,1.0);
        g.connect(4,3,1.0);
        g2.init(g);
    }

    @Test
    void getGraph() {
        assertEquals(g,g2.getGraph());
    }

    @Test
    void isConnected() {
        assertEquals(true,g2.isConnected());
    }

    @Test
    void shortestPathDist() {
        assertEquals(2,g2.shortestPathDist(0,3));
        assertEquals(3,g2.shortestPathDist(1,4));
        assertEquals(7,g2.shortestPathDist(4,2));
        assertEquals(-1,g2.shortestPathDist(15,20));
    }

    @Test
    void shortestPath() {
        List<NodeData> path=new ArrayList<>();
        path.add((NodeData) g.getNode(0));
        path.add((NodeData) g.getNode(4));
        path.add((NodeData) g.getNode(3));
        assertEquals(path,g2.shortestPath(0,3));
        path.removeAll(path);
        path.add((NodeData) g.getNode(0));
        path.add((NodeData) g.getNode(1));
        path.add((NodeData) g.getNode(2));
        assertEquals(path,g2.shortestPath(0,2));
        path.removeAll(path);
        assertEquals(null,g2.shortestPath(10,15));
    }

    @Test
    void center() {
        assertEquals(g.getNode(0),g2.center());
        assertNotEquals(g.getNode(3),g2.center());
    }

    @Test
    void tsp() {
      List<Node_Data> cities = new ArrayList<>();
      cities.add((NodeData) g.getNode(3));
      cities.add((NodeData) g.getNode(0));
      cities.add((NodeData) g.getNode(4));
      cities.add((NodeData) g.getNode(1));
      cities.add((NodeData) g.getNode(2));
      List<NodeData> t=new ArrayList<>();
      t.add((NodeData) g.getNode(4));
      t.add((NodeData) g.getNode(1));
      t.add((NodeData) g.getNode(3));
      t.add((NodeData) g.getNode(1));
      t.add((NodeData) g.getNode(2));
      assertEquals(t,g2.tsp(cities));
    }


}