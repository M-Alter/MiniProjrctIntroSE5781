package geometries;

import primitives.Point3D;
import primitives.Ray;
import scene.Box;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * class Geometries to keep all the geometries of the picture
 */
public class Geometries extends Intersectable {

    private Intersectable lastAdded;
    /**
     * A list of intersectable geometries
      */
    private List<Intersectable> _geometries;

    /**
     * constructor of geometry
     */
    public Geometries() {
        _geometries = new LinkedList<Intersectable>();
        minBoundary = new Point3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        maxBoundary = new Point3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    /**
     *  constructor of geometry
     * @param geometries list of intersectable geometries bodies
     */
    public Geometries(Intersectable... geometries) {
        this(); // first we initialize the list
        add(geometries);
    }



    /**
     * add geometries to the list
     * @param geometries geometry bodies to add to the list
     */
    public void add(Intersectable... geometries){
        for (Intersectable intersectable : geometries) {
            _geometries.add(intersectable);
            lastAdded = intersectable;
            setMinBoundary();
            setMaxBoundary();
        }
    }

    /**
     * A method to find the the intersections
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = null;
        List<GeoPoint> temp;
        for (Intersectable item: _geometries) {
            temp = item.findGeoIntersections(ray);
            if (temp != null){
                if (result == null){
                    result = new LinkedList<GeoPoint>();
                }
                result.addAll(temp);
            }
        }
        return result;
    }

    /**
     * get all the bodies
     */
    public List<Intersectable> getBodies() {
        return _geometries;
    }

    /**
     * This function returns only the relevant point of the intersection using the
     * help of regular grid structure if the box is null that means we call the
     * regular find intersection (without acceleration)
     *
     * @param ray            - Ray that intersect
     * @param box            - box of the scene
     * @param shadowRaysCase - if its shadow ray we traverse always all the way .
     * @param dis            - distance for find intersection
     * @return the relevant point
     */
    public List<GeoPoint> findRelevantIntersections(Ray ray, Box box, boolean shadowRaysCase, double dis) {
        if (box == null)
            return this.findGeoIntersections(ray, dis);
        return box.findIntersectionsInTheBox(ray, shadowRaysCase, dis);
    }

    /**
     * Function Return all the intersection Points of the Ray in the Geometry in
     * specific distance ( that not bigger than max)
     *
     * @param ray - The ray that crosses the body
     * @param max - maximum distance of intersection
     * @return list of the intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double max) {
        List<GeoPoint> points = null;
        if (_geometries != null) {
            for (var body : _geometries) {
                var result = body.findGeoIntersections(ray, max);
                if (result != null)
                    if (points == null)
                        points = new LinkedList<GeoPoint>(result);
                    else
                        points.addAll(result);
            }
        }
        return points;
    }

    @Override
    public void setMaxBoundary() {
        double x, y, z;
        x = lastAdded.maxBoundary.getX();
        y = lastAdded.maxBoundary.getY();
        z = lastAdded.maxBoundary.getZ();
        double maxX = maxBoundary.getX();
        double maxY = maxBoundary.getY();
        double maxZ = maxBoundary.getZ();
        if (x > maxX)
            maxX = x;
        if (y > maxY)
            maxY = y;
        if (z > maxZ)
            maxZ = z;
        maxBoundary = new Point3D(maxX, maxY, maxZ);
    }

    @Override
    public void setMinBoundary() {
        double x, y, z;
        x = lastAdded.minBoundary.getX();
        y = lastAdded.minBoundary.getY();
        z = lastAdded.minBoundary.getZ();
        double minX = minBoundary.getX();
        double minY = minBoundary.getY();
        double minZ = minBoundary.getZ();
        if (x < minX)
            minX = x;
        if (y < minY)
            minY = y;
        if (z < minZ)
            minZ = z;
        minBoundary = new Point3D(minX, minY, minZ);
    }
}
