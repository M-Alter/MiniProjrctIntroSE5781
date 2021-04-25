package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    double height;

    /**
     * Constructor of a cylinder
     * @param axisRay The direction of the cylinder
     * @param radius the width of the cylinder
     * @param height The length of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * The normal of the cylinder
     * @param pnt
     * @return The normal of the cylinder
     */
    @Override
    public Vector getNormal(Point3D pnt) {
        return super.getNormal(pnt);
    }
}
