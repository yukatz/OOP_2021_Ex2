public class EdgeData implements Edge_Data {
    private int src;
    private int dest;
    private String info;
    private int tag;
    private double weight;

    public EdgeData(int source, int destination, double weight) {
        this.src = source;
        this.dest = destination;
        this.weight = weight;

    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
