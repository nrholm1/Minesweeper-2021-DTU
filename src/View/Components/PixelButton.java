package View.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PixelButton extends Rectangle {
    public PixelButton(String picURL, int width, int height) {
        super(width, height);
        setFill(new ImagePattern(new Image(picURL)));
        setId("pixelbutton");

        setOnMouseClicked(e -> {
            System.out.println("Button pressed");
        });
    }
}
