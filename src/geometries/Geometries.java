package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    /**
     * A list of intersectable geometries
      */
    private List<Intersectable> _geometries;

    /**
     * constructor of geometry
     */
    public Geometries() {
        _geometries = new LinkedList<Intersectable>();
    }

    /**
     *  constructor of geometry
     * @param geometries list of intersectable geometries bodies
     */
    public Geometries(Intersectable... geometries) {
        _geometries = new LinkedList<Intersectable>();

        _geometries.addAll(Arrays.asList(geometries));
    }

    /**
     *
     * @param geometries
     */
    public void add(Intersectable... geometries){
        _geometries.addAll(Arrays.asList(geometries));
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
}
