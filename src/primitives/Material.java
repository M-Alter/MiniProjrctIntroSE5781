package primitives;

/**
 * class material is about to define materials
 */
public class Material {
    public double kD = 0d, kS = 0d;
    public int nShininess = 0;

    /**
     * setter of parameter kD
     * @param kD diffusion of the light
     * @return this
     */
    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter of kS
     * @param kS
     * @return this
     */
    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter of shininess
     * @param nShininess
     * @return this
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
