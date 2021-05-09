package primitives;

import java.util.Objects;

/**
 * Point3D class is represent a point in the plan
 */
public class Point3D {

    /**
     * x, y, z coordinates of the point
     */
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    /**
     * ZERO point (0,0,0)
     */
    public final static Point3D ZERO = new Point3D(0,0,0);

    public double getX() {
        return _x.coord;
    }

    public double getY() {
        return _y.coord;
    }

    public double getZ() {
        return _z.coord;
    }

    /**
     * Constructor of point that receive 3 coordinates
     * @param _x x coordinate of the point
     * @param _y y coordinate of the point
     * @param _z z coordinate of the point
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this(_x.coord, _y.coord, _z.coord);
    }

    /**
     * Constructor of point that receive 3 double
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }


    public Point3D add(Vector vector){
        return  new Point3D(
                _x.coord + vector._head._x.coord,
                _y.coord + vector._head._y.coord,
                _z.coord + vector._head._z.coord);
    }

    // functions
    public Vector subtract(Point3D secPoint){
        return new Vector(
                _x.coord-secPoint._x.coord,
                _y.coord-secPoint._y.coord,
                _z.coord-secPoint._z.coord);
    }

    public double distanceSquared(Point3D pnt){
        return (
                (_x.coord-pnt._x.coord)*(_x.coord-pnt._x.coord) +
                (_y.coord-pnt._y.coord)*(_y.coord-pnt._y.coord)+
                (_z.coord-pnt._z.coord)*(_z.coord-pnt._z.coord));
    }

    public double distance(Point3D pnt){
        return (Math.sqrt(distanceSquared(pnt)));
    }


    // override functions
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }


    @Override
    public String toString() {
        return "("  + _x +
                ", " + _y +
                ", " + _z +
                ')';
    }
}
