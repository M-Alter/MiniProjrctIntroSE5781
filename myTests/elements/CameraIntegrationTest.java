package elements;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CameraIntegrationTest {

    @Test
    public void sphereTests(){
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        List<Point3D> result;
        List<Point3D> temp;
        //TC01
        Sphere sphere = new Sphere(new Point3D(0,0,3),1);
        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = sphere.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(2,result.size(),"wrong size, size should be 2");
        //TC02
        camera = new Camera(new Point3D(0,0,-0.5), new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        sphere = new Sphere(new Point3D(0,0,2.5),2.5);
        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = sphere.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(18,result.size(),"wrong size, size should be 18");

        //TC03
        camera = new Camera(new Point3D(0,0,-0.5), new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        sphere = new Sphere(new Point3D(0,0,2),2);
        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = sphere.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(10,result.size(),"wrong size, size should be 10");
        //TC04
        camera = new Camera(new Point3D(0,0,-0.5), new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        sphere = new Sphere(new Point3D(0,0,0),4);
        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = sphere.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(9,result.size(),"wrong size, size should be 9");

        //TC05
        camera = new Camera(new Point3D(0,0,0), new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        sphere = new Sphere(new Point3D(0,0,-1),1);
        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = sphere.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(0,result.size(),"wrong size, size should be 0");
    }

    @Test
    public void planeTests(){
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        List<Point3D> result;
        List<Point3D> temp;
        //TC01
        Plane plane = new Plane(new Point3D(0,0,3),new Vector(0,0,-1));

        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = plane.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(9,result.size(),"wrong size, size should be 9");

        //TC02
        plane = new Plane(new Point3D(0,0,2),new Point3D(1,2,3),new Point3D(-1,2,3));

        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = plane.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(9,result.size(),"wrong size, size should be 9");

        //TC03
        plane = new Plane(new Point3D(0,0,3),new Point3D(1,1,4),new Point3D(-1,1,4));

        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = plane.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(6,result.size(),"wrong size, size should be 6");
    }

    @Test
    public void triangleTests(){
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        List<Point3D> result;
        List<Point3D> temp;
        //TC01
        Triangle triangle = new Triangle(
                new Point3D(0,1,2),
                new Point3D(1,-1,2),
                new Point3D(-1,-1,2)
        );

        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = triangle.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(1,result.size(),"wrong size, size should be 1");

        //TC02
        triangle = new Triangle(
                new Point3D(0,20,2),
                new Point3D(1,-1,2),
                new Point3D(-1,-1,2)
        );

        result = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                temp = triangle.findIntersections(camera.constructRay(3,3,j,i));
                if (temp!=null) result.addAll(temp);
            }
        }
        assertEquals(2,result.size(),"wrong size, size should be 2");
    }
}
