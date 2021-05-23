package renderer;

import elements.Camera;
import primitives.Color;

import java.util.MissingResourceException;

public class Render {
    // parameters
    private Camera _camera;
    private ImageWriter _imageWriter;
    private RayTracerBase _rayTracer;

    /**
     * setter for camera
     * @param _camera camera to set
     * @return this object (similar to builder)
     */
    public Render setCamera(Camera _camera) {
        this._camera = _camera;
        return this;
    }

    /**
     * setter for imageWriter
     * @param _imageWriter the image writer to use
     * @return this object (similar to builder)
     */
    public Render setImageWriter(ImageWriter _imageWriter) {
        this._imageWriter = _imageWriter;
        return this;
    }

    /**
     * setter for rayTracer
     * @param _rayTracer ray tracer to use
     * @return this object (similar to builder)
     */
    public Render setRayTracer(RayTracerBase _rayTracer) {
        this._rayTracer = _rayTracer;
        return this;
    }

    /**
     * method to test if there are some objects that they null
     */
    public void renderImage(){
        // verify the camera fields are not null
        if (_camera == null) {
            throw new MissingResourceException("camera can't be null","Render","Camera");
        }

        //verify the image writer fields is not null
        if (_imageWriter == null) {
            throw new MissingResourceException("imageWriter can't be null","Render","imageWriter");
        }
        //verify the ray tracer fields is not null
        if (_rayTracer == null) {
            throw new MissingResourceException("rayTracer can't be null","Render","rayTracer");
        }


        for (int i = 0; i < _imageWriter.getNx(); i++){
            for (int j = 0; j < _imageWriter.getNy(); j++){
                _imageWriter.writePixel(j,i,_rayTracer.traceRay(_camera.constructRay(_imageWriter.getNx(), _imageWriter.getNy(),j,i)));
            }
        }
    }

    /**
     * method to print the grid
     * @param interval interval of grids
     * @param color the color to print with
     */
    public void printGrid(int interval, Color color){
        //verify the image writer fields is not null
        if (_imageWriter == null) {
            throw new MissingResourceException("imageWriter can't be null","Render","imageWriter");
        }

        for (int i = 0; i < _imageWriter.getNx(); i++){
            for (int j = 0; j < _imageWriter.getNy(); j += interval){
                _imageWriter.writePixel(i,j, color);
            }
        }

        for (int i = 0; i < _imageWriter.getNy(); i+= interval){
            for (int j = 0; j < _imageWriter.getNx(); j ++){
                _imageWriter.writePixel(i,j,color);
            }
        }
    }

    /**
     * method to create the image
     */
    public void writeToImage(){
        //verify the image writer fields is not null
        if (_imageWriter == null) {
            throw new MissingResourceException("imageWriter can't be null","Render","imageWriter");
        }
        _imageWriter.writeToImage();
    }
}
