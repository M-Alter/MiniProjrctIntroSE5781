package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A list of points that the ray meets the geometric bodies
 * include a class of the geometry point of the intersectable
 */
public abstract class Intersectable {

    protected Point3D minBoundary = new Point3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY);
    protected Point3D maxBoundary = new Point3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY);

    /**
     * A class of a geometry point of the intersectable
     */
    public static class GeoPoint{
        /**
         * a field of the geometry
         */
        public Geometry geometry;
        /**
         * the point of the geometry
         */
        public Point3D point3D;

        /**
         * Constructor of a class GeoPoint
         * @param geometry the body of the intersection
         * @param point3D the point of the intersection
         */
        public GeoPoint(Geometry geometry, Point3D point3D) {
            this.geometry = geometry;
            this.point3D = point3D;
        }

        /**
         * override method of equal
         * @param o the object to equal
         * @return true if the objects fields are the same
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            // equal between the two fields
            return this.geometry.equals(geoPoint.geometry)
                    && this.point3D.equals(geoPoint.point3D);
        }


    }

    /**
     * A method to find the the intersections
     * @param ray the ray that engage the geometry body
     * @return a list of the intersections
     */
    public List<Point3D> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return geoList == null? null
                : geoList.stream().map(gp->gp.point3D).collect(Collectors.toList());
    }

    /**
     * A method to find the the intersections
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Function Return all the intersection Points of the Ray in the Geometry in
     * specific distance ( that not bigger than max)
     *
     * @param ray - The ray that crosses the body
     * @param max - maximum distance of intersection
     * @return list of the intersection points
     */
    public abstract List<GeoPoint> findGeoIntersections(Ray ray, double max);


    /**
     * get the maximum Boundary
     *
     * @return the maximum Boundary
     */
    public Point3D getMaxBoundary() {
        return maxBoundary;
    }

    /**
     * get the minimum Boundary
     *
     * @return the minimum Boundary
     */
    public Point3D getMinBoundary() {
        return minBoundary;
    }

    /**
     * set the maximum Boundary for geometry
     */
    public abstract void setMaxBoundary();

    /**
     * set the minimum Boundary for geometry
     */
    public abstract void setMinBoundary();

}
