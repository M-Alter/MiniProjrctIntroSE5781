package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * class that represents a scene that contains a camera, lights and geometric shapes
 */
public class Scene {
    //name of the scene
    public String name;
    //color of hte background
    public Color background = Color.BLACK;
    //ambient light of the scene
    public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0);
    //geometric shapes in the scene
    public Geometries geometries;

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    // list of lights of the scene
    public List<LightSource> lights = new LinkedList<LightSource>();

    /**
     * c'tor of the scene
     * @param name name to call the scene
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    /**
     * set the color of hte background
     * @param _background color to set the background to
     * @return this object (similar to the builder design pattern)
     */
    public Scene setBackground(Color _background) {
        this.background = _background;
        return this;
    }

    /**
     * set the ambient light of the scene
     * @param _ambientLight the ambient light ot set the scene
     * @return this object (similar to the builder design pattern)
     */
    public Scene setAmbientLight(AmbientLight _ambientLight) {
        this.ambientLight = _ambientLight;
        return this;
    }

    /**
     * set the geometries in the scene
     * @param _geometries geometries in the scene
     * @return this object (similar to the builder design pattern)
     */
    public Scene setGeometries(Geometries _geometries) {
        this.geometries = _geometries;
        return this;
    }


}
