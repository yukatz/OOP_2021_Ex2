/**
 * This interface represents a geo location <x,y,z>, (aka Point3D data).
 *
 */
public interface Geo_Location {
    public double x();
    public double y();
    public double z();
    public double distance(Geo_Location g);
}