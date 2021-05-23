package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

public class Plane implements Geometry {
    Point3D _q0;
    Vector _normal;

    public Plane(Point3D q0, Vector normal) {
        this._q0 = q0;
        this._normal = normal;
    }

    public Plane(Point3D vertex1, Point3D vertex2, Point3D vertex3) {
        this._q0 = vertex1;
        if (vertex1.equals(vertex2) || vertex1.equals(vertex3) || vertex2.equals(vertex3))
            throw new IllegalArgumentException("2 points cannot be the same");
        try {
            Vector n = vertex2.subtract(vertex1).crossProduct(vertex3.subtract(vertex1));
            this._normal = n.normalized();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("2 points cannot be the same");
        }
    }


    public Point3D getQ0() {
        return _q0;
    }

    public Vector getNormal() {
        return _normal;
    }

    @Override
    public Vector getNormal(Point3D pnt) {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + _q0 +
                ", normal=" + _normal +
                '}';
    }

    /**
     * Find the intersection between a ray to a plane
     * @param ray
     * @return List with the intersection
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result;
        // Parameters for calculation

        //check if ray starts in the plane and prevent zero vector
        if (_q0.equals(ray.getP0())){
            return null;
        }

        double numerator = _normal.dotProduct(_q0.subtract(ray.getP0()));
        double denominator = _normal.dotProduct(ray.getDir());
        // Return null when the normal is orthogonal to the ray
        if (isZero(denominator)) {
            return null;
        }
        // Return null when t is the opposite direction of the ray
        double t = alignZero(numerator / denominator);
        if (t <= 0) {
            return null;
        }
        // Add the intersection to the list and return the list
        result = new LinkedList<Point3D>();
        result.add(ray.getPoint(t));

        return result;
    }
}
