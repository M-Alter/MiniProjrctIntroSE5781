package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class that represents a finite cylinder
 */
public class Cylinder extends Tube{
    private final double _height;

    /**
     * Constructor of a cylinder
     * @param axisRay The direction of the cylinder
     * @param radius the width of the cylinder
     * @param height The length of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this._height = height;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = super.findGeoIntersections(ray);
        List<GeoPoint> result = new LinkedList<>();
        if (intersections != null) {
            for (GeoPoint geoPoint : intersections)
            {
                result.add(new GeoPoint(this, geoPoint.point3D));
            }
            return result;
        }
        return null;
    }

    /**
     * The normal of the cylinder
     * @param pnt
     * @return The normal of the cylinder
     */
    @Override
    public Vector getNormal(Point3D pnt) {

        Point3D o = _axisRay.getP0();
        Vector v = _axisRay.getDir();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(pnt.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return pnt.subtract(o).normalize();
    }
}
