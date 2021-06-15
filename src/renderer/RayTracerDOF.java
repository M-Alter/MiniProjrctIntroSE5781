package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.Random;

public class RayTracerDOF extends RayTracerBasic{

    Random r = new Random();

    public RayTracerDOF(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        Point3D focalPoint = ray.getP0().add(ray.getDir().scale(_scene.focalLength));

        Intersectable.GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null)
                return _scene.background;
        else if (closestPoint.point3D.distance(focalPoint) < 5 )
            return calcColor(closestPoint, ray);
        Point3D origin = new Point3D(
         ray.getP0().getX() + (r.nextDouble()*_scene.aperture-0.5),
         ray.getP0().getY() + (r.nextDouble()*_scene.aperture-0.5),
         ray.getP0().getZ() + (r.nextDouble()*_scene.aperture-0.5)
        );

        Ray shiftedRay = new Ray(origin, focalPoint.subtract(origin));

        return super.traceRay(shiftedRay);
    }
}
