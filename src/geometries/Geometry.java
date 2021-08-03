package geometries;

import primitives.*;

/**
 * abstract class to represent geometry body
 */
public abstract class Geometry extends Intersectable {
    /**
     * emission field of the color of the body
     */
    protected Color _emission = Color.BLACK;
    /**
     * material field of the body
     */
    private Material _material = new Material();

    /**
     * abstract method getNormal
     * @param pnt point to calculation
     * @return the normal between the body to the point
     */
    public abstract Vector getNormal(Point3D pnt);

    /**
     * setter of material
     * @param material to set
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
     * @param emission the color to set
     * @return this object
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }
}
