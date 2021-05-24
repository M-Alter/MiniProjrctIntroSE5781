package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point3D _position;
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
     * @param kC the kC factor of the distance
     * @param kL the kL factor of the distance
     * @param kQ the kQ factor of the distance
     */
    protected PointLight(Color intensity, Point3D position, double kC,double kL, double kQ) {
        super(intensity);
        _position = position;
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return null;
    }

    @Override
    public Vector getL(Point3D p) {
        return null;
    }
}
