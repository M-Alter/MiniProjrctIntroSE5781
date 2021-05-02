package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import javax.imageio.ImageTranscoder;
import java.util.LinkedList;
import java.util.List;

public class Sphere implements Geometry{
    Point3D _center;
    double _radius;

    /**
     * Constructor of sphere
     * @param center is a center point of a sphere
     * @param radius radius distance between the center to the central point
     */
   public Sphere(Point3D center, double radius) {
        this._center = center;
        this._radius = radius;
    }

    /**
     * Normal of a sphere to a point
     * @param pnt
     * @return Normal of a sphere to a point
     */
    @Override
    public Vector getNormal(Point3D pnt) {
       if(pnt.equals(_center))
           throw new IllegalArgumentException("The points for normal has to be different");
       return pnt.subtract(_center).normalized();
    }

    /**
     * Find the intersections between a ray to a sphere
     * @param ray
     * @return A list of the intersections
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;
        // Parameters for calculation
        Vector u = this._center.subtract(ray.getP0());
        double tM = ray.getDir().dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tM*tM);
        // Return null in case there are no intersections
        if(d>_radius){
            return null;
        }

        // Parameters for calculations
        double tH = Math.sqrt(_radius*_radius - d*d);
        double t1 = tM + tH;
        double t2 = tM - tH;
        // Add the intersections points to the list
        if (t1 > 0 ) {
            // Init the list where there are intersections
            result = new LinkedList<Point3D>();
            result.add(ray.getPoint(t1));
        }
        if (t2 > 0 ){
            if(result == null){
                // Init the list where there are intersections
                result = new LinkedList<Point3D>();
            }
            result.add(ray.getPoint(t2));
        }
        return result;
    }
}
