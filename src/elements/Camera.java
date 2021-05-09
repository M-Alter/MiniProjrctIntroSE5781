package elements;

import primitives.*;

public class Camera {
    /**
     * Properties of a camera
     */
    private Point3D _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;
    private double _width;
    private double _height;
    private double _distance;

    /**
     *  Constructor of a camera
     * @param p0 center of the camera
     * @param vUp up vector
     * @param vTo vector to the center
     */
    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        // throw exception where the vectors are not orthogonal
        if (vUp.dotProduct(vTo) != 0){
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        }
        this._p0 = p0;
        this._vUp = vUp.normalized();
        this._vTo = vTo.normalized();
        // vRight vector is a normal
        _vRight = vTo.crossProduct(vUp).normalized();
    }

    /**
     * set the plane height and width
      * @param width the width of the view frame
     * @param height the height of the view frame
     * @return the instance similar to the design pattern
     */
    public Camera setViewPlaneSize(double width, double height){
        _width = width;
        _height = height;
        return this;
    }

    /**
     * set the distance to the object
     * @param distance distance
     * @return the instance similar to the design pattern
     */
    public Camera setDistance(double distance){
        _distance = distance;
        return this;
    }

    /**
     *  getter of the point
     * @return p0 point
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * getter of vUp
     * @return vUp vector
     */
    public Vector get_vUp() {
        return _vUp;
    }

    /**
     * getter of vTo
     * @return vTo vector
     */
    public Vector get_vTo() {
        return _vTo;
    }

    /**
     * getter of vRight
     * @return vRight vector
     */
    public Vector get_vRight() {
        return _vRight;
    }

    /**
     * getter of the width
     * @return the width of the picture
     */
    public double get_width() {
        return _width;
    }
    /**
     * getter of the height
     * @return the height of the picture
     */
    public double get_height() {
        return _height;
    }
    /**
     * getter of the distance
     * @return the width of the distance
     */
    public double get_distance() {
        return _distance;
    }

    /**
     * construct that created the ray to pixel i*j
     * @param nX amount of the pixels from left to right
     * @param nY amount of the pixels from bottom to top
     * @param j current parameter from left to right
     * @param i current parameter from bottom to top
     * @return the ray to the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point3D pC = _p0.add(_vTo.scale(_distance));
        double rY = _height/nY;
        double rX = _width/nX;

        double yI = -(i-((nY-1)/2d))*rY;
        double xJ = (j-(nX-1)/2d)*rX;


        Point3D pIJ = pC;
        if (xJ != 0) pIJ = pIJ.add(_vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(_vUp.scale(yI));
        Vector vIJ = pIJ.subtract(_p0);

        return new Ray(_p0,vIJ);

    }
}
