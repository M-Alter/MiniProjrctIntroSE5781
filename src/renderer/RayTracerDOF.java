package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.Random;

public class RayTracerDOF extends RayTracerBasic {

    /**
     * constant of initial value of K = 1.0
     */
    private static final double INITIAL_K = 1.0;
    Random x = new Random();


    public RayTracerDOF(Scene _scene) {
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


        Point3D focalPoint = ray.getP0().add(ray.getDir().normalized().scale(_scene.focalLength));

        double r = _scene.aperture;
        Color resultColor = new Color(0, 0, 0);
        Point3D head = ray.getP0();
        Point3D tempHead;
        Ray tempRay;
        Intersectable.GeoPoint closestPoint;
        for (int i = 1; i < Math.min(90 * r + 1, 361); i++) {
            tempHead = head.add(new Vector(Math.cos((Math.PI * 2d) / (double) i), Math.sin((Math.PI * 2d) / (double) i), 0).normalized().scale(x.nextDouble() - 0.5).scale(r));
            tempRay = new Ray(tempHead, focalPoint.subtract(tempHead));
            closestPoint = findClosestIntersection(tempRay);
            if (closestPoint == null) {
                resultColor = resultColor.add(_scene.background);
                continue;
            }
            resultColor = resultColor.add(calcColor(closestPoint, tempRay));
        }
        resultColor = resultColor.scale(1d / (Math.min(90 * r, 360)));
        return resultColor;
    }

    /**
     * method that return the intensity of the color
     *
     * @param geoPoint point that we have handling
     * @param ray      the ray to the point
     * @return the intensity of the color
     */
    protected Color calcColor(Intersectable.GeoPoint geoPoint, Ray ray) {
        //Color emissionColor = geoPoint.geometry.getEmission();
        //return _scene.ambientLight.getIntensity().add(emissionColor).add(calcLocalEffects(geoPoint, ray));
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }


}
