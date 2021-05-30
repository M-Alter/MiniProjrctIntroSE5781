package geometries;

import primitives.*;

/**
 * abstract class to represent geometry body
 */
public abstract class Geometry implements Intersectable {
    /**
     * emission field
     */
    protected Color _emission = Color.BLACK;
    /**
     * material field
     */
    private Material _material = new Material();

    /**
     * abstract method getNormal
     * @param pnt point to calculation
     * @return
     */
    public abstract Vector getNormal(Point3D pnt);

    /**
     * setter of material
     * @param material
     * @return this
     */
    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     * getter of material
     * @return _material
     */
    public Material getMaterial() {
        return _material;
    }

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
