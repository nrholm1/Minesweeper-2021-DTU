package Controller;

import Model.Board;
import Model.Field;
import View.GameScreen.Util.BoardView;
import View.Components.HexTile;

public class GameController {
  private Board board; // board data - states, etc.
  private final BoardView boardView; // graphical representation of board - for updating view on state changes
  private MultiplayerController mpController; // only set in multiplayer contexts. Can be either receiver or sender of http requests.

  public GameController(Board b, BoardView bv) {
    this.board = b;
    this.boardView = bv;
    bv.setController(this);
  }

  public GameController(BoardView bv) {
    this.boardView = bv;
    bv.setController(this);
  }

  // needed for refactor bfl to service
  public int getAdjacentMines(int x, int y) {
    return board.getField(x,y).getAdjacentMines();
  }

  public void pressField(int x, int y) {
    board.pressField(x,y);
    board.firstClick(x, y);
    board.blankField(x,y,this);
    System.out.println(board.getField(x,y));
  }

  public void flagField(int x, int y) {
    board.flagField(x,y);
    System.out.println(board.getField(x,y));
  }

  public void updateTile(int x, int y) {
    Field field = board.getField(x, y);
    HexTile tile = boardView.getTile(x, y);
    tile.setTileText(field.getTileText());
    tile.render(field.getState());

    if (mpController != null)
      mpController.sendEvent(field);
  }

  public void setFieldState(int x, int y, Field.State action) {
    board.setFieldState(x,y,action);
  }

  public void setMpController(MultiplayerController mpController) {
    this.mpController = mpController;
  }
}
