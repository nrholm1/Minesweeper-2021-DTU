package Controller;

import Model.Board;
import Model.Field;
import View.GameScreen.Game;
import View.GameScreen.HexTile;
import View.GameScreen.HexagonalBoard;
import View.MenuScreen.PregameMenu;
import javafx.stage.Stage;

public class GameController {
  // does it need these three?
  private Stage root;
  private PregameMenu menu;
  private Game game;
  // ------------------------

  private Board board; // board data - states, etc.
  private HexagonalBoard GuiBoard; // graphical representation of board - for updating view on state changes

  public GameController(Stage root, PregameMenu menu) {
    this.root = root;
    this.menu = menu;
    menu.setController(this);
    root.setScene(menu);
    root.show();
  }

  public void beginGame() {
    board = new Board(menu.getSize() + 1, 20);
    game = new Game((int)menu.getWidth(), 30, menu.getSize());
    game.setController(this);
    root.setScene(game);
  }

  public int getAdjacentMines(int x, int y) {
    return board.getField(x,y).getAdjacentMines();
  }

  public void pressField(int x, int y) {
    Field field = board.getField(x, y);
    field.press();
    System.out.println(field.isMine() + " : " + field.getAdjacentMines());
  }

  public void flagField(int x, int y) {
    Field field = board.getField(x, y);
    field.toggleFlag();
  }

  public void updateTile(int x, int y) {
    Field field = board.getField(x, y);
    HexTile tile = GuiBoard.getTile(x, y); // make class for another level of abstraction for this?
    tile.renderTile(field.getState());
  }

  public void setGuiBoard(HexagonalBoard GuiBoard) {
    this.GuiBoard = GuiBoard;
  }
}
