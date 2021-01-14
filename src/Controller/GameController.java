package Controller;

import Model.Board;
import Model.Field;
import View.GameScreen.Util.BoardView;
import View.Components.HexTile;

public class GameController {
  private static Board board; // board data - states, etc.
  private static BoardView boardView; // graphical representation of board - for updating view on state changes

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
    board.firstClick(x, y);
    board.blankField(x,y);
    System.out.println(board.getField(x,y));
  }

  public void flagField(int x, int y) {
    board.flagField(x,y);
    System.out.println(board.getField(x,y));
  }

  public static void updateTile(int x, int y) {
    Field field = board.getField(x, y);
    HexTile tile = boardView.getTile(x, y);
    tile.setTileText(field.getTileText());
    tile.render(field.getState());

  }

  public void setBoardModel(Board _board) {
    board = _board;
  }

  public void setBoardView(BoardView _boardView) {
    this.boardView = _boardView;
  }

  public void setFieldState(int x, int y, Field.State action) {
    board.setFieldState(x,y,action);
  }
}
