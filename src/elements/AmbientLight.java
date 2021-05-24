package elements;

import primitives.Color;

/**
 * class that controls lighting and coloring
 */
public class AmbientLight extends Light{


    /**
     * c'tor of ambientLight
     * @param intensity color
     * @param ka scale the color
     */
    public AmbientLight(Color intensity, double ka) {
        super(intensity.scale(ka));
    }

    /**
     * c'tor for the ambient light with a default of black
     */
    public AmbientLight() {
       super(Color.BLACK);
    }

}
