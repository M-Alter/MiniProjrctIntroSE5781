package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneTests {
    @Test
    /**
     * Testing the nomal of a plane
     */
    public void normalTest(){
        Plane pl = new Plane(new Point3D(1,0,0), new Point3D(0,1,0),
                new Point3D(0,0,1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)), "Bad normal to trinagle");
    }
}
