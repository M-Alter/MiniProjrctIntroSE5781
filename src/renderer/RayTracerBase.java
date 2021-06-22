package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class for tracing rays
 */
public abstract class RayTracerBase {
    /**
     * the scene of the program
     */
    protected Scene _scene;

    /**
     * constructor to follow the trace
     * @param _scene the scene to follow
     */
    public RayTracerBase(Scene _scene) {
        this._scene = _scene;
    }

    /**
     * abstract ray trace
     * @param ray to intersection ray
     * @return the closest intersection
     */
    public abstract Color traceRay(Ray ray);
}
