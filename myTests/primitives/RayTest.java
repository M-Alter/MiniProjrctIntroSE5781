package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * test to verify the closest point
     */
    @Test
    void findClosestPoint() {
        List<Point3D> points = new LinkedList<Point3D>();
        points.add(new Point3D(0, 0, 1));
        points.add(new Point3D(0, 0, 2));
        points.add(new Point3D(0, 0, 3));
        points.add(new Point3D(0, 0, 4));
        points.add(new Point3D(0, 0, 5));
        points.add(new Point3D(0, 0, 6));
        points.add(new Point3D(0, 0, 7));
        points.add(new Point3D(0, 0, 8));
        assertEquals(new Point3D(0, 0, 1),
                new Ray(new Point3D(0, 0, 0),
                        new Vector(0, 0, 1)).
                        findClosestPoint(points));
    }
}