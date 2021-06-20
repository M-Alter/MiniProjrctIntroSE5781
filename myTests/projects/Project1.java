package projects;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Cylinder;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class Project1 {
    private final Camera camera1 = new Camera(new Point3D(50, 130, 1000), new Vector(0, 1, 0), new Vector(0, 0, -1)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    private final Camera camera2 = new Camera(new Point3D(1000, -1000, 50), new Vector(0, 0, 1), new Point3D(50, 130, 50).subtract(new Point3D(1000, -1000, 50))) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    private final Camera camera3 = new Camera(new Point3D(50, -1000, 50), new Vector(0, 0, 1), new Point3D(50, 130, 50).subtract(new Point3D(51, -1000, 50))) //
            .setViewPlaneSize(200, 200).setDistance(1000);


    @Test
    public void candleSticks() {


        Scene scene = new Scene("CandleSticks")
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), .5));

        scene.geometries.add(
                new Sphere(new Point3D(30, 30, 0), 30)
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200)),
                new Polygon(new Point3D(0, 0, -1),
                        new Point3D(0, 6, -1),
                        new Point3D(12, 6, -1),
                        new Point3D(12, 0, -1))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200)),
                new Polygon(new Point3D(-12, -12, -.0001),
                        new Point3D(-12, 18, -.0001),
                        new Point3D(24, 18, -.0001),
                        new Point3D(24, -12, -.0001))
                        .setMaterial(new Material().setkD(0).setkS(0).setnShininess(0).setkR(0).setkT(0))
                        .setEmission(new Color(java.awt.Color.BLACK)),
                new Cylinder(new Ray(new Point3D(30, 30, 0), new Vector(0, 0, 1)), 20, 10)
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200))
        );
        scene.lights.add(
                new PointLight(new Color(224, 157, 55), new Point3D(3, 3, 10))
                        .setkL(0.00001).setkQ(0.000001)
        );

        scene.lights.add(
                new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(0, 0, 0), new Point3D(3, 3, 1).subtract(new Point3D(0, 0, 0)))
        );


        Render render = new Render()
                .setImageWriter(new ImageWriter("CandleSticks", 400, 400))
                .setCamera(camera1)
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void room() {
        Scene scene = new Scene("Room");
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.background = new Color(java.awt.Color.WHITE);

        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-5));
        scene.lights.add(new PointLight(new Color(968, 500, 50).scale(2), new Point3D(0, 130, 130))
                .setkL(4E-4).setkQ(2E-5));
        scene.lights.add(new PointLight(new Color(968, 500, 50).scale(2), new Point3D(100, 130, 130))
                .setkL(4E-4).setkQ(2E-5));

        scene.geometries.add( //

                new Plane(//floor
                        new Point3D(0, 0, -1),
                        new Vector(0, 0, 1)
                ).setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setkR(.1).setnShininess(100).setkD(.5).setkS(1)),


                new Sphere(new Point3D(0, 130, 130), 10)//light
                        .setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setkT(.1).setkR(0.1))
                        .setEmission(new Color(0, 0, 0)),

                new Sphere(new Point3D(100, 130, 130), 10)//light
                        .setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setkT(.1).setkR(0.1))
                        .setEmission(new Color(0, 0, 0)),


                new Polygon(//front
                        new Point3D(-10, 110, 0),
                        new Point3D(10, 110, 0),
                        new Point3D(10, 110, 130),
                        new Point3D(-10, 110, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//back
                        new Point3D(-10, 150, 0),
                        new Point3D(10, 150, 0),
                        new Point3D(10, 150, 130),
                        new Point3D(-10, 150, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//left
                        new Point3D(-20, 120, 0),
                        new Point3D(-20, 140, 0),
                        new Point3D(-20, 140, 130),
                        new Point3D(-20, 120, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//right
                        new Point3D(20, 120, 0),
                        new Point3D(20, 140, 0),
                        new Point3D(20, 140, 130),
                        new Point3D(20, 120, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//front right
                        new Point3D(10, 110, 0),
                        new Point3D(20, 120, 0),
                        new Point3D(20, 120, 130),
                        new Point3D(10, 110, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//front left
                        new Point3D(-10, 110, 0),
                        new Point3D(-20, 120, 0),
                        new Point3D(-20, 120, 130),
                        new Point3D(-10, 110, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back left
                        new Point3D(-20, 140, 0),
                        new Point3D(-10, 150, 0),
                        new Point3D(-10, 150, 130),
                        new Point3D(-20, 140, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back right
                        new Point3D(20, 140, 0),
                        new Point3D(10, 150, 0),
                        new Point3D(10, 150, 130),
                        new Point3D(20, 140, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//front base
                        new Point3D(-10, 110, 20),
                        new Point3D(10, 110, 20),
                        new Point3D(30, 80, -10),
                        new Point3D(-30, 80, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back base
                        new Point3D(-10, 150, 20),
                        new Point3D(10, 150, 20),
                        new Point3D(30, 180, -10),
                        new Point3D(-30, 180, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//left base
                        new Point3D(-20, 120, 20),
                        new Point3D(-20, 140, 20),
                        new Point3D(-50, 160, -10),
                        new Point3D(-50, 100, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//right base
                        new Point3D(20, 120, 20),
                        new Point3D(20, 140, 20),
                        new Point3D(50, 160, -10),
                        new Point3D(50, 100, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//front right base
                        new Point3D(10, 110, 20),
                        new Point3D(20, 120, 20),
                        new Point3D(50, 100, -10),
                        new Point3D(30, 80, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//front left base
                        new Point3D(-10, 110, 20),
                        new Point3D(-20, 120, 20),
                        new Point3D(-50, 100, -10),
                        new Point3D(-30, 80, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back left base
                        new Point3D(-10, 150, 20),
                        new Point3D(-20, 140, 20),
                        new Point3D(-50, 160, -10),
                        new Point3D(-30, 180, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back right base
                        new Point3D(10, 150, 20),
                        new Point3D(20, 140, 20),
                        new Point3D(50, 160, -10),
                        new Point3D(30, 180, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//front
                        new Point3D(90, 110, 0),
                        new Point3D(110, 110, 0),
                        new Point3D(110, 110, 130),
                        new Point3D(90, 110, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back
                        new Point3D(90, 150, 0),
                        new Point3D(110, 150, 0),
                        new Point3D(110, 150, 130),
                        new Point3D(90, 150, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//left
                        new Point3D(80, 120, 0),
                        new Point3D(80, 140, 0),
                        new Point3D(80, 140, 130),
                        new Point3D(80, 120, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//right
                        new Point3D(120, 120, 0),
                        new Point3D(120, 140, 0),
                        new Point3D(120, 140, 130),
                        new Point3D(120, 120, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back left
                        new Point3D(110, 110, 0),
                        new Point3D(120, 120, 0),
                        new Point3D(120, 120, 130),
                        new Point3D(110, 110, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//front left
                        new Point3D(90, 110, 0),
                        new Point3D(80, 120, 0),
                        new Point3D(80, 120, 130),
                        new Point3D(90, 110, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//front right
                        new Point3D(80, 140, 0),
                        new Point3D(90, 150, 0),
                        new Point3D(90, 150, 130),
                        new Point3D(80, 140, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back right
                        new Point3D(120, 140, 0),
                        new Point3D(110, 150, 0),
                        new Point3D(110, 150, 130),
                        new Point3D(120, 140, 130))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//front base
                        new Point3D(90, 110, 20),
                        new Point3D(110, 110, 20),
                        new Point3D(130, 80, -10),
                        new Point3D(70, 80, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back base
                        new Point3D(90, 150, 20),
                        new Point3D(110, 150, 20),
                        new Point3D(130, 180, -10),
                        new Point3D(70, 180, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//left base
                        new Point3D(80, 120, 20),
                        new Point3D(80, 140, 20),
                        new Point3D(50, 160, -10),
                        new Point3D(50, 100, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//right base
                        new Point3D(120, 120, 20),
                        new Point3D(120, 140, 20),
                        new Point3D(150, 160, -10),
                        new Point3D(150, 100, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Polygon(//front right base
                        new Point3D(110, 110, 20),
                        new Point3D(120, 120, 20),
                        new Point3D(150, 100, -10),
                        new Point3D(130, 80, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//front left base
                        new Point3D(90, 110, 20),
                        new Point3D(80, 120, 20),
                        new Point3D(50, 100, -10),
                        new Point3D(70, 80, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back left base
                        new Point3D(90, 150, 20),
                        new Point3D(80, 140, 20),
                        new Point3D(50, 160, -10),
                        new Point3D(70, 180, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),

                new Polygon(//back right base
                        new Point3D(110, 150, 20),
                        new Point3D(120, 140, 20),
                        new Point3D(150, 160, -10),
                        new Point3D(130, 180, -10))
                        .setMaterial(new Material().setkD(0.4).setkS(.9).setnShininess(50).setkR(.7).setkT(0))
                        .setEmission(new Color(197, 198, 200).scale(.5)),


                new Plane(//back wall / mirror
                        new Point3D(0, 250, 0),
                        new Point3D(250, 250, 0),
                        new Point3D(0, 250, 250)
                ).setMaterial(new Material().setkD(.3).setkS(.3).setkR(.5)),


                new Plane(//left wall / mirror
                        new Point3D(-150, 0, 0),
                        new Point3D(-150, 250, 0),
                        new Point3D(-150, 250, 250)
                ).setMaterial(new Material().setkD(.3).setkS(.3).setkR(.5)),


                new Plane(//right wall / mirror
                        new Point3D(250, 0, 0),
                        new Point3D(250, 250, 0),
                        new Point3D(250, 250, 250)
                ).setMaterial(new Material().setkD(.3).setkS(.3).setkR(.5))

        );


        Render render = new Render() //
                .setImageWriter(new ImageWriter("Room camera1", 600, 600)) //
                .setCamera(camera1) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

        render = new Render() //
                .setImageWriter(new ImageWriter("Room camera2", 600, 600)) //
                .setCamera(camera2) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();


        render = new Render() //
                .setImageWriter(new ImageWriter("Room camera3", 200, 200)) //
                .setCamera(camera3) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

    }

}
