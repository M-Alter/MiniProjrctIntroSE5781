package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray ray) {
       List<Point3D> points = _scene._geometries.findIntersections(ray);
       if (points == null){
           return _scene._background;
       }
       Point3D p = ray.findClosestPoint(points);
        return calcColor(p);
    }

    private Color calcColor(Point3D point) {
        return _scene._ambientLight.getIntensity();

    }

}
