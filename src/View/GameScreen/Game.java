package View.GameScreen;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Game extends Scene {
  private BorderPane gameView;

  public Game(int width, int inset, int size) {
    super(new BorderPane());

    gameView = new BorderPane();
    gameView.setTop(new TopMenu(width));
    gameView.setCenter(new HexagonalBoard(width, inset, size));

    super.setRoot(gameView);
  }
}
