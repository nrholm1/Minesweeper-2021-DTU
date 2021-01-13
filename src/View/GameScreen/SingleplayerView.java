package View.GameScreen;

import Controller.GameController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class SingleplayerView extends Scene {
  private final BorderPane gameView;
  private final BoardView boardView;
  private final TopMenu topMenu;

  GameController controller;

  public SingleplayerView(int width, int inset, int size) {
    super(new BorderPane());

    topMenu = new TopMenu(width, inset);
    boardView = new BoardView(width, inset, size);

    gameView = new BorderPane();
    gameView.setTop(topMenu);
    gameView.setCenter(boardView);
    gameView.setId("gameback");

    super.setRoot(gameView);
    super.getStylesheets()
         .add(this.getClass()
                  .getResource("GameStyles.css")
                  .toExternalForm());
  }

  public void setController(GameController _controller) {
    controller = _controller;
  }

  // temp
  public void linkControllerToButtons() {
    boardView.setEvents(controller);
    topMenu.setEvents(controller);
  }
}
