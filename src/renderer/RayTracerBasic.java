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
public class RayTracerBasic extends RayTracerBase {

    /**
     * constant of maximum calculation of color level
     */
    public static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * constant of minimum calculation of
     */
    public static final double MIN_CALC_COLOR_K = 0.001;
    /**
     * constant of initial value of K = 1.0
     */
    private static final double INITIAL_K = 1.0;

    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    /**
     * method that return the color the ray meets
     *
     * @param ray to intersection ray
     * @return color of the intersection where there is intersection,
     * else return the background of the scene
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? _scene.background : calcColor(closestPoint, ray);
    }

    /**
     * method that return the intensity of the color
     *
     * @param geoPoint point that we have handling
     * @param ray      the ray to the point
     * @return the intensity of the color
     */
    protected Color calcColor(GeoPoint geoPoint, Ray ray) {
        //Color emissionColor = geoPoint.geometry.getEmission();
        //return _scene.ambientLight.getIntensity().add(emissionColor).add(calcLocalEffects(geoPoint, ray));
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     * method that return recursive the intensity of the color
     *
     * @param geoPoint geo point of the intersection
     * @param ray      the ray to the point
     * @param level    recursive condition
     * @param k        transparency level
     * @return the color of the point
     */
    protected Color calcColor(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = geoPoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geoPoint, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray.getDir(), level, k));
    }

    /**
     * method to calculate all the global effects
     *
     * @param gp    geo point that we handling
     * @param v     vector of the direction of the ray
     * @param level recursive condition
     * @param k     transparency level
     * @return the final calculation of the color after global effect
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point3D);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point3D, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point3D, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * @param point3D
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point3D point3D, Vector v, Vector n) {
        return new Ray(point3D, v, n);
    }

    /**
     * method to find the reflection ray
     *
     * @param point3D the point the reflection
     * @param v       the vector to the point
     * @param n       the normal for calculate r vector
     * @return the ray of the reflection
     */
    private Ray constructReflectedRay(Point3D point3D, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(v.dotProduct(n) * 2));
        return new Ray(point3D, r, n);
    }

    /**
     * method to calculate all the global effects
     *
     * @param ray   the direction wh shooting
     * @param level recursive condition
     * @param kx    reduce the material
     * @param kkx   the intensity of the reflection
     * @return if there are not closest point return the background
     * else return the calculation of the color
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    /**
     * method to find the closest intersection
     *
     * @param ray the ray we shooting
     * @return if there aren't intersections return null
     * else return the closest point
     */
    protected GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> points = _scene.geometries.findGeoIntersections(ray);
        return points == null ? null : ray.findClosestGeoPoint(points);
    }


    /**
     * method to find the effects of this geo point
     *
     * @param geoPoint point that we have handling
     * @param ray      the ray to the point
     * @param k
     * @return the color of the point (after calculations)
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, double k) {
        Vector v = ray.getDir();
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
                double ktr = transparency(lightSource, l, n, geoPoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point3D).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * boolean function to decide if there is shaded
     *
     * @param l  vector to direction from the light
     * @param n  vector of the normal of the body
     * @param gp the body that we check if is unshaded
     * @return boolean if this shaded or not
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Ray lightRay = new Ray(gp.point3D, lightDirection, n);
        double lightDistance = light.getDistance(gp.point3D);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1d;
        // check if there is any think between the light source to the point
        double myDistance = light.getDistance(gp.point3D);
        double ktr = 1.0;
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point3D.distance(gp.point3D) - lightDistance) <= 0)
                ktr *= geoPoint.geometry.getMaterial().kT;
            if (ktr < MIN_CALC_COLOR_K) return 0.0;
        }
        return ktr;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vrMinus = v.scale(-1).dotProduct(r);
        double vrN = Math.pow(vrMinus, nShininess);
        return lightIntensity.scale(ks * vrN);
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd * ln);
    }

}
