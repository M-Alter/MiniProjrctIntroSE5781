package primitives;

import java.util.Objects;

public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this(_x.coord, _y.coord, _z.coord);
    }

    public Point3D(double x, double y, double z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }

    public Coordinate get_x() {
        return _x;
    }

    public Coordinate get_y() {
        return _y;
    }

    public Coordinate get_z() {
        return _z;
    }

public Point3D add(Vector vector){
        return  new Point3D(
                _x.coord + vector._head._x.coord,
                _y.coord + vector._head._y.coord,
                _z.coord + vector._head._z.coord);
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z +
                '}';
    }
}
