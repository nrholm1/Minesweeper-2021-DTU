package Controller;

import Model.Board;
import Model.Field;
import View.GameScreen.BoardView;
import View.GameScreen.HexTile;

public class GameController {
  NavigationController navigation;

  private Board board; // board data - states, etc.
  private BoardView boardView; // graphical representation of board - for updating view on state changes

  public GameController(NavigationController _navigation) {
    navigation = _navigation;
  }

  public void initializeMinefield() {
    board.initializeMinefield();
  }

  public void initializeBoardView() {
    boardView.renderEntireTileField();
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
    HexTile tile = boardView.getTile(x, y);
    tile.setAdjacentMinesAmount("" + field.getAdjacentMines());
    tile.render(field.getState());

  }

  public void setBoardModel(Board _board) {
    board = _board;
  }

  public void setBoardView(BoardView _boardView) {
    this.boardView = _boardView;
  }

  public NavigationController getNavigation() {
    return navigation;
  }
}
