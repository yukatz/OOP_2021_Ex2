public class GeoLocation implements Geo_Location {
    private double x;
    private double y;
    private double z;

    public GeoLocation(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public GeoLocation(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {

        return this.z;
    }

    @Override
    public double distance(Geo_Location g) {
        double d = Math.sqrt(Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z - g.z(),2));
        return d;
    }
}
