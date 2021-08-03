package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import static primitives.Util.*;

import javax.imageio.ImageTranscoder;
import java.util.LinkedList;
import java.util.List;

/**
 * class that represents a sphere
 */
public class Sphere extends Geometry{
    /**
     * the center of the sphere
     */
    Point3D _center;
    /**
     * the radius of the sphere
     */
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
     * @param pnt the point to get the normal to
     * @return Normal of a sphere to a point
     */
    @Override
    public Vector getNormal(Point3D pnt) {
       if(pnt.equals(_center))
           throw new IllegalArgumentException("The points for normal has to be different");
       return pnt.subtract(_center).normalized();
    }

//    /**
//     * Find the intersections between a ray to a sphere
//     * @param ray ray the runs through the geometry
//     * @return A list of the intersections
//     */
//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        List<Point3D> result = null;
//        // Parameters for calculation
//        Vector u;
//        //if ray.p0 == _centre prevent zero vector
//        try{
//            u = this._center.subtract(ray.getP0());
//        }catch (IllegalArgumentException e){
//            return List.of(ray.getPoint(this._radius));
//        }
//        double tM = ray.getDir().dotProduct(u);
//        double d = Math.sqrt(u.lengthSquared() - tM*tM);
//        // Return null in case there are no intersections
//        if(d>_radius){
//            return null;
//        }
//
//        // Parameters for calculations
//        double tH = Math.sqrt(_radius*_radius - d*d);
//        double t2 = tM + tH;
//        double t1 = tM - tH;
//        // Add the intersections points to the list
//        if (t1 > 0 ) {
//            // Init the list where there are intersections
//            result = new LinkedList<>();
//            result.add(ray.getPoint(t1));
//        }
//        if (t2 > 0 ){
//            if(result == null){
//                // Init the list where there are intersections
//                result = new LinkedList<>();
//            }
//            result.add(ray.getPoint(t2));
//        }
//        return result;
//    }

    /**
     * A method to find the the intersections
     *
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = null;
        // Parameters for calculation
        Vector u;
        //if ray.p0 == _centre prevent zero vector
        try{
            u = this._center.subtract(ray.getP0());
        }catch (IllegalArgumentException e){
            return List.of(new GeoPoint(this, ray.getPoint(this._radius)));
        }
        double tM = ray.getDir().dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tM*tM);
        // Return null in case there are no intersections
        if(d>_radius){
            return null;
        }

        // Parameters for calculations
        double tH = Math.sqrt(_radius*_radius - d*d);
        double t2 = tM + tH;
        double t1 = tM - tH;
        // Add the intersections points to the list
        if (t1 > 0 ) {
            // Init the list where there are intersections
            result = new LinkedList<>();
            result.add(new GeoPoint(this, ray.getPoint(t1)));
        }
        if (t2 > 0 ){
            if(result == null){
                // Init the list where there are intersections
                result = new LinkedList<>();
            }
            result.add(new GeoPoint(this, ray.getPoint(t2)));
        }
        return result;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double max) {
        double tm;
        double d;
        var p0 = ray.getP0();
        try {
            var u = _center.subtract(p0);
            tm = ray.getDir().dotProduct(u);
            d = Math.sqrt(u.lengthSquared() - (tm * tm));
            if (d >= _radius)
                return null;
        } catch (Exception e) {
            d = 0;
            tm = 0;
        }
        double th = Math.sqrt(_radius * _radius - (d * d));
        double t1 = Util.alignZero(tm + th);
        double t2 = Util.alignZero(tm - th);
        double dis1 = Util.alignZero(t1 - max);
        double dis2 = Util.alignZero(t2 - max);
        Point3D p1, p2;
        if (t1 > 0 && dis1 <= 0 || t2 > 0 && dis2 <= 0) {
            List<GeoPoint> myList = new LinkedList<GeoPoint>();
            if (t1 > 0 && dis1 <= 0) {
                p1 = ray.getPoint(t1);
                if (!p1.equals(p0))
                    myList.add(new GeoPoint(this, p1));
            }
            if (t2 > 0 && dis2 <= 0) {
                p2 = ray.getPoint(t2);
                if (!p2.equals(p0))
                    myList.add(new GeoPoint(this, p2));
            }
            return myList;
        }
        return null;
    }

    @Override
    public void setMaxBoundary() {
        double x = _center.getX() + _radius;
        double y = _center.getY() + _radius;
        double z = _center.getZ() + _radius;
        maxBoundary = new Point3D(x, y, z);

    }

    @Override
    public void setMinBoundary() {
        double x = _center.getX() - _radius;
        double y = _center.getY() - _radius;
        double z = _center.getZ() - _radius;
        minBoundary = new Point3D(x, y, z);
    }
}
