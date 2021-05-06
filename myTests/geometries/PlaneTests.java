package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneTests {

    /**
     * Testing the normal of a plane
     * {@link Plane#getNormal()}
     */
    @Test
    public void normalTest() {
        Plane pl = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(0, 0, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)), "Bad normal to trinagle");
    }

    /**
     * Verify there is no a plane with 2 same points or 3 points that created a vector
     * {@link geometries.Plane#Plane(Point3D, Point3D, Point3D)}
     */

    @Test
    public void alignmentTest() {
        try {
            Plane plane = new Plane(
                    new Point3D(1, 0, 0),
                    new Point3D(2, 0, 0),
                    new Point3D(3, 0, 0));
            fail("You should have received an error");
        } catch (IllegalArgumentException e){
            /* no nothing */}
        try {
            Plane plane = new Plane(
                    new Point3D(1, 0, 0),
                    new Point3D(1, 0, 0),
                    new Point3D(3, 3, 3));
            fail("You should have received an error");
        } catch (IllegalArgumentException e){
            /* no nothing */}

    }

    /**
     * test for find intersections
     * {@link geometries.Plane#findIntersections(Ray)}
     */
    @Test
    public void intersectionsTest() {
        Plane plane = new Plane(new Point3D(1,1,1), new Vector(0,0,-1));
        //ev tests
        //tc01 intersects the plane
        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(0,0,2), new Vector(-1,-1,-1)));
        assertEquals(1, result.size(),"this plane has 1 intersection with the ray");

        //tc02 doesn't intersect the plane
        result = plane.findIntersections(new Ray(new Point3D(0,0,1), new Vector(-1,-1,-1)));
        assertNull(result,"this ray shouldn't intersect with the plane");

        //=======================bva tests===========================
        //tc03 parallel and not included in the plane
        result = plane.findIntersections(new Ray(new Point3D(0,0,3), new Vector(1,1,3)));
        assertNull(result,"this ray is parallel with the plane");

        //tc04 parallel and included in the plane
        result = plane.findIntersections(new Ray(new Point3D(0,0,1), new Vector(1,1,1)));
        assertNull(result,"this ray is included in the plane");

        //tc05 orthogonal and crosses the plane
        result = plane.findIntersections(new Ray(new Point3D(3,3,3), new Vector(3,3,0)));
        assertNull(result,"this ray is orthogonal to the plane and crosses the plane");

        //tc06 orthogonal and start in the plane
        result = plane.findIntersections(new Ray(new Point3D(3,3,1), new Vector(3,3,0)));
        assertNull(result,"this ray is orthogonal to the plane and starts in the plane");

        //tc07 orthogonal and starts after the plane
        result = plane.findIntersections(new Ray(new Point3D(3,3,-3), new Vector(3,3,-6)));
        assertNull(result,"this ray is orthogonal to the plane and starts after the plane");

        //tc08 ray begins in the plane but isn't orthogonal nor parallel
        result = plane.findIntersections(new Ray(new Point3D(5,4,1), new Vector(9,8,7)));
        assertNull(result,"this ray isn't orthogonal nor parallel but starts in the plane");

        //tc09 prevent zero vector when p0 is reference point of the plane
        result = plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(9,8,7)));
        assertNull(result,"this ray isn't orthogonal nor parallel but starts at the reference point of the plane");

    }

}
