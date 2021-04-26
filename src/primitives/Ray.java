package primitives;

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
}
