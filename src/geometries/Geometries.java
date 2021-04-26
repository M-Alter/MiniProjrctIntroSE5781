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

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
