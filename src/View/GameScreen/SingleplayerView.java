package View.GameScreen;

import Controller.GameController;
import View.GameScreen.Util.BoardViewBuilder;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class SingleplayerView extends Scene {
  private BorderPane gameView;
  private final BoardView boardView;
  private final TopMenu topMenu;

  GameController controller;

  public SingleplayerView(int width, int inset, int size) {
    super(new BorderPane());

    topMenu = new TopMenu(width, inset);
    boardView = new BoardViewBuilder()
            .withWidth(width)
            .withInsetSize(inset)
            .withSize(size)
            .build();

    initializeGameView();

    super.setRoot(gameView);
    super.getStylesheets()
         .add(this.getClass()
                  .getResource("GameStyles.css")
                  .toExternalForm());
  }

  public void initializeGameView() {
    gameView = new BorderPane();
    gameView.setTop(topMenu);
    gameView.setCenter(boardView);
    gameView.setId("gameback");
  }

  public void setController(GameController _controller) {
    controller = _controller;

    // also set controller refs in children
    boardView.setController(controller);
    topMenu.setController(controller);
  }

  public BoardView getBoardView() {
    return boardView;
  }

  // temp
  public void linkControllerToButtons() {
    boardView.setEvents();
    topMenu.setEvents();
  }
}
