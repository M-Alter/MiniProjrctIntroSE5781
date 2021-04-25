package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneTests {

    /**
     * Testing the normal of a plane
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
     */

    @Test
    public void alignmentTest() {
        try {
            Plane plane = new Plane(
                    new Point3D(1, 0, 0),
                    new Point3D(2, 0, 0),
                    new Point3D(3, 0, 0));
            fail("You should have received an error");
        } catch (IllegalArgumentException e) {
        }
        try {
            Plane plane = new Plane(
                    new Point3D(1, 0, 0),
                    new Point3D(1, 0, 0),
                    new Point3D(3, 3, 3));
            fail("You should have received an error");
        }
        catch (IllegalArgumentException e) {
        }

    }

}
