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
    /**
     * constant double DELTA = 0.1
     */
    public static final double DELTA = 0.1;
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
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                    if (unshaded(lightSource,l,n, geoPoint)){
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point3D);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * boolean function to decide if there is shaded
     * @param l vector to direction from the light
     * @param n vector of the normal of the body
     * @param gp the body that we check if is unshaded
     * @return boolean if this shaded or not
     */
    private boolean unshaded (LightSource light,Vector l, Vector n, GeoPoint gp){
        Vector lightDirection = l.scale(-1); // from point to light source

        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point3D point = gp.point3D.add(epsVector);

        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        // check if there is any think between the light source to the point
        double myDistance = light.getDistance(gp.point3D);
        for (GeoPoint geoPoint: intersections) {
            if (light.getDistance(geoPoint.point3D) < myDistance)
                return false;
        }
        return true;


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
