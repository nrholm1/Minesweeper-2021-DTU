package View.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

//Skrevet af s204508 Massimo Hansen
public class PixelButton extends Rectangle {
    public PixelButton(Image pic, int width, int height) {
        super(width, height);
        setFill(new ImagePattern(pic));
        setId("pixelbutton");
    }
}
