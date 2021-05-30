package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class that create a light source
 */
public class DirectionalLight extends Light implements LightSource{
    private final Vector _direction;

    /**
     * constructor of Light
     * @param intensity
     * @param direction
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    /**
     * getter of intensity
     * @param p
     * @return super.getIntensity()
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    /**
     * override of the getL
     * @param p point
     * @return the direction
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    /**
     * method to return the distance between point of the light to the received point
     *
     * @param p point to calculate
     * @return the distance
     */
    @Override
    public double getDistance(Point3D p) {
        return Double.POSITIVE_INFINITY;
    }
}
