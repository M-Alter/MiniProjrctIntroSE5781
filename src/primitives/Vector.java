package primitives;
import java.util.Objects;

import static primitives.Point3D.*;

/**
 * Vector class is represent a vector in the plan
 */

public class Vector {

    Point3D _head;

    public Vector(Point3D head) {
        this._head = new Point3D(head._x, head._y, head._z);
    }

    public Vector(double x, double y, double z){
        Point3D pnt = new Point3D(x,y,z);
        if(ZERO.equals(pnt)){
            throw new IllegalArgumentException("Vector can't be \"zero\" point");
        }
        _head = pnt;
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z){
        this(x.coord,y.coord,z.coord);
    }

    public Vector add(Vector vec){
        return new Vector(
                _head._x.coord + vec._head._x.coord,
                _head._y.coord + vec._head._y.coord,
                _head._z.coord + vec._head._z.coord);
    }

    public Vector subtract(Vector vec){
        return new Vector(
                _head._x.coord - vec._head._x.coord,
                _head._y.coord - vec._head._y.coord,
                _head._z.coord - vec._head._z.coord);
    }

    public Vector scale(double k){
        return new Vector(
                _head._x.coord * k,
                _head._y.coord * k,
                _head._z.coord * k);
    }

    public double dotProduct(Vector vec){
        return (_head._x.coord * vec._head._x.coord +
                _head._y.coord * vec._head._y.coord +
                _head._z.coord * vec._head._z.coord);
    }

    public Vector crossProduct(Vector vec){
        return new Vector(
                _head._y.coord * vec._head._z.coord - _head._z.coord * vec._head._y.coord,
                _head._x.coord * vec._head._z.coord - _head._z.coord * vec._head._x.coord,
                _head._x.coord * vec._head._y.coord - _head._y.coord * vec._head._x.coord
        );
    }

    public double lengthSquared(){
        return (_head._x.coord * _head._x.coord+
                _head._y.coord * _head._y.coord+
                _head._z.coord * _head._z.coord);
    }

    public double length(){
        return Math.sqrt((lengthSquared()));
    }

    public Vector normalize(){
        double length = length();
        this._head = new Point3D(
                _head._x.coord / length,
                _head._y.coord / length,
                _head._z.coord / length);
        // Should verify if _haed can be not final
//        this._head._x = new Coordinate(_head._x.coord / length);
//        this._head._y = new Coordinate(_head._y.coord / length);
//        this._head._z = new Coordinate(_head._z.coord / length);
        return this;
    }

    public Vector normalized(){
        return new Vector(_head).normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    public Point3D getHead() {
        return _head;
    }

    @Override
    public String toString() {
        return _head.toString();
    }
}
