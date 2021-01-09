package Controller;

import View.GameScreen.Game;
import View.MenuScreen.PregameMenu;
import javafx.stage.Stage;

public class GameController {
  private Stage root;
  private PregameMenu menu;
  private Game game;

  public GameController(Stage root, PregameMenu menu, Game game) {
    this.root = root;
    this.menu = menu;
    this.game = game;
    menu.setController(this);
    root.setScene(menu);
    root.show();
  }

  public void beginGame() {
    root.setScene(game);
  }
}
