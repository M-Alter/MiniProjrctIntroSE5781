package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class GeometriesTests {
    @Test
    public void intersectionsTest(){
        // ============ Equivalence Partitions Tests ==============
        var geometries = new Geometries(new Plane(new Point3D(-4, 0, 0), new Point3D(0, 0, 2), new Point3D(0, 0, 0)),
                new Sphere(new Point3D(0, 3, 0), 2),
                new Triangle(new Point3D(0, -2, 4), new Point3D(0, -2, 0), new Point3D(4, -2, 0)));

        // TC01: Some (but not all) shapes are cut
        assertEquals( 4,
                geometries.findIntersections(new Ray(new Point3D(0.5, -5, 0.5), new Vector(0, 1, 0))).size());

        Geometries geometriesEmpty = new Geometries();

        // TC02: Empty body collection
        assertNull(geometriesEmpty.findIntersections(new Ray(new Point3D(2, 4, -3), new Vector(-1, 0, 1))),"is Empty!");

        // TC03: No shape is cut
        assertNull(geometries.findIntersections(new Ray(new Point3D(-1, 0.5, 0), new Vector(1, 0, 0))),"without crossing!");

        // TC04: Only one shape is cut
        assertEquals(2,
                geometries.findIntersections(new Ray(new Point3D(0, 0.5, 0), new Vector(0, 1, 0))).size(),"Only one shape is cut");

        // TC05: All shapes are cut
        assertEquals( 4, geometries.findIntersections(new Ray(new Point3D(0.5, 6, 0.5), new Vector(0, -1, 0))).size(),("Only one shape is cut"));



    }
}