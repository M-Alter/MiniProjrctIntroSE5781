package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
    @Test
    public void imageTest(){
        ImageWriter imageWriter = new ImageWriter("FirstTest",800,500);
        for (int i = 0; i < 800; i++){
            for (int j = 0; j < 500; j++){
                imageWriter.writePixel(i, j , new Color(132, 222, 2));
            }
        }
        for (int i = 0; i < 800; i += 50){
            for (int j = 0; j < 500; j++) {
                imageWriter.writePixel(i, j, Color.BLACK);
            }
        }

        for (int i = 0; i < 800; i++){
            for (int j = 0; j < 500; j += 50) {
                imageWriter.writePixel(i, j, Color.BLACK);
            }
        }

        imageWriter.writeToImage();
    }

}