package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * class that represents a triangle
 */
public class Triangle extends Polygon{

    // constructor
    public Triangle(Point3D x,Point3D y,Point3D z) {
        super(x,y,z);
    }

//    /**
//     * Find the intersection between a ray to a triangle
//     * @param ray
//     * @return List with the intersection
//     */
//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        List<Point3D> planeIntersections = plane.findIntersections(ray);
//        // Return null if the ray is not in the plane of the triangle
//        if (planeIntersections == null){
//            return null;
//        }
//        List<Point3D> result;
//        // Parameters for calculation
//        Vector v1 = vertices.get(0).subtract(ray.getP0());
//        Vector v2 = vertices.get(1).subtract(ray.getP0());
//        Vector v3 = vertices.get(2).subtract(ray.getP0());
//
//        Vector n1 = v1.crossProduct(v2).normalized();
//        Vector n2 = v2.crossProduct(v3).normalized();
//        Vector n3 = v3.crossProduct(v1).normalized();
//
//        double vN1 = ray.getDir().dotProduct(n1);
//        double vN2 = ray.getDir().dotProduct(n2);
//        double vN3 = ray.getDir().dotProduct(n3);
//        // Return null when some of the number don't have the same prefix
//        if( !(vN1>0 && vN2 > 0 && vN3 > 0) && !(vN1 < 0 && vN2 < 0 && vN3 < 0) ){
//            return null;
//        }
//        //
//        result = planeIntersections;
//        return result;
//    }

    // override function
    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }

    /**
     * A method to find the the intersections
     *
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray);
        // Return null if the ray is not in the plane of the triangle
        if (planeIntersections == null){
            return null;
        }
        List<GeoPoint> result;
        // Parameters for calculation
        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());

        Vector n1 = v1.crossProduct(v2).normalized();
        Vector n2 = v2.crossProduct(v3).normalized();
        Vector n3 = v3.crossProduct(v1).normalized();

        double vN1 = ray.getDir().dotProduct(n1);
        double vN2 = ray.getDir().dotProduct(n2);
        double vN3 = ray.getDir().dotProduct(n3);
        // Return null when some of the number don't have the same prefix
        if( !(vN1>0 && vN2 > 0 && vN3 > 0) && !(vN1 < 0 && vN2 < 0 && vN3 < 0) ){
            return null;
        }

        result = planeIntersections;
        // Implement the geometry field to be this
        for (GeoPoint geoPoint:result) {
            geoPoint.geometry = this;
        }
        return result;
    }
}
