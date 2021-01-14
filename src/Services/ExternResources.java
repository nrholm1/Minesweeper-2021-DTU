package Services;

import Model.Field;
import View.MainMenuScreen.MainMenuView2;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

// This class holds all of the extern resources, so the program wont need to load new files every time they're needed
public abstract class ExternResources {
  public static String pixelFontResource;
  public static Image topmenuNewGame;
  public static Image topMenuTitle;
  public static Image submarine;

  public static void createResources() {
    //pixelFontResource = this.getClass().getResource("../View/PressStart2P-Regular.ttf").toExternalForm();
    topmenuNewGame = new Image("Images/pre-new-game.png");
    topMenuTitle = new Image("Images/premenu-title.png");
    submarine = new Image("Images/image_submarine.gif");

    pixelFontResource = ExternResources.class.getResource("../View/PressStart2P-Regular.ttf").toExternalForm();
  }
}
