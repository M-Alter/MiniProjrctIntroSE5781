package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{
    Point3D q0;
    Vector normal;

    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    public Plane(Point3D vertex1, Point3D vertex2, Point3D vertex3) {
        this.q0 = vertex1;
        this.normal = null;
    }


    public Point3D getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D pnt) {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
