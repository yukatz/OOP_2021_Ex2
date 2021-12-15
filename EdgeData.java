import Interfaces.Edge_Data;

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
        this.tag=-1;
        this.info="BZZ";

    }
    public EdgeData(int src, int dest, double weight, String info, int tag){
        this.src= src;
        this.dest= dest;
        this.weight= weight;
        this.info= info;
        this.tag= tag;
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
