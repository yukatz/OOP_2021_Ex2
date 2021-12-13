import Interfaces.Geo_Location;
import Interfaces.Node_Data;

public class NodeData implements Node_Data {
    private int key;
    private int tag;
    private double weight;
    private String info;
    private Geo_Location location;

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
        this.info = "";
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

    @Override
    public double getWeight() {
        return this.weight;
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
}