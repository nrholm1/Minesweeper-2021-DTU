package Services;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.InputStream;

// This class holds all of the external resources, so the program wont need to load new files every time they're needed
public abstract class ExternalResources {
  public static Font pixelFont10;
  public static Font pixelFont16;

  public static String gamestyleSheet;
  public static String victoryStyleSheet;
  public static String multiplayerStyleSheet;
  public static String multiplayerMenuStyleSheet;

  public static Image menuTitle;
  public static Image singeplayerText;
  public static Image multiplayerText;

  public static Image startgameText;
  public static Image backButton;

  public static Image topmenuNewGame;
  public static Image topMenuTitle;
  public static Image submarine;

  public static void createResources() {
    topmenuNewGame = new Image("Images/pre-new-game.png");
    topMenuTitle = new Image("Images/premenu-title.png");
    submarine = new Image("Images/image_submarine.gif");

    menuTitle = new Image("Images/premenu-title.png");
    singeplayerText = new Image("Images/pre-single-game.png");
    multiplayerText = new Image("Images/pre-multi-game.png");

    startgameText = new Image("Images/pre-start-game.png");
    backButton = new Image("Images/back-icon.png");

    gamestyleSheet = ExternalResources.class.getResource("../View/GameScreen/Util/GameStyles.css").toExternalForm();
    victoryStyleSheet = ExternalResources.class.getResource("../View/VictoryScreen/VictoryStyles.css").toExternalForm();
    multiplayerStyleSheet = ExternalResources.class.getResource("../View/MultiPlayerGameScreen/MultiStyles.css").toExternalForm();
    multiplayerMenuStyleSheet = ExternalResources.class.getResource("../View/MultiPlayerMenu/MultiStyles.css").toExternalForm();

    pixelFont10 = Font.loadFont(getPixelFontResourceStream(), 10);
    pixelFont16 = Font.loadFont(getPixelFontResourceStream(), 16);
  }

  // make new inputStream each time - workaround for space chars ' ' in font path
  public static InputStream getPixelFontResourceStream() {
    return ExternalResources.class.getResourceAsStream("../View/PressStart2P-Regular.ttf");
  }
}
