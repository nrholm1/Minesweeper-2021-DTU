package Controller;

import Model.Board;
import Model.Field;
import Networking.MultiplayerService;
import Services.BlankFieldSolver;
import View.Util.BoardView;
import View.Components.HexTile;

// By Magnus Meyer, s204509 & Niels Raunkjær Holm, s204503 & Massimo Hansen, s204508 & Simon Buk-Mortensen, s204497
// We have all added to the controller, since the controller includes many aspects of the project
// The game controller is the main constroller. It receives events them the user and updates the models accordingly.
// Meanwhile it updates the views, so they are in alignment with the states of the models
// It has helper methods to get certain values from the views or the models, which is needed in other parts of the project.
// Notice the class isn't abstract, and it has been designed to be initiated with a Board model and Board view, in order to allow multiple games in the same session.
public class GameController {
  private Board board; // board data - states, etc.
  private final BoardView boardView; // graphical representation of board - for updating view on state changes
  private boolean multiplayerSession = false; // Whether or not to send own position to enemy

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

    if(board.getField(x,y).getState() == Field.State.PRESSED) {
      board.firstClick(x, y);
      BlankFieldSolver.recursiveSolve(x, y, this);

      if (board.getField(x, y).isMine())
        NavigationController
                .goToDefeatScreen();
      if (board.isGameWon())
        NavigationController
                .goToVictoryScreen(getWinTime(), true);
    }
  }

  public void flagField(int x, int y) {
    board.flagField(x,y);

    if(board.isGameWon())
      NavigationController
              .goToVictoryScreen(getWinTime(), true);
  }

  public void updateTile(int x, int y) {
    Field field = board.getField(x, y);
    HexTile tile = boardView.getTile(x, y);
    tile.setTileText(field.getTileText());
    tile.render(field.getState());

    if (multiplayerSession)
      MultiplayerService
              .sendHttpRequest(MultiplayerService
                      .createPayload(board.isGameWon(), field));
  }

  public void updateTile(int x,
                         int y,
                         Field.State action,
                         String tileText) {
    HexTile tile = boardView.getTile(x,y);
    tile.setTileText(tileText);
    tile.render(action);
  }

  public void toggleMultiplayer() {
    multiplayerSession = !multiplayerSession;
  }

  public int getWinTime() {
    return boardView.getTime();
  }
}
