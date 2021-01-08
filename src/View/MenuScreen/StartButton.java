package View.MenuScreen;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sample.Main;

public class StartButton {

    static final String startUrl = "images/premenu-start-game.png";
    static Rectangle startbutton;

    public StartButton() {
        startbutton = new Rectangle(200, 50);
        startbutton.setFill(new ImagePattern(new Image(startUrl)));
        startbutton.setId("pixelbutton");

        startbutton.setOnMouseClicked(e -> {
            Main.collectGameScene();
            Main.goToScene("game");
        });
    }

    public Rectangle visual() {
        return startbutton;
    }
}
