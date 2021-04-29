package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * A list of points that the ray meets the geometric bodies
 */
public interface Intersectable {
    public List<Point3D> findIntersections(Ray ray);
}
