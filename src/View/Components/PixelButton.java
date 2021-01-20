package View.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PixelButton extends Rectangle {
    public PixelButton(Image pic, int width, int height) {
        super(width, height);
        setFill(new ImagePattern(pic));
        setId("pixelbutton");
    }
}
