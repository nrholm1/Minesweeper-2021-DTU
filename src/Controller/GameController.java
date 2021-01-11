package Controller;

import Model.Board;
import Model.Field;
import View.GameScreen.Game;
import View.MenuScreen.PregameMenu;
import javafx.stage.Stage;

public class GameController {
  private Stage root;
  private PregameMenu menu;
  private Game game;
  private Board b;

  public GameController(Stage root, PregameMenu menu) {
    this.root = root;
    this.menu = menu;
    menu.setController(this);
    root.setScene(menu);
    root.show();
  }

  public void beginGame() {
    b = new Board(menu.getSize() + 1, 20);
    game = new Game((int)menu.getWidth(), 30, menu.getSize());
    game.setController(this);
    root.setScene(game);
  }

  public void clickField(int x, int y) {
    Field f = b.getField(x, y);
    System.out.println(f.isMine() + " : " + f.getAdjacentMines());
  }
}
