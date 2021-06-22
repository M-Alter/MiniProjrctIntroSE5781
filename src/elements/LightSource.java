package elements;

import primitives.*;

/**
 * interface class of the light source
 */
public interface LightSource {

    /**
     * method of getIntensity for receive color at a point
     * @param p point
     * @return the color
     */
    public Color getIntensity(Point3D p);

    /**
     * method of getL for receive direction at a point
     * @param p
     * @return the direction
     */
    public Vector getL(Point3D p);

    /**
     * method to return the distance between point of the light to the received point
     * @param p point to calculate
     * @return the distance
     */
    public double getDistance(Point3D p);
}
