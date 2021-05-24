package elements;

import primitives.Color;

/**
 * abstract Light class that represented all kinds of lights
 */
abstract class Light {
    private Color _intensity;

    /**
     * getter of parameter intensity
     * @return _intensity
     */
    public Color getIntensity() {
        return _intensity;
    }

    /**
     * constructor of Light
     * @param intensity
     */
    protected Light(Color intensity) {
        _intensity = intensity;
    }

}
