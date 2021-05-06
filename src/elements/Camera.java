package elements;

import primitives.*;


public class Camera {
    private Point3D _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;
    private double _width;
    private double _height;
    private double _distance;

    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        if (vUp.dotProduct(vTo) != 0){
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        }
        this._p0 = p0;
        this._vUp = vUp.normalized();
        this._vTo = vTo.normalized();
        _vRight = _vTo.crossProduct(_vUp).normalized();
    }


    public Camera setViewPlaneSize(double width, double height){
        _width = width;
        _height = height;
        return this;
    }

    public Camera setDistance(double distance){
        _distance = distance;
        return this;
    }


    public Point3D get_p0() {
        return _p0;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public Vector get_vRight() {
        return _vRight;
    }

    public double get_width() {
        return _width;
    }

    public double get_height() {
        return _height;
    }

    public double get_distance() {
        return _distance;
    }

    public Ray constructRay(int nX, int nY, int j, int i){
        Point3D pC = _p0.add(_vTo.scale(_distance));
        double rY = _height/nY;
        double rX = _width/nX;

        double yI = -(i-(nY-1)/2)*rY;
        double xJ = (j-(nX-1)/2)*rX;

        Vector vIJ = _vRight.scale(xJ).add(_vUp.scale(yI));

        return new Ray(pC,vIJ);

    }
}
