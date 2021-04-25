package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTests {
    /**
     * Testing the normal of a sphere
     */
    @Test
    public void normalTest(){
        Sphere spr = new Sphere(new Point3D(0,0,0),1d);
        assertEquals (new Vector(1,0,0), spr.getNormal(new Point3D(1,0,0)), "Normal is not as expected");
    }
}

