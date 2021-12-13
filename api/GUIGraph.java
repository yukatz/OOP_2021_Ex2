import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUIGraph extends JFrame implements ActionListener {
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
    private ActionEvent e;
    private DirectedWeightedGraph G;
    private DirectedWeightedGraphAlgorithms graph_algo;

    public GUIGraph() {
        super();
        /////////////Frame/////////////
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        this.setSize(screenSize.width / 2, screenSize.height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Yulia&Avidan Graph");
        ImageIcon gr = new ImageIcon("./api/Research/graphicon.png");
        this.setIconImage(gr.getImage());

        this.getContentPane().setBackground(new Color(148, 179, 210));
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


        this.setVisible(true);


    }

    public static void main(String[] args) {
        new GUIGraph();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            JFrame fr = new JFrame();
            fr.setLayout(null);
            fr.setSize(300, 150);
            fr.setLocation(100, 100);
                       JLabel l = new JLabel();
            l.setBounds(50, 100, 250, 20);
            JButton b = new JButton("Save");
            b.setBounds(50, 150, 95, 30);
            b.addActionListener(this);
            l.setText("Are you sure you want to save this changes?");
            fr.add(b);
            fr.add(l);
            this.repaint();
            fr.setVisible(true);
            //////לעשות תנאי

                JFileChooser fc = new JFileChooser();
                try {
                    fc.setCurrentDirectory(new File("./data"));
                } catch (Exception E) {
                }
                fc.setDialogTitle("Loading File");
                int selection = fc.showOpenDialog(null);
                if (selection == JFileChooser.FILES_ONLY) {
                    String filepath = fc.getSelectedFile().getPath();
                    this.graph_algo = new DirectedWeightedGraphAlgorithms();
                    this.graph_algo.load(filepath);

                }


        }


           if (e.getSource() == this.load) {
                JFileChooser fc = new JFileChooser();
                try {
                    fc.setCurrentDirectory(new File("./data"));
                } catch (Exception E) {
                }
                fc.setDialogTitle("Loading File");
                int selection = fc.showOpenDialog(null);
                if (selection == JFileChooser.APPROVE_OPTION) {
                    String filepath = fc.getSelectedFile().getPath();
                    this.graph_algo = new DirectedWeightedGraphAlgorithms();
                    this.graph_algo.load(filepath);
                    ////accept message////
                    JFrame fr = new JFrame();
                    fr.setLayout(null);
                    fr.setSize(300, 150);
                    fr.setLocation(100, 100);
                    JLabel l = new JLabel();
                    l.setBounds(80, 20, 250, 20);
                    JButton b = new JButton("OK");
                    b.setBounds(80, 40, 95, 30);
                    b.addActionListener(this);
                    l.setText("Graph was loaded");
                    fr.add(b); fr.add(l);
                    this.repaint();
                    fr.setVisible(true);
                }
            }






        if (e.getSource() == this.addNode) {
            JFrame fr = new JFrame();
            fr.setLayout(null);
            fr.setSize(300, 150);
            fr.setLocation(100, 100);
            fr.setTitle("Node Adding");
            ImageIcon gr = new ImageIcon("./api/Research/graphicon.png");
            fr.setIconImage(gr.getImage());
            //////Message//////
            JLabel l = new JLabel();
            l.setBounds(80, 20, 250, 20);//messege size
            l.setText("Enter Node data");//messege text//לוודא מול הבנאים

            //////Text insert line//////
            JTextField x = new JTextField();
            x.setBounds(65, 40, 80, 20);//text line size
            //////Button//////
            JButton b = new JButton("Add Node");// text
            b.setBounds(100, 60, 90, 20);//button size

            fr.add(b);fr.add(x);fr.add(l);//add all to window
            b.addActionListener(this);//listener to the button
            this.repaint();
            fr.setVisible(true);
        }

        if (e.getSource() == this.addEdge) {
            JFrame fr = new JFrame();
            fr.setLayout(null);
            fr.setSize(300, 150);
            fr.setLocation(100, 100);
            fr.setTitle("Edge Adding");
            ImageIcon gr = new ImageIcon("./api/Research/graphicon.png");
            fr.setIconImage(gr.getImage());
            //////Message//////
            JLabel l = new JLabel();
            l.setBounds(80, 20, 250, 20);//messege size
            l.setText("Enter source and destination of new edge");//messege text

            //////Text insert line//////
            JTextField textline = new JTextField();
            textline.setBounds(65, 40, 160, 20);//text line size

            //////Button//////
            JButton b = new JButton("Add Edge");// text
            b.setBounds(100, 60, 90, 20);//button size

            fr.add(b);
            fr.add(textline);
            fr.add(l);//add all to window

            b.addActionListener(this);//listener to the button

            this.repaint();
            fr.setVisible(true);
        }

        if (e.getSource() == this.removeEdge) {
            JFrame fr = new JFrame();
            fr.setLayout(null);
            fr.setSize(300, 150);
            fr.setLocation(100, 100);
            fr.setTitle("Edge removing");
            ImageIcon gr = new ImageIcon("./api/Research/graphicon.png");
            fr.setIconImage(gr.getImage());
            //////Message//////
            JLabel l = new JLabel();
            l.setBounds(80, 20, 250, 20);//messege size
            l.setText("Witch edge to remove?");//messege text

            //////Text insert line//////
            JTextField textline = new JTextField();
            textline.setBounds(65, 40, 160, 20);//text line size

            //////Button//////
            JButton b = new JButton("Remove");// text
            b.setBounds(100, 60, 90, 20);//button size

            fr.add(b);
            fr.add(textline);
            fr.add(l);//add all to window

            b.addActionListener(this);//listener to the button

            this.repaint();
            fr.setVisible(true);
        }

        if (e.getSource() == this.removeNode) {
            JFrame fr = new JFrame();
            fr.setLayout(null);
            fr.setSize(300, 150);
            fr.setLocation(100, 100);
            fr.setTitle("Node Removing");
            ImageIcon gr = new ImageIcon("./api/Research/graphicon.png");
            fr.setIconImage(gr.getImage());
            //////Message//////
            JLabel l = new JLabel();
            l.setBounds(80, 20, 250, 20);//messege size
            l.setText("Which node to remove?");//messege text

            //////Text insert line//////
            JTextField textline = new JTextField();
            textline.setBounds(65, 40, 160, 20);//text line size

            //////Button//////
            JButton b = new JButton("Remove");// text
            b.setBounds(100, 60, 90, 20);//button size

            fr.add(b);
            fr.add(textline);
            fr.add(l);//add all to window

            b.addActionListener(this);//listener to the button

            this.repaint();
            fr.setVisible(true);
        }

        if (e.getSource() == this.center) {//להתאים


        }
        if (e.getSource() == this.shortestPathDist) {//להתאים לפונקציה

        }
        if (e.getSource() == this.isConected) {
            JFrame fr = new JFrame();
            fr.setLayout(null);
            fr.setSize(300, 150);
            fr.setLocation(100, 100);
            ImageIcon gr = new ImageIcon("./api/Research/vi.jpg");
            fr.setIconImage(gr.getImage());
            JLabel l = new JLabel();
            l.setBounds(80, 20, 250, 20);//messege size
            if(this.graph_algo.isConnected()){
                l.setText("This Graph is Connected");//messege text

            }
            else {
                l.setText("This Graph isn't Connected");//messege text

            }
            fr.add(l);
            this.repaint();
            fr.setVisible(true);


        }
    }




}



