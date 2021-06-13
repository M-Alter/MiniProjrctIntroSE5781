package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
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


    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15))
                .setFocalLength(700)
        .setAperture(2.5);

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Sphere( new Point3D(0, 0, -115),30) //
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

}