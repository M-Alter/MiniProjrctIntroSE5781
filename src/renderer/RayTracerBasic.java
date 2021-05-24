package renderer;

import elements.LightSource;
import primitives.*;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

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
        return calcColor(p, ray);
    }

    /**
     * method that return the intensity of the color
     * @param geoPoint
     * @param ray
     * @return the intensity of the color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color emissionColor = geoPoint.geometry.getEmission();
//        Material material = geoPoint.geometry.getMaterial();
//
//        double iP = _scene.ambientLight.getIntensity().add(emissionColor)
//                .add(geoPoint.geometry.getNormal().dotProduct(_scene.ambientLight.));
        return _scene.ambientLight.getIntensity().add(emissionColor).add(calcLocalEffects(geoPoint, ray));

    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point3D);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(geoPoint.point3D);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(geoPoint.point3D);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n)*2));
        double vrMinus = v.scale(-1).dotProduct(r);
        double vrN = Math.pow(vrMinus ,nShininess);
        return lightIntensity.scale(ks*vrN);
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd*ln);
    }

}
