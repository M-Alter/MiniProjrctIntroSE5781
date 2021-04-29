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

    public Geometries() {
        _geometries = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        _geometries = new LinkedList<Intersectable>();

        _geometries.addAll(Arrays.asList(geometries));
    }

    public void add(Intersectable... geometries){
        _geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * Find the intersection between a ray to a geometry
     * @param ray
     * @return A list of all intersections of the ray with all geometries
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;
        List<Point3D> temp;
        for (Intersectable item: _geometries) {
            temp = item.findIntersections(ray);
            if (temp != null){
                if (result == null){
                    result = new LinkedList<Point3D>();
                }
                result.addAll(temp);
            }
        }
        return result;
    }
}
