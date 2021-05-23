package primitives;

import geometries.Intersectable;
import geometries.Plane;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * test to verify the closest point
     * {@link primitives.Ray#findClosestPoint(List)}
     */
    @Test
    void findClosestPoint() {
        //============================ EP tests =====================
        //TC01
        List<Point3D> points = new LinkedList<Point3D>();
        points.add(new Point3D(0, 0, 2));
        points.add(new Point3D(0, 0, 3));
        points.add(new Point3D(0, 0, 4));
        points.add(new Point3D(0, 0, 1));
        points.add(new Point3D(0, 0, 5));
        points.add(new Point3D(0, 0, 6));
        points.add(new Point3D(0, 0, 7));
        points.add(new Point3D(0, 0, 8));

        assertEquals(new Point3D(0, 0, 1),
                new Ray(new Point3D(0, 0, 0),
                        new Vector(0, 0, 1)).
                        findClosestPoint(points),"(0,0,0) is the closest point");

        //============================ BV tests =====================
        //TC02
        points= new LinkedList<>();
        assertNull(new Ray(new Point3D(0, 0, 0),
                new Vector(0, 0, 1)).
                findClosestPoint(points),"points is an empty list");

        //TC03
        points = new LinkedList<Point3D>();
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
                        findClosestPoint(points),"(0,0,0) is the closest point");

        //TC03
        points = new LinkedList<Point3D>();
        points.add(new Point3D(0, 0, 2));
        points.add(new Point3D(0, 0, 3));
        points.add(new Point3D(0, 0, 4));
        points.add(new Point3D(0, 0, 5));
        points.add(new Point3D(0, 0, 6));
        points.add(new Point3D(0, 0, 7));
        points.add(new Point3D(0, 0, 8));
        points.add(new Point3D(0, 0, 1));

        assertEquals(new Point3D(0, 0, 1),
                new Ray(new Point3D(0, 0, 0),
                        new Vector(0, 0, 1)).
                        findClosestPoint(points),"(0,0,0) is the closest point");
    }

    /**
     * test to verify the closest geo point
     * {@link primitives.Ray#findClosestPoint(List)}
     */
    @Test
    void findClosestGeoPoint() {
        //============================ EP tests =====================
        //TC01
        List<GeoPoint> points = new LinkedList<GeoPoint>();
        Plane plane = new Plane(new Point3D(0,0,2), new Point3D(0,0,1), new Point3D(0,1,0));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 2)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 3)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 4)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 1)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 5)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 6)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 7)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 8)));

        assertEquals(new Point3D(0, 0, 1),
                new Ray(new Point3D(0, 0, 0),
                        new Vector(0, 0, 1)).
                        findClosestGeoPoint(points).point3D,"(0,0,1) is the closest point");

        //============================ BV tests =====================
        //TC02
        points= new LinkedList<>();
        assertNull(new Ray(new Point3D(0, 0, 0),
                new Vector(0, 0, 1)).
                findClosestGeoPoint(points),"points is an empty list");

        //TC03
        points = new LinkedList<>();
        points.add(new GeoPoint(plane, new Point3D(0, 0, 1)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 2)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 3)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 4)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 5)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 6)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 7)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 8)));

        assertEquals(new Point3D(0, 0, 1),
                new Ray(new Point3D(0, 0, 0),
                        new Vector(0, 0, 1)).
                        findClosestGeoPoint(points).point3D,"(0,0,0) is the closest point");

        //TC03
        points = new LinkedList<>();
        points.add(new GeoPoint(plane, new Point3D(0, 0, 2)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 3)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 4)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 5)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 6)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 7)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 8)));
        points.add(new GeoPoint(plane, new Point3D(0, 0, 1)));

        assertEquals(new Point3D(0, 0, 1),
                new Ray(new Point3D(0, 0, 0),
                        new Vector(0, 0, 1)).
                        findClosestGeoPoint(points).point3D,"(0,0,0) is the closest point");
    }
}