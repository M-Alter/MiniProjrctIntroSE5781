package geometries;

import primitives.*;

public interface Geometry extends Intersectable{
    public Vector getNormal(Point3D pnt);
}
