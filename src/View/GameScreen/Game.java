package View.GameScreen;

import Controller.GameController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Game extends Scene {
  private BorderPane gameView;
  private BoardView boardView;
  private TopMenu topmenu;

  public Game(int width, int inset, int size) {
    super(new BorderPane());

    topmenu = new TopMenu(width, inset);
    boardView = new BoardView(width, inset, size);

    gameView = new BorderPane();
    gameView.setTop(topmenu);
    gameView.setCenter(boardView);
    gameView.setId("gameback");

    super.setRoot(gameView);
    super.getStylesheets()
         .add(this.getClass()
                  .getResource("GameStyles.css")
                  .toExternalForm());
  }

  public void setController(GameController controller) {
    boardView.setEvents(controller);
  }
}
