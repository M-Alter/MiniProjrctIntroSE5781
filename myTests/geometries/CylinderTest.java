package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class CylinderTest {

    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============

        //test when the point is at the the side of the cylinder
        Vector v1 = new Vector(0,1,1).normalize();
        Cylinder cylinder  = new Cylinder( new Ray(new Point3D(2,2,-1), v1),5, 10);
        Point3D point = new Point3D(7,3,0);
        assertEquals(cylinder.getNormal(point),(new Vector(1,0,0)));

        //test when the point is at one base
        cylinder  = new Cylinder( new Ray(new Point3D(2,2,-1), v1),5, Math.sqrt(2));
        point = new Point3D(4,3,0);
        assertEquals(cylinder.getNormal(point),(new Vector(0,1,1).normalize()));

//        //test when  the point is at the other  base
//        cylinder  = new Cylinder( new Ray(new Point3D(2,2,-1), v1),5, 10);
//        point = new Point3D(3,2,-1);
//        assertEquals(cylinder.getNormal(point),(new Vector(0,-1,-1).normalize().scale(-1)));
//
//
//        // =============== Boundary Values Tests ==================
//        //test when  the point is at the upwards base edge
//        cylinder  = new Cylinder( new Ray(new Point3D(2,2,-1), v1),5, Math.sqrt(2));
//        point = new Point3D(7,3,0);
//        assertEquals(cylinder.getNormal(point),((v1.add(new Vector(1,0,0)).normalize())));
//
//        //test when  the point is at the other  base edge
//        cylinder  = new Cylinder( new Ray(new Point3D(2,2,-1), v1), 5,Math.sqrt(2));
//        point = new Point3D(7,2,-1);
//        assertEquals(cylinder.getNormal(point),((v1.scale(-1).add(new Vector(1,0,0)).normalize())));
    }
}