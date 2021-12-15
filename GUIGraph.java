import Interfaces.Directed_Weighted_Graph;
import Interfaces.Edge_Data;
import Interfaces.Node_Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.Iterator;

public class GUIGraph extends JFrame implements ActionListener, MouseListener {
    JMenuBar menu;
    JMenu file;
    JMenu edit;
    JMenu algo;
    JMenuItem save;
    JMenuItem load;
    JMenuItem addNode;
    JMenuItem addEdge;
    JMenuItem removeNode;
    JMenuItem removeEdge;
    JMenuItem shortestPathDist;
    JMenuItem center;
    JMenuItem isConected;
    String message;

    private Panel panel;
    private ActionEvent e;
    private Directed_Weighted_Graph G;
    private DirectedWeightedGraphAlgorithms graph_algo;

    public GUIGraph(DirectedWeightedGraphAlgorithms g) {
        super();
        /////////////Frame/////////////
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        this.setSize(screenSize.width / 2, screenSize.height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Yulia&Avidan Graph");
        ImageIcon gr = new ImageIcon("Data/graphicon.png");
        this.setIconImage(gr.getImage());

        this.getContentPane().setBackground(new Color(148, 168, 210));
        /////////////Menu Tools/////////////
        menu = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        algo = new JMenu("Algorithms");
        save = new JMenuItem("Save Graph");
        load = new JMenuItem("Load Graph");
        addNode = new JMenuItem("Add New Node");
        addEdge = new JMenuItem("Add New Edge");
        removeNode = new JMenuItem("Remove Node");
        removeEdge = new JMenuItem("Remove Edge");
        shortestPathDist = new JMenuItem("Shortest Path");
        center = new JMenuItem("Find Center");
        isConected = new JMenuItem("Is Conected?");
        file.add(save);
        file.add(load);
        edit.add(addNode);
        edit.add(addEdge);
        edit.add(removeNode);
        edit.add(removeEdge);
        algo.add(shortestPathDist);
        algo.add(center);
        algo.add(isConected);
        menu.add(file);
        menu.add(edit);
        menu.add(algo);
        this.setJMenuBar(menu);

        load.addActionListener(this);
        save.addActionListener(this);
        addNode.addActionListener(this);
        addEdge.addActionListener(this);
        removeNode.addActionListener(this);
        removeEdge.addActionListener(this);
        shortestPathDist.addActionListener(this);
        center.addActionListener(this);
        isConected.addActionListener(this);


        this.graph_algo = g;
        this.G = g.getGraph();
        this.setResizable(true);
        this.setVisible(true);
        this.add(this.panel);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            JFileChooser jf = new JFileChooser();
            jf.setCurrentDirectory(new File("."));
            int ans = jf.showSaveDialog(null); // in order to choose where to save the Json file
            if(ans == JFileChooser.APPROVE_OPTION){
                String getPath = jf.getSelectedFile().getAbsolutePath();
                this.graph_algo.save(getPath);
            }
        }



        if (e.getSource() == this.load) {

            DirectedWeightedGraphAlgorithms neGraph = new DirectedWeightedGraphAlgorithms(this.G);
            Container count = getContentPane();
            try {
                JFileChooser load_file = new JFileChooser();
                load_file.setCurrentDirectory(new File("Data"));
                int choose = load_file.showOpenDialog(this);
                if (choose == JFileChooser.APPROVE_OPTION) {
                    count.removeAll();
                    File file = load_file.getSelectedFile();
                    neGraph.load(String.valueOf(file));
                    Panel newPanel = new Panel((DirectedWeightedGraph) neGraph.getGraph());
                    this.panel = newPanel;
                    count.add(newPanel);
                    count.validate();
                    count.repaint();
                    this.G = neGraph.getGraph();
                    this.setVisible(true);
                }

            } catch (Exception exception) {
                System.out.println(exception.getMessage());

            }
        }



        if (e.getSource() == this.addNode) {

            JFrame fra = new JFrame();
            String x = JOptionPane.showInputDialog(fra,"input x ");
            String y = JOptionPane.showInputDialog(fra,"input y");
            String z = JOptionPane.showInputDialog(fra,"input z");
            String wei = JOptionPane.showInputDialog(fra,"input weight(key)");
            int xx = Integer.parseInt(x);
            int yy = Integer.parseInt(y);
            int zz = Integer.parseInt(z);
            int weig = Integer.parseInt(wei);
            NodeData a0 = new NodeData(xx,yy,zz,weig);
            G.addNode(a0);
            this.graph_algo.init(this.G);
            this.add(panel);
            this.repaint();
        }

        if (e.getSource() == this.addEdge) {
            JFrame fr = new JFrame();
            String source = JOptionPane.showInputDialog(fr,"input Source Node");
            String dest = JOptionPane.showInputDialog(fr,"input Destination Node");
            String weight = JOptionPane.showInputDialog(fr,"input weight path");
            int src = Integer.parseInt(source);
            int des = Integer.parseInt(dest);
            int wei = Integer.parseInt(weight);
            G.connect(src,des,wei);
            this.graph_algo.init(this.G);
            this.add(panel);
            this.repaint();
        }

        if (e.getSource() == this.removeEdge) {
            JFrame fr = new JFrame();
            String source = JOptionPane.showInputDialog(fr," input Source Node");
            String dest = JOptionPane.showInputDialog(fr," input Destination Node");
            /////removing////
            int src = Integer.parseInt(source);
            int des = Integer.parseInt(dest);
            this.graph_algo.getGraph().removeEdge(src,des);
            this.graph_algo.init(G);
            repaint();
        }

        if (e.getSource() == this.removeNode) {
            JFrame fr = new JFrame();
            String node=JOptionPane.showInputDialog(fr,"Enter the key of Node you want to remove");
            int KeyNode=Integer.parseInt(node);
            this.G.removeNode(KeyNode);
            this.graph_algo.init(G);
            repaint();
        }

        if (e.getSource() == this.center) {//להתאים
            JOptionPane.showMessageDialog(new JFrame(),"the Node center of the Graph is:"+ graph_algo.center().getKey(),"Center In Graph",JOptionPane.DEFAULT_OPTION);

        }
        if (e.getSource() == this.shortestPathDist) {
            JFrame fr = new JFrame();
            String files = JOptionPane.showInputDialog(fr,"input Source");
            String filed = JOptionPane.showInputDialog(fr,"input Destination");
            int src = Integer.parseInt(files);
            int dest = Integer.parseInt(filed);
            JOptionPane.showMessageDialog(fr,"the short path from source to destination is:"+this.graph_algo.shortestPathDist(src,dest));
        }
        if (e.getSource() == this.isConected) {
            JFrame fr = new JFrame();
            fr.setLayout(null);
            fr.setSize(300, 150);
            fr.setLocation(100, 100);
            ImageIcon gr = new ImageIcon("Data/vi.jpg");
            fr.setIconImage(gr.getImage());
            JLabel l = new JLabel();
            l.setBounds(80, 20, 250, 20);//messege size
            DirectedWeightedGraphAlgorithms neGraph = new DirectedWeightedGraphAlgorithms(this.G);

            if(this.graph_algo.isConnected()){
                JOptionPane.showMessageDialog(fr,"This Graph is Connected");//message text
            }
            else {
                JOptionPane.showMessageDialog(fr,"This Graph isn't Connected");//messege text

            }
            fr.add(l);
            this.repaint();
            fr.setVisible(false);


        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public static class Panel extends JPanel {
        DirectedWeightedGraph graph;
        private double minX;
        private double minY;
        private final int ARR_SIZE = 7;
        private double maxX;
        private double maxY;
        private double scaleX;
        private double scaleY;


        public Panel(DirectedWeightedGraph graph) {
            this.setBackground(Color.black);
            this.setFocusable(true);
            this.graph = graph;
            MaxMinForXY();
        }

        private void MaxMinForXY() {
            Iterator<Node_Data> n = graph.nodeIter();
            Node_Data node = n.next();
            minX = node.getLocation().x();
            minY = node.getLocation().y();

            maxX = node.getLocation().x();
            maxY = node.getLocation().y();
            while (n.hasNext()) {
                node = n.next();
                minX = Math.min(minX, node.getLocation().x());
                minY = Math.min(minY, node.getLocation().y());

                maxX = Math.max(maxX, node.getLocation().x());
                maxY = Math.max(maxY, node.getLocation().y());
            }

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.scaleX = this.getWidth() / Math.abs(maxX - minX)*0.89999 ;
            this.scaleY = this.getHeight() / Math.abs(maxY - minY)*0.855;
            drawGraph(g);
        }

        private void drawGraph(Graphics g) {
            Iterator<Node_Data> NodesIter=this.graph.nodeIter();
            while(NodesIter.hasNext()){
                Node_Data n=NodesIter.next();
                drawNode(g,n);
                Iterator<Edge_Data> edgesIter=this.graph.edgeIter(n.getKey());
                while(edgesIter.hasNext()){
                    Edge_Data e = edgesIter.next();
                    drawEdge(g,e);
                }
            }
        }

        public void drawNode(Graphics g,Node_Data node) {
            int x = (int) ((node.getLocation().x() - this.minX) * this.scaleX);
            int y = (int) ((node.getLocation().y() - this.minY) * this.scaleY);
            g.setColor(Color.RED);
            g.fillOval(x, y, 24, 24);
            g.setColor(Color.WHITE);
            g.setFont(new Font("OOP", Font.BOLD, 15));
            g.drawString(String.valueOf(node.getKey()), x+8 , y+14 );

        }


        public void drawEdge(Graphics g,Edge_Data edge) {
            double x1 = graph.getNode(edge.getSrc()).getLocation().x();
            x1 = ((x1 - minX) * this.scaleX) + 16.5;
            double x2 = graph.getNode(edge.getSrc()).getLocation().y();
            x2 = ((x2 - minY) * this.scaleY) + 16.5;

            double y1 = this.graph.getNode(edge.getDest()).getLocation().x();
            y1 = ((y1 - this.minX) * this.scaleX) + 16.5;
            double y2 = this.graph.getNode(edge.getDest()).getLocation().y();
            y2 = ((y2 - this.minY) * this.scaleY) + 16.5;

            g.setColor(Color.BLUE);
            drawArrow(g,(int) x1, (int) x2, (int) y1, (int) y2);
            String weightString =String.valueOf(edge.getWeight()) ;
            weightString = weightString.substring(0,weightString.indexOf(".")+2);

            g.setColor(Color.green);
            g.setFont(new Font("OOP", Font.BOLD, 15));
            g.drawString(weightString, (int)(x1*0.25 + y1*0.75),(int)(x2*0.25 + y2*0.75));

        }
        void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
            Graphics2D g = (Graphics2D) g1.create();
            double dx = x2 - x1, dy = y2 - y1;
            double angle = Math.atan2(dy, dx);
            int len = (int) Math.sqrt(dx*dx + dy*dy);
            AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
            at.concatenate(AffineTransform.getRotateInstance(angle));
            g.transform(at);
            g.drawLine(0, 0, len, 0);
            g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                    new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }


    }


}