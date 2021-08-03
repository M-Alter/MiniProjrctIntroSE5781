package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * class that represents a infinite tue
 */
public class Tube extends Geometry {
    /**
     * the axis ray of the tube
     */
    protected Ray _axisRay;
    /**
     * the radius of the tube
     */
    protected double _radius;

    /**
     * Constructor of a tube
     *
     * @param axisRay The direction of the tube
     * @param radius  The width of the tube
     */
    public Tube(Ray axisRay, double radius) {
        this._axisRay = axisRay;
        this._radius = radius;
    }

    /**
     * The normal of the tube to the point
     *
     * @param pnt point to calculate the normal
     * @return The normal of the tube to the point
     */
    @Override
    public Vector getNormal(Point3D pnt) {
        if (pnt.equals(_axisRay.getP0()))
            throw new IllegalArgumentException("this point would create a zero vector");
        double t = _axisRay.getDir().dotProduct(pnt.subtract(_axisRay.getP0()));
        Point3D o = _axisRay.getP0().add(_axisRay.getDir().scale(t));
        return pnt.subtract(o).normalized();

    }

    /**
     * method to find the intersections with the tube
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    /**
     * A method to find the the intersections
     *
     * @param ray the ray that engage the geometry body
     * @return list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Point3D p = ray.getP0();
        Point3D _point = this._axisRay.getP0();

        Vector v = ray.getDir(),
                vA = this._axisRay.getDir(),
                deltaP = new Vector(p.subtract(_point).getHead()),
                tempForUse1, temp_for_use2;

        double V_dot_Va = v.dotProduct(vA),
                DeltaP_dot_Va = deltaP.dotProduct(vA);

        tempForUse1 = v.subtract(vA.scale(V_dot_Va));
        temp_for_use2 = deltaP.subtract(vA.scale(DeltaP_dot_Va));

        double A = tempForUse1.dotProduct(tempForUse1);
        double B = 2 * v.subtract(vA.scale(V_dot_Va)).dotProduct(deltaP.subtract(vA.scale(DeltaP_dot_Va)));
        double C = temp_for_use2.dotProduct(temp_for_use2) - _radius * _radius;
        double desc = alignZero(B * B - 4 * A * C);

        if (desc < 0) {//No solution
            return null;
        }

        double t1 = (-B + Math.sqrt(desc)) / (2 * A),
                t2 = (-B - Math.sqrt(desc)) / (2 * A);

        if (desc == 0) {//One solution
            if (-B / (2 * A) < 0) {
                return null;
            } else {
                return List.of(new GeoPoint(this, p.add(v.scale(-B / (2 * A)))));
            }
        } else if (t1 < 0 && t2 < 0) {
            return null;
        } else if (t1 < 0 && t2 > 0) {
            return List.of(new GeoPoint(this, p.add(v.scale(t2))));
        } else if (t1 > 0 && t2 < 0) {
            return List.of(new GeoPoint(this, p.add(v.scale(t1))));
        } else {
            return List.of(
                    new GeoPoint(this, p.add(v.scale(t1))),
                    new GeoPoint(this, p.add(v.scale(t2)))
            );
        }
    }

    /**
     * Function Return all the intersection Points of the Ray in the Geometry in
     * specific distance ( that not bigger than max)
     *
     * @param ray - The ray that crosses the body
     * @param max - maximum distance of intersection
     * @return list of the intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double max) {
        return null;
    }

    /**
     * set the maximum Boundary for geometry
     */
    @Override
    public void setMaxBoundary() {

    }

    /**
     * set the minimum Boundary for geometry
     */
    @Override
    public void setMinBoundary() {

    }
}
