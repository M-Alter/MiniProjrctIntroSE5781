package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.*;

class RayTracerDOFTest {
    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 1, 0), new Vector(0, 0, -1)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    private Camera camera2 = new Camera(new Point3D(1000, -1000, 50), new Vector(0, 0, 1), new Point3D(50, 130, 50).subtract(new Point3D(1000, -1000, 50))) //
            .setViewPlaneSize(200, 200).setDistance(1000);


    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15))
                .setFocalLength(250)
                .setAperture(2d);

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Sphere(new Point3D(0, 0, -115), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-5));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("shadowTrianglesSphereDOF", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerDOF(scene));
        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void room() {
        Scene scene = new Scene("Room").setFocalLength(750).setAperture(1.5d);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.background = new Color(java.awt.Color.DARK_GRAY);

        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-5));
        scene.lights.add(new PointLight(new Color(968, 500, 50).scale(2), new Point3D(0, 130, 130))
                .setkL(4E-4).setkQ(2E-5));
        scene.lights.add(new PointLight(new Color(968, 500, 50).scale(2), new Point3D(100, 130, 130))
                .setkL(4E-4).setkQ(2E-5));

        scene.geometries.add( //

//                new Polygon(//Tray
//                        new Point3D(-20,80,0),
//                        new Point3D(100,80,0),
//                        new Point3D(100,190,0),
//                        new Point3D(-20,190,0))
//                        .setMaterial(new Material().setkT(0).setkD(0).setkS(0).setkR(0).setnShininess(0))
//                        .setEmission(new Color(197,198,200)),

                new Plane(//floor
                        new Point3D(0, 0, -1),
                        new Vector(0, 0, 1)
                ).setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setkR(.1).setnShininess(100).setkD(.5).setkS(1)),

//
//                new Sphere(new Point3D(-500, 500, 50), 10)//DOF test
//                        .setMaterial(new Material().setkD(1).setkS(1).setnShininess(1).setkT(0).setkR(0))
//                        .setEmission(new Color(0, 0, 0)),


                new Sphere(new Point3D(0, 130, 130), 10)//light
                        .setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setkT(.1).setkR(0.1))
                        .setEmission(new Color(0, 0, 0)),

//                new Sphere(new Point3D(100, 130, 130), 10)//light
//                        .setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setkT(.1).setkR(0.1))
//                        .setEmission(new Color(0, 0, 0)),


//                new Sphere( new Point3D(0, 130, -10),40) //
//                        //.setEmission(new Color(java.awt.Color.BLUE)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)), //

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


//
//                new Sphere( new Point3D(100, 130, -10),40) //
//                        //.setEmission(new Color(java.awt.Color.BLUE)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)), //


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
                        .setEmission(new Color(197, 198, 200).scale(.5))


//
        );


        Render render = new Render() //
                .setImageWriter(new ImageWriter("Room camera2 DOF", 600, 600)) //
                .setCamera(camera2) //
                .setRayTracer(new RayTracerDOF(scene));
        render.renderImage();
        render.writeToImage();

    }


    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 1, 0), new Vector(0, 0, -1)) //
                .setViewPlaneSize(150, 150).setDistance(1000);

        scene.setAperture(1.5d).setFocalLength(300);

        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -50), 50) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
                new Sphere(new Point3D(0, 0, -50), 25) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setkL(0.0004).setkQ(0.0000006));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("refractionTwoSpheresDOF", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerDOF(scene));
        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void dofTest() {
        scene.geometries.add(

                new Sphere(new Point3D(0, 0, -1000), 50)
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3))
        );

        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setkL(0.0004).setkQ(0.0000006));

        scene.setFocalLength(1500).setAperture(30);

        Render render = new Render() //
                .setImageWriter(new ImageWriter("DOFTest", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerDOF(scene));
        render.renderImage();
        render.writeToImage();
    }
}