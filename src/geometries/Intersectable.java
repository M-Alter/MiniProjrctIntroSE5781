package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * A list of points that the ray meets the geometric bodies
 * include a class of the geometry point of the intersectable
 */
public interface Intersectable {
    /**
     * A class of a geometry point of the intersectable
     */
    public static class GeoPoint{
        public Geometry geometry;
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
    List<Point3D> findIntersections(Ray ray);

    /**
     * A method to find the the intersections
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    List<GeoPoint> findGeoIntersections(Ray ray);


}
