package elements;

import primitives.*;

/**
 * interface class of the light source
 */
public interface LightSource {

    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);
}
