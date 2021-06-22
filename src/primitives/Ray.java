package primitives;

import java.time.Period;
import java.util.List;
import geometries.Intersectable.GeoPoint;

public class Ray {

    /**
     * the point of the beginning
     */
    final Point3D _p0;
    /**
     * the direction vector from the point
     */
    final Vector _dir;
    /**
     * constant double DELTA = 0.1
     */
    public static final double DELTA = 0.1;

    /**
     * constructor of the ray
     * @param pnt the point of the ray
     * @param vec the direction of the ray
     */
    public Ray(Point3D pnt, Vector vec){
        _p0 = new Point3D(pnt._x, pnt._y, pnt._z);
        _dir = vec.normalized();
    }

    /**
     * constructor of the ray
     * @param head the head of the ray
     * @param dir the direction of the ray
     * @param normal the normal of the ray
     */
    public Ray(Point3D head, Vector dir, Vector normal){
        double nv = normal.dotProduct(dir.normalized());
        if (nv > 0)
            _p0 = head.add(normal.scale(DELTA));
        else
            _p0 = head.add(normal.scale(DELTA * -1));
        _dir = dir.normalized();
    }

    /**
     * getter of the point
     * @return the point of the ray
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * getter of the direction
     * @return the direction of the ray
     */
    public Vector getDir() {
        return _dir;
    }

    /**
     *  override function to calculate if two vectors are equals
     * @param o object to compare
     * @return if this two objects are equals
     */
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

    /**
     * function to return the ray as string
     * @return the point as string
     */
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
        if (points == null || points.isEmpty()) return null;
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
        if (points == null || points.isEmpty()) return null;
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
