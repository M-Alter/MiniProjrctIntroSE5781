package geometries;

import primitives.*;

/**
 * interface for intersectable that has a method of getNormal
 */
public interface Geometry extends Intersectable{
    public Vector getNormal(Point3D pnt);
}
