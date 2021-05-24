package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    protected final Point3D _position;
    private Double _kC = 1d, _kL=0d, _kQ=0d;

    public PointLight setkC(Double kC) {
        _kC = kC;
        return this;
    }

    public PointLight setkL(Double kL) {
        _kL = kL;
        return this;
    }

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

    @Override
    public Color getIntensity(Point3D p) {
        double d = _position.distance(p);
        return super.getIntensity().scale(1/ denominator(d));
    }

    protected double denominator(double d) {
        return _kC + (_kL * d) + (_kQ * d * d);
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }
}
