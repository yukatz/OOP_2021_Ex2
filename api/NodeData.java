public class NodeData implements Node_Data {
    private int key;
    private int tag;
    private double weight;
    private String info;
    private Geo_Location location;

    public NodeData(int key){
        this.tag=0;
        this.info = "";
        this.key = key;
        this.weight = 0.0;
        this.location = new GeoLocation();
    }

    public NodeData(int key, Geo_Location location){
        this.tag=0;
        this.info = "";
        this.key = key;
        this.weight = 0.0;
        this.location = location;
    }

    public NodeData(int key, int tag, String info, double weight, Geo_Location location){
        this.tag=tag;
        this.info = info;
        this.key = key;
        this.weight = weight;
        this.location = location;
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
