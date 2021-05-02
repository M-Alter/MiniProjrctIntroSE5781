package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point3D;


class TriangleTest {
    @Test
    public void evTests() {
        Triangle triangle = new Triangle(new Point3D(1, 0, 1), new Point3D(0, 1, 1), new Point3D(1, 1, 1));
        //===================================ep tests==============================================
        //tc01 point is in the triangle
        List<Point3D> result = triangle.findIntersections(new Ray(new Point3D(0.5, 0.5, 0.5), new Vector(0.75, 0.75, 1)));
        assertEquals(1, result.size(), "this ray has 1 intersection with the triangle");

        //tc02 ray is out side of the triangle but in it's angle
        result = triangle.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(-.5, 1.25, 1)));
        assertNull(result,"ray doesn't intersect with the triangle");

        //tc03 ray is outside of the triangle
        result = triangle.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(0.5, 1.5, 1)));
        assertNull(result,"ray doesn't intersect with the triangle");

        //===============================bva tests==============================================
        //tc04 ray meets on the edge of the triangle
        result = triangle.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 1)));
        assertNull(result,"ray meets on the edge of hte triangle and doesn't intersect with the triangle");

        //tc05  ray meets on the side of the triangle
        result = triangle.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0.5, 1)));
        assertNull(result,"ray meets on the side of the triangle and doesn't intersect with the triangle");

        //tc06  ray meets the continue of the vertex
        result = triangle.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 1.5, 1)));
    }
}