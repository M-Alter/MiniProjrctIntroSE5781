package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * extends RayTracerBase
 * implement the methods
 */
public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    /**
     * method that return the color the ray meets
     * @param ray to intersection ray
     * @return color of the intersection
     */
    @Override
    public Color traceRay(Ray ray) {
       List<Point3D> points = _scene._geometries.findIntersections(ray);
       if (points == null){
           return _scene._background;
       }
       Point3D p = ray.findClosestPoint(points);
        return calcColor(p);
    }

    /**
     * method that return the intensity of the color
     * @param point
     * @return the intensity of the color
     */
    private Color calcColor(Point3D point) {
        return _scene._ambientLight.getIntensity();

    }

}
