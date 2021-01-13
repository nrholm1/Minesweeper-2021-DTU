package sample.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sample.Main;

public class PixelButton extends Rectangle{

    public PixelButton(String picURL, int width, int height, String dest) {
        super(width, height);
        setFill(new ImagePattern(new Image(picURL)));
        setId("pixelbutton");

        setOnMouseClicked(e -> {
            Main.goToScene(dest);
        });
    }
}
