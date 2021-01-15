package Services;

import javafx.scene.image.Image;

// This class holds all of the extern resources, so the program wont need to load new files every time they're needed
public abstract class ExternalResources {
  public static String pixelFontResource;
  public static String gamestyleSheet;

  public static Image menuTitle;
  public static Image singeplayerText;
  public static Image multiplayerText;

  public static Image startgameText;
  public static Image backButton;

  public static Image topmenuNewGame;
  public static Image topMenuTitle;
  public static Image submarine;



  public static void createResources() {
    //pixelFontResource = this.getClass().getResource("../View/PressStart2P-Regular.ttf").toExternalForm();
    topmenuNewGame = new Image("Images/pre-new-game.png");
    topMenuTitle = new Image("Images/premenu-title.png");
    submarine = new Image("Images/image_submarine.gif");

    menuTitle = new Image("Images/premenu-title.png");
    singeplayerText = new Image("Images/pre-single-game.png");
    multiplayerText = new Image("Images/pre-multi-game.png");

    startgameText = new Image("Images/pre-start-game.png");
    backButton = new Image("Images/back-icon.png");

    pixelFontResource = ExternalResources.class.getResource("../View/PressStart2P-Regular.ttf").toExternalForm();
    gamestyleSheet = ExternalResources.class.getResource("../View/GameScreen/Util/GameStyles.css").toExternalForm();
  }
}
