package Services;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.InputStream;

// By Magnus Meyer, s204509
// This class holds all of the external resources, so the program wont need to load new files every time they're needed
// These files are loaded by calling the createResources-method, which is called in the Main-class
public abstract class ExternalResources {
  public static Font pixelFont10;
  public static Font pixelFont16;

  public static String gameStyleSheet;
  public static String victoryStyleSheet;
  public static String multiplayerStyleSheet;
  public static String multiplayerMenuStyleSheet;
  public static String mainStyleStyleSheet;
  public static String singePlayerMenuStyleSheet;

  public static Image menuTitle;
  public static Image singleplayerText;
  public static Image multiplayerText;

  public static Image startGameText;
  public static Image backButton;

  public static Image topMenuNewGame;
  public static Image topMenuTitle;
  public static Image submarine;

  public static void createResources() {
    topMenuNewGame = new Image("Images/pre-new-game.png");
    topMenuTitle = new Image("Images/premenu-title.png");
    submarine = new Image("Images/image_submarine.gif");

    menuTitle = new Image("Images/premenu-title.png");
    singleplayerText = new Image("Images/pre-single-game.png");
    multiplayerText = new Image("Images/pre-multi-game.png");

    startGameText = new Image("Images/pre-start-game.png");
    backButton = new Image("Images/back-icon.png");

    gameStyleSheet = ExternalResources.class.getResource("Assets/GameStyles.css").toExternalForm();
    mainStyleStyleSheet = ExternalResources.class.getResource("Assets/MainStyles.css").toExternalForm();
    victoryStyleSheet = ExternalResources.class.getResource("Assets/VictoryStyles.css").toExternalForm();
    multiplayerMenuStyleSheet = ExternalResources.class.getResource("Assets/MultiStyles.css").toExternalForm();
    singePlayerMenuStyleSheet = ExternalResources.class.getResource("Assets/SingleStyles.css").toExternalForm();
    multiplayerStyleSheet = ExternalResources.class.getResource("Assets/MultiplayerStyles.css").toExternalForm();

    pixelFont10 = Font.loadFont(getPixelFontResourceStream(), 10);
    pixelFont16 = Font.loadFont(getPixelFontResourceStream(), 16);
  }

  // make new inputStream each time - workaround for space chars ' ' in font path
  public static InputStream getPixelFontResourceStream() {
    return ExternalResources.class
            .getResourceAsStream("Assets/PressStart2P-Regular.ttf");
  }
}
