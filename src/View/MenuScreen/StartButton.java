package View.MenuScreen;

import Controller.GameController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class StartButton extends Rectangle{

    static final String startUrl = "Images/premenu-start-game.png";

    public StartButton() {
        super(200, 50);
        super.setFill(new ImagePattern(new Image(startUrl)));
        super.setId("pixelbutton");
    }

    public void setController(GameController controller) {
        super.setOnMouseClicked(e -> {
            controller.beginGame();
        });
    }

}
