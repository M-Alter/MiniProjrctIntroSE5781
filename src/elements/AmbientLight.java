package elements;

import primitives.Color;

/**
 * class that controls lighting and coloring
 */
public class AmbientLight {
    /**
     * color
     */
    private Color _intensity;

    /**
     * c'tor of ambientLight
     * @param intensity color
     * @param ka scale the color
     */
    public AmbientLight(Color intensity, double ka) {
        this._intensity = intensity;
        _intensity.scale(ka);
    }

    public AmbientLight() {
        _intensity = Color.BLACK;
    }

    public Color getIntensity() {
        return _intensity;
    }
}
