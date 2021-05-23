package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0);
    public Geometries geometries;

    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }


    public Scene setBackground(Color _background) {
        this.background = _background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight _ambientLight) {
        this.ambientLight = _ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries _geometries) {
        this.geometries = _geometries;
        return this;
    }

    //    public static class Builder { // in the next slide
//            private Scene scene = null;
//            public Builder(String name) { scene = new Scene(name); }
//            public void reset(String name) { scene = new Scene(name); }
//            public Builder setBackground(Color color) { scene._background = color; return this; }
//            public Builder setAmbient(Color color, double ka) {
//                scene._ambient = new AmbientLight(color, ka); return this;
//            }
//            public Builder addGeometry(Intersectable geo) {
//                if (scene._geometries == null) scene._geometries = new Geometries();
//                scene._geometries.add(geo);
//                return this;
//            }
//            public Scene build() {
//                if (scene._name == null || scene._geometries == null) throw new SomeException();
//                return scene;
//            }
//        }
}
