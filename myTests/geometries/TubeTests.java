package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class TubeTests {
    /**
     * Testing the normal of a tube
     * {@link geometries.Tube#getNormal(Point3D)}
     */
    @Test
    public void normalTest() {
        Tube tube = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0)), 1d);

        //======================= Equivalence Partitions Tests =========================
        assertEquals(new Vector(0, 1, 0), tube.getNormal(new Point3D(1, 2, 0)), "not the expected normal");


        // =============== Boundary Values Tests ==================
        try {
            tube.getNormal(new Point3D(0, 2, 0));
            fail("normal dot-product direction should create a zero vector");
        }
        catch (IllegalArgumentException e){
            /* no nothing */}
    }

}
