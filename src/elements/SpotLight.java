package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class to represent spot light
 */
public class SpotLight extends PointLight{
    /**
     * vector that represent the direction of the light
     */
    private final Vector _direction;

    /**
     * constructor of PointLight
     *
     * @param intensity the color of point light
     * @param position  where the light it is
     * @param direction the direction vector
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    /**
     * override the method getIntensity
     * @param p point
     * @return the intensity at the point
     */
    @Override
    public Color getIntensity(Point3D p) {
        Vector l = getL(p);
        return super.getIntensity().scale(Math.max(0 , _direction.dotProduct(l)));
    }
}
