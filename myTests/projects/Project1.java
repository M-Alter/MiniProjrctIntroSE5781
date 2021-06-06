package projects;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

public class Project1 {
    @Test
    public void candleSticks(){
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

    }
}
