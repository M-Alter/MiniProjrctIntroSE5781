package primitives;


import static primitives.Point3D.*;

/**
 * class that represents a vector
 */
public class Vector {

    /**
     * the head of the vector
     */
    Point3D _head;

    /**
     * constructor of the vector
     * @param head the head of the vector
     */
    public Vector(Point3D head) {
        this._head = new Point3D(head._x, head._y, head._z);
    }

    /**
     * constructor to create a vector with 3 doubles
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @param z z coordinate of the point
     */
    public Vector(double x, double y, double z){
        Point3D pnt = new Point3D(x,y,z);
        if(ZERO.equals(pnt)){
            throw new IllegalArgumentException("Vector can't be \"zero\" point");
        }
        _head = pnt;
    }

    /**
     * constructor to create a vector with 3 coordinates
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @param z z coordinate of the poin
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z){
        this(x.coord,y.coord,z.coord);
    }

    /**
     * get the head of the vector
     * @return the point of the head of the Vector
     */
    public Point3D getHead() {
        return _head;
    }

    /**
     * add a vector to this Vector
     * @param vec Vector to add
     * @return a new Vector which is a combination of these 2 Vectors
     */
    public Vector add(Vector vec){
        return new Vector(
                _head._x.coord + vec._head._x.coord,
                _head._y.coord + vec._head._y.coord,
                _head._z.coord + vec._head._z.coord);
    }

    /**
     * subtract a Vector from your Vector
     * @param vec Vector to subtract
     * @return a new Vector which is a combination of these 2 Vectors
     */
    public Vector subtract(Vector vec){
        return new Vector(
                _head._x.coord - vec._head._x.coord,
                _head._y.coord - vec._head._y.coord,
                _head._z.coord - vec._head._z.coord);
    }

    /**
     * scale the vector with a scalar
     * @param k scalar to multiply the vector with
     * @return a new vector multiplied by the scalar
     */
    public Vector scale(double k){
        return new Vector(
                _head._x.coord * k,
                _head._y.coord * k,
                _head._z.coord * k);
    }

    /**
     * dot product of two vectors
     * @param vec Vector to dot product with
     * @return  the dot product of the two Vectors
     */
    public double dotProduct(Vector vec){
        return (_head._x.coord * vec._head._x.coord +
                _head._y.coord * vec._head._y.coord +
                _head._z.coord * vec._head._z.coord);
    }

    /**
     * cross product of two Vectors
     * @param vec Vector to cross product with
     * @return the new Vector that's a normal to the plane created by the 2 Vectors
     */
    public Vector crossProduct(Vector vec){
        return new Vector(
                _head._y.coord * vec._head._z.coord - _head._z.coord * vec._head._y.coord,
                _head._z.coord * vec._head._x.coord - _head._x.coord * vec._head._z.coord ,
                _head._x.coord * vec._head._y.coord - _head._y.coord * vec._head._x.coord
        );
    }

    /**
     * length of the Vector squared
     * @return the length of the Vector squared
     */
    public double lengthSquared(){
        return (_head._x.coord * _head._x.coord+
                _head._y.coord * _head._y.coord+
                _head._z.coord * _head._z.coord);
    }

    /**
     * function to calculate the length of the vector
     * @return the length of the Vector
     */
    public double length(){
        return Math.sqrt((lengthSquared()));
    }

    /**
     * normalize this vector
     * @return this vector normalized
     */
    public Vector normalize(){
        double length = length();
        this._head = new Point3D(
                _head._x.coord / length,
                _head._y.coord / length,
                _head._z.coord / length);
        return this;
    }

    /**
     * doesn't mutate the class
     * @return a new vector like this vector and normalized
     */
    public Vector normalized(){
        return new Vector(_head).normalize();
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
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /**
     * function to return the vector as string
     * @return the point as string
     */
    @Override
    public String toString() {
        return _head.toString();
    }
}
