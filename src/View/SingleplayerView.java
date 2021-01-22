package View;

import Controller.GameController;
import Services.ExternalResources;
import View.Components.TopMenuView;
import View.Util.BoardView;
import View.Util.BoardViewBuilder;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

//Skrevet af s204508 Massimo Hansen
public class SingleplayerView extends Scene {
  private BorderPane gameView;
  private final BoardView boardView;
  private final TopMenuView topMenu;

  GameController controller;

  public SingleplayerView(int[] stageDims, int inset, int size) {
    super(new BorderPane(), stageDims[0], stageDims[1]);

    topMenu = new TopMenuView(stageDims[0], inset);
    boardView = new BoardViewBuilder()
            .withStageDims(stageDims)
            .withInsetSize(inset)
            .withSize(size)
            .withTopview(topMenu)
            .build();

    initializeGameView();

    super.setRoot(gameView);
    super.getStylesheets()
         .add(ExternalResources.gameStyleSheet);
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
}
