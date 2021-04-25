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
        if (vertex1.equals(vertex2) || vertex1.equals(vertex3) || vertex2.equals(vertex3))
            throw new IllegalArgumentException("2 points cannot be the same");
       try {
           Vector n = vertex2.subtract(vertex1).crossProduct(vertex3.subtract(vertex1));
           this.normal = n.normalized();
       }
       catch (IllegalArgumentException e) {
           throw new IllegalArgumentException("2 points cannot be the same");
       }
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
