package Controller;

import Model.Board;
import Model.Field;
import Networking.FieldDTO;
import Networking.MultiplayerService;
import Services.BlankFieldSolver;
import View.GameScreen.Util.BoardView;
import View.Components.HexTile;

public class GameController {
  private Board board; // board data - states, etc.
  private final BoardView boardView; // graphical representation of board - for updating view on state changes
  private MultiplayerService mpService; // only set in multiplayer contexts. Can be either receiver or sender of http requests.

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

  public int getRadius() {
    return board.getRadius();
  }

  public boolean isInsideBounds(int x, int y) {
    return board.isInsideBounds(x,y);
  }

  public boolean isMine(int x, int y) {
    return board.getField(x,y).isMine();
  }

  public boolean isUnflagged(int x, int y) {
    return board.getField(x,y).getState() == Field.State.UNFLAGGED;
  }

  public void pressField(int x, int y) {
    board.pressField(x, y);
    board.firstClick(x, y);
    BlankFieldSolver.recursiveSolve(x, y, this);

    if(board.getField(x,y).isMine()) NavigationController.goToDefeatScreen();
    if(board.gameWon()) NavigationController.goToVictoryScreen(boardView.getTime());
  }

  public void flagField(int x, int y) {
    board.flagField(x,y);

    if(board.gameWon()) NavigationController.goToVictoryScreen(boardView.getTime());
  }

  public void updateTile(int x, int y) {
    Field field = board.getField(x, y);
    HexTile tile = boardView.getTile(x, y);
    tile.setTileText(field.getTileText());
    tile.render(field.getState());

    if (mpService != null) {
      mpService.sendHttpRequest(new FieldDTO(
              field.getX(),
              field.getY(),
              field.getState(),
              field.getTileText()));
    }
  }

  public void updateTile(int x,
                         int y,
                         Field.State action,
                         String tileText) {
    HexTile tile = boardView.getTile(x,y);
    tile.setTileText(tileText);
    tile.render(action);
  }

  public void setMpService(MultiplayerService mpService) {
    this.mpService = mpService;
  }
}
