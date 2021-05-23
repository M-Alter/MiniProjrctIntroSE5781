package primitives;

import java.time.Period;
import java.util.List;
import geometries.Intersectable.GeoPoint;

public class Ray {

    // variables
    final Point3D _p0;
    final Vector _dir;

    // constructor
    public Ray(Point3D pnt, Vector vec){
        _p0 = new Point3D(pnt._x, pnt._y, pnt._z);
        _dir = vec.normalized();
    }

    // getters
    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    // override functions
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     * find the point that 't' * _dir away from the _p0
     * @param t distance from p0
     * @return the point
     */
    public Point3D getPoint(double t){
        return _p0.add(_dir.scale(t));
    }

    @Override
    public String toString() {
        return  "p0 = " + _p0.toString() +
                ", dir = " + _dir;
    }


    /**
     * method to find the point closest to the ray's p0 in a list of points
     * @param points list of points
     * @return the point that's closest to the rays p0
     */
    public Point3D findClosestPoint(List<Point3D> points){
        Point3D result = points.get(0);
        double min = _p0.distance(points.get(0));
        for (Point3D p : points) {
            if (_p0.distance(p) < min){
                min = _p0.distance(p);
                result = p;
            }
        }
        return result;
    }

    /**
     * method to find the geoPoint closest to the ray's p0 in a list of points
     * @param points list of geoPoints
     * @return the geoPoint that's closest to the rays p0
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points){
        GeoPoint result = points.get(0);
        double min = _p0.distance(points.get(0).point3D);
        for (GeoPoint p : points) {
            if (_p0.distance(p.point3D) < min){
                min = _p0.distance(p.point3D);
                result = p;
            }
        }
        return result;
    }
}
