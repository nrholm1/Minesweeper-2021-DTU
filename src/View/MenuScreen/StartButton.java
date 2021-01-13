package View.MenuScreen;

import Controller.GameController;
import Controller.MainMenuController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class StartButton extends Rectangle{
    static final String startUrl = "Images/pre-start-game.png";

    public StartButton() {
        super(200, 50);
        super.setFill(new ImagePattern(new Image(startUrl)));
        super.setId("pixelbutton");
    }

    public void setController(MainMenuController controller) {
        super.setOnMouseClicked(e -> {
            System.out.println("Start game");
            controller.beginSingleplayerGame();
        });
    }

}
