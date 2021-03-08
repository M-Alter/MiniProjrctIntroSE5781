package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Polygon{

    // constructor
    public Triangle(Point3D x,Point3D y,Point3D z) {
        super(x,y,z);
    }

    // override function
    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }
}
