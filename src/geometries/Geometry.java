package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {
    protected Color _emission = Color.BLACK;

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    private Material _material;

    /**
     * getter of material
     * @return _material
     */
    public Material getMaterial() {
        return _material;
    }

    public abstract Vector getNormal(Point3D pnt);

    /**
     * Getter of _emission
     * @return the field of emission
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * Setter of _emission
     * @param emission
     * @return this object
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }
}
