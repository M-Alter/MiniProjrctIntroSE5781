package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import geometries.Intersectable.GeoPoint;

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
       List<GeoPoint> points = _scene.geometries.findGeoIntersections(ray);
       if (points == null){
           return _scene.background;
       }
       GeoPoint p = ray.findClosestGeoPoint(points);
        return calcColor(p);
    }

    /**
     * method that return the intensity of the color
     * @param geoPoint
     * @return the intensity of the color
     */
    private Color calcColor(GeoPoint geoPoint) {
        Color emissionColor = geoPoint.geometry.getEmission();
        //emissionColor.add(_scene.ambientLight.getIntensity());
        return _scene.ambientLight.getIntensity().add(emissionColor);

    }

}
