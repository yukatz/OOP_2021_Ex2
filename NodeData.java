import Interfaces.Geo_Location;
import Interfaces.Node_Data;

public class NodeData implements Node_Data, Comparable<Node_Data> {
    private int key;
    private int tag;
    private double weight;
    private String info;
    private Geo_Location location;
    private Node_Data previous;
    public final static int WHITE = 1;
    public final static int GRAY = 2;
    public final static int BLACK = 3;

    public NodeData(double x , double y , double z, int key){
        this.tag=0;
        this.info = "";
        this.key = key;
        this.weight = 0.0;
        location = new GeoLocation(x,y,z);
    }

    public NodeData(int key, Geo_Location g){
        this.key= key;
        this.location= g;
        this.tag = 0;
        this.weight = 0;
        this.info = "WHITE";
        this.previous=null;
    }


    public NodeData(Node_Data n){
        this.info = n.getInfo();
        this.key = n.getKey();
        this.weight = n.getWeight();
        this.location = n.getLocation();
    }




    @Override
    public int getKey() {

        return this.key;
    }

    @Override
    public Geo_Location getLocation() {

        return this.location;
    }

    @Override
    public void setLocation(Geo_Location p) {
    this.location =  p;

    }
    public Node_Data getPrev(){
        return this.previous;
    }

    public void setPrev(Node_Data n){
        this.previous = n;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }
@Override
public int compareTo(Node_Data other) {
        return Double.compare(this.getWeight(), other.getWeight());
    }


    public int compareTo(NodeData other) {
        return Double.compare(this.getWeight(), other.getWeight());
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
       this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
       this.tag = t;
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "key=" + key +



                ", location=" + location +

                '}';
    }
}
