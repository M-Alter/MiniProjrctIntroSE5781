package projects;

import elements.Camera;
import elements.PointLight;
import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class Project1 {
    @Test
    public void candleSticks(){
        Camera camera = new Camera(
                new Point3D(0, -50, 15),
                new Vector(0,1,0),
                new Vector(0,0,1))
                .setViewPlaneSize(150,150).setDistance(1000);

        Scene scene = new Scene("CandleSticks");

        scene.geometries.add(
        new Sphere(new Point3D(2,2,0),5)
                .setMaterial(new Material().setkS(1).setkD(1).setnShininess(100).setkT(0).setkR(1))
                .setEmission(new Color(192,192,192)),
        new Polygon(new Point3D(0,0,0),
                new Point3D(0,6,0),
                new Point3D(12,6,0),
                new Point3D(12,0,0))
                .setMaterial(new Material().setkS(1).setkD(1).setnShininess(100).setkT(0).setkR(1))
                .setEmission(new Color(192,192,192)),
        new Cylinder(new Ray(new Point3D(0,0,0), new Vector(0,0,1)),1,10)
                .setMaterial(new Material().setkS(1).setkD(1).setnShininess(100).setkT(0).setkR(1))
                .setEmission(new Color(192,192,192))
        );
        scene.lights.add(
                new PointLight(new Color(224,157,55),new Point3D(3,3,10))
        );

        Render render = new Render()
                .setImageWriter(new ImageWriter ("CandleSticks",500,500))
                .setCamera(camera)
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

    }
}
