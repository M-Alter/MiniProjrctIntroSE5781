package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere implements Geometry{
    Point3D center;
    double radius;

    /**
     * Constructor of sphere
     * @param center is a center point of a sphere
     * @param radius radius distance between the center to the central point
     */
   public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Normal of a sphere to a point
     * @param pnt
     * @return Normal of a sphere to a point
     */
    @Override
    public Vector getNormal(Point3D pnt) {
       if(pnt.equals(center))
           throw new IllegalArgumentException("The points for normal has to be different");
       return pnt.subtract(center).normalized();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
