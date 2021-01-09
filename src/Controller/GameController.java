package Controller;

import View.GameScreen.Game;
import View.MenuScreen.PregameMenu;
import javafx.stage.Stage;

public class GameController {
  private Stage root;
  private PregameMenu menu;
  private Game game;

  public GameController(Stage root, PregameMenu menu) {
    this.root = root;
    this.menu = menu;
    menu.setController(this);
    root.setScene(menu);
    root.show();
  }

  public void beginGame() {
    game = new Game((int)menu.getWidth(), 20, menu.getSize());
    root.setScene(game);
  }
}
