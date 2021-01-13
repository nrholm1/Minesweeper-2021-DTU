package Controller;

import Model.Board;
import Model.Field;
import Model.Util.BoardBuilder;
import View.GameScreen.BoardView;
import View.GameScreen.Game;
import View.GameScreen.HexTile;
import View.MenuScreen.PregameMenu;
import javafx.stage.Stage;

public class GameController {
  // does it need these three?
  private Stage root;
  private PregameMenu menu;
  private Game game;
  // ------------------------

  private Board board; // board data - states, etc.
  private BoardView boardView; // graphical representation of board - for updating view on state changes

  public GameController(Stage root, PregameMenu menu) {
    this.root = root;
    this.menu = menu;
    menu.setController(this);
    root.setScene(menu);
    root.show();
  }

  public void beginGame() {
    board = new BoardBuilder().withAmountMines(20)
                              .withSideLength(menu.getSize() + 1)
                              .build();
    game = new Game((int)menu.getWidth(), 30, menu.getSize());
    game.setController(this);
    root.setScene(game);
  }

  public int getAdjacentMines(int x, int y) {
    return board.getField(x,y).getAdjacentMines();
  }

  public void pressField(int x, int y) {
    board.pressField(x,y);
    System.out.println(board.getField(x,y));
  }

  public void flagField(int x, int y) {
    board.flagField(x,y);
    System.out.println(board.getField(x,y));
  }

  public void updateTile(int x, int y) {
    Field field = board.getField(x, y);
    HexTile tile = boardView.getTile(x, y); // make class for another level of abstraction for this?
    tile.render(field.getState());
  }

  public void setGuiBoard(BoardView _boardView) {
    this.boardView = _boardView;
  }
}
