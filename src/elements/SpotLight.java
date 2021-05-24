package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector _direction;

    /**
     * constructor of PointLight
     *
     * @param intensity the color of point light
     * @param position  where the light it is
     * @param kC        the kC factor of the distance
     * @param kL        the kL factor of the distance
     * @param kQ        the kQ factor of the distance
     * @param direction the direction vector
     */
    protected SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector direction) {
        super(intensity, position, kC, kL, kQ);
        _direction = direction;
    }
}
