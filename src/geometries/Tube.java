package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class that represents a infinite tue
 */
public class Tube extends Geometry{
    Ray axisRay;
    double radius;

    /**
     * Constructor of a tube
     * @param axisRay The direction of the tube
     * @param radius The width of the tube
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * The normal of the tube to the point
     * @param pnt
     * @return The normal of the tube to the point
     */
    @Override
    public Vector getNormal(Point3D pnt) {
        if(pnt.equals(axisRay.getP0()))
            throw new IllegalArgumentException("this point would create a zero vector");
        double t = axisRay.getDir().dotProduct(pnt.subtract(axisRay.getP0()));
        Point3D o = axisRay.getP0().add(axisRay.getDir().scale(t));
        return pnt.subtract(o).normalized();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    /**
     * A method to find the the intersections
     *
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
