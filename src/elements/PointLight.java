package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class to represent a point light
 */
public class PointLight extends Light implements LightSource{

    /**
     * point3D of the position
     */
    protected final Point3D _position;
    /**
     * double that representing the distance
     */
    private Double _kC = 1d, _kL=0d, _kQ=0d;

    /**
     * setter for kC
     * @param kC
     * @return this
     */
    public PointLight setkC(Double kC) {
        _kC = kC;
        return this;
    }

    /**
     * setter for kL
     * @param kL
     * @return this
     */
    public PointLight setkL(Double kL) {
        _kL = kL;
        return this;
    }

    /**
     * settern for kQ
     * @param kQ
     * @return this
     */
    public PointLight setkQ(Double kQ) {
        _kQ = kQ;
        return this;
    }

    /**
     * constructor of PointLight
     * @param intensity the color of point light
     * @param position where the light it is
     */
    protected PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;

    }

    /**
     * override method getIntensity
     * @param p point
     * @return calculate of the intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = _position.distance(p);
        return super.getIntensity().scale(1/ denominator(d));
    }

    /**
     * function to calculate the denominator
     * @param d
     * @return denominator calculation
     */
    protected double denominator(double d) {
        return _kC + (_kL * d) + (_kQ * d * d);
    }

    /**
     * override method getL
     * @param p point to calculation
     * @return vector of the normalized direction
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }

    /**
     * method to return the distance between point of the light to the received point
     *
     * @param p point to calculate
     * @return the distance
     */
    @Override
    public double getDistance(Point3D p) {
        return _position.distance(p);
    }
}
