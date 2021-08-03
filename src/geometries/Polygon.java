package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal(null);

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * function to receive the normal of the polygon
     * @param point the point to calculate the normal
     * @return the normal
     */
    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal();
    }

//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        List<Point3D> planeIntersections = plane.findIntersections(ray);
//        // Return null if the ray is not in the plane of the triangle
//        if (planeIntersections == null) {
//            return null;
//        }
//        List<Point3D> result = null;
//        // Parameters for calculation
//        List<Vector> v = new ArrayList<>();
//        for (int i = 1; i <= vertices.size(); i++) {
//            v.add(i-1, vertices.get(i-1).subtract(ray.getP0()));
//        }
//
//        List<Vector> n = new ArrayList<>();
//        n.add(0, v.get(0).crossProduct(v.get(1)).normalized());
//        n.add(1, v.get(1).crossProduct(v.get(2)).normalized());
//
//        for (int i = 2; i < vertices.size(); i++) {
//            n.add(i, v.get(i).crossProduct(v.get(0)).normalized());
//        }
//
//        ArrayList<Double> vN = new ArrayList<>();
//
//        for (int i = 0; i < vertices.size(); i++) {
//            vN.add(i, ray.getDir().dotProduct(n.get(i)));
//        }
//
//        if (vN.stream().allMatch(x -> x > 0) || vN.stream().allMatch(x -> x < 0)) {
//            result = planeIntersections;
//        }
//        return result;
//    }

    /**
     * A method to find the the intersections
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> palaneIntersections = plane.findGeoIntersections(ray);
        if (palaneIntersections == null)
            return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(1).subtract(p0);
        Vector v2 = vertices.get(0).subtract(p0);
        double sign=0;
        try{
            sign = v.dotProduct(v1.crossProduct(v2));}
        catch (IllegalArgumentException e)
        {
            System.out.println(this.vertices);
        }
        if (isZero(sign))
            return null;

        boolean positive = sign > 0;

        for (int i = vertices.size() - 1; i > 0; --i) {
            v1 = v2;
            v2 = vertices.get(i).subtract(p0);
            sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
            if (isZero(sign)) return null;
            if (positive != (sign > 0)) return null;
        }

        //for GeoPoint
        List<GeoPoint> result = new LinkedList<>();
        for (GeoPoint geo : palaneIntersections) {
            result.add(new GeoPoint(this, geo.point3D));
        }
        return result;

//        List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray);
//        // Return null if the ray is not in the plane of the triangle
//        if (planeIntersections == null) {
//            return null;
//        }
//        List<GeoPoint> result = null;
//        // Parameters for calculation
//        List<Vector> v = new ArrayList<>();
//        for (int i = 1; i <= vertices.size(); i++) {
//            v.add(i-1, vertices.get(i-1).subtract(ray.getP0()));
//        }
//
//        List<Vector> n = new ArrayList<>();
//        n.add(0, v.get(0).crossProduct(v.get(1)).normalized());
//        n.add(1, v.get(1).crossProduct(v.get(2)).normalized());
//
//        for (int i = 2; i < vertices.size(); i++) {
//            n.add(i, v.get(i).crossProduct(v.get(0)).normalized());
//        }
//
//        ArrayList<Double> vN = new ArrayList<>();
//
//

//        for (int i = 0; i < vertices.size(); i++) {
//            vN.add(i, ray.getDir().dotProduct(n.get(i)));
//        }
//
//        if (vN.stream().allMatch(x -> x > 0) || vN.stream().allMatch(x -> x < 0)) {
//            result = planeIntersections;
//            // Implement the geometry field to be this
//            for (GeoPoint geoPoint: result) {
//                geoPoint.geometry = this;
//            }
//        }
//        return result;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double max) {
        var myList = plane.findGeoIntersections(ray, max);
        if (myList == null)
            return null;
        var dir = ray.getDir();

        var p0 = ray.getP0();
        var vectors = new LinkedList<Vector>();
        for (var vertice : vertices)
            vectors.add(vertice.subtract(p0));

        var normals = new LinkedList<Vector>();
        for (int i = 0; i < vectors.size() - 1; i++) {
            normals.add(vectors.get(i).crossProduct(vectors.get(i + 1)));
        }
        normals.add(vectors.getLast().crossProduct(vectors.getFirst()));

        Boolean isPositive = false, isNegative = false;
        for (var normal : normals) {
            var result = alignZero(normal.dotProduct(dir));
            if (result != 0) {
                if (result > 0) {
                    isPositive = true;
                    if (isNegative == true)
                        return null;
                } else if (result < 0) {
                    isNegative = true;
                    if (isPositive == true)
                        return null;
                }
            } else
                return null;
        }

        return List.of(new GeoPoint(this, myList.get(0).point3D));
    }

    @Override
    public void setMaxBoundary() {
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        double x, y, z;
        for (Point3D p : vertices) {
            x = p.getX();
            y = p.getY();
            z = p.getZ();
            if (x > maxX)
                maxX = x;
            if (y > maxY)
                maxY = y;
            if (z > maxZ)
                maxZ = z;
        }
        maxBoundary = new Point3D(maxX , maxY , maxZ);
    }

    @Override
    public void setMinBoundary() {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double x, y, z;
        for (Point3D p : vertices) {
            x = p.getX();
            y = p.getY();
            z = p.getZ();
            if (x < minX)
                minX = x;
            if (y < minY)
                minY = y;
            if (z < minZ)
                minZ = z;
        }
        minBoundary = new Point3D(minX, minY, minZ);
    }
}
