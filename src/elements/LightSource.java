package elements;

import primitives.*;

/**
 * interface class of the light source
 */
public interface LightSource {

    /**
     * method of getIntensity for receive color at a point
     * @param p point
     * @return
     */
    public Color getIntensity(Point3D p);

    /**
     * method of getL for receive direction at a point
     * @param p
     * @return
     */
    public Vector getL(Point3D p);
}
