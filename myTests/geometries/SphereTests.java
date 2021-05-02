package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTests {
    /**
     * Testing the normal of a sphere
     */
    @Test
    public void normalTest() {
        Sphere spr = new Sphere(new Point3D(0, 0, 0), 1d);
        assertEquals(new Vector(1, 0, 0), spr.getNormal(new Point3D(1, 0, 0)), "Normal is not as expected");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void intersectionsTest() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);
// ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)

        assertNull(sphere.findIntersections(
                new Ray(new Point3D(-1, 0, 0),
                        new Vector(1, 1, 0))),
                "Rays line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(),"Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result,"Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray((new Point3D(.5,0,0)),
                new Vector(1,0,0)));
        assertEquals(1,result.size(),"Wrong number of points");

        // TC04: Ray starts after the sphere (0 points)

        result = sphere.findIntersections(new Ray((new Point3D(2.5,0,0)),
                new Vector(1,0,0)));
        assertEquals(null,result,"Wrong number of points");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray((new Point3D(0,0,0)),
                new Vector(1,0,0)));
        assertEquals(1,result.size(),"Wrong number of points");
        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray((new Point3D(2,0,0)),
                new Vector(1,0,0)));
        assertEquals(null,result,"Wrong number of points");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray((new Point3D(2.5,0,0)),
                new Vector(-1,0,0)));
        assertEquals(2,result.size(),"Wrong number of points");
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray((new Point3D(2,0,0)),
                new Vector(-1,0,0)));
        assertEquals(1,result.size(),"Wrong number of points");
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray((new Point3D(0.5,0,0)),
                new Vector(-1,0,0)));
        assertEquals(1,result.size(),"Wrong number of points");
        // TC16: Ray starts at the center (1 points)
        //TODO zero vector when subtracting p0 from centre
//        result = sphere.findIntersections(new Ray((new Point3D(1,0,0)),
//                new Vector(0,1,0)));
//        assertEquals(1,result.size(),"Wrong number of points");
        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray((new Point3D(2,0,0)),
                new Vector(1,0,0)));
        assertEquals(null,result,"Wrong number of points");
        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray((new Point3D(2.1,0,0)),
                new Vector(1,0,0)));
        assertEquals(null,result,"Wrong number of points");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray((new Point3D(0,0,1)),
                new Vector(1,0,1)));
        assertEquals(null,result,"Wrong number of points");
        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray((new Point3D(1,0,1)),
                new Vector(0,0,1)));
        assertEquals(null,result,"Wrong number of points");
        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray((new Point3D(0.5,0,1)),
                new Vector(0,0,1)));
        assertEquals(null,result,"Wrong number of points");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray((new Point3D(-1,0,0)),
                new Vector(-1,0,1)));
        assertEquals(null,result,"Wrong number of points");
    }

}