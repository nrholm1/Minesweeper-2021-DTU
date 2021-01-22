package Services;
import Controller.GameController;
//This service clicks on all blank fields until it reach a field, there has a mines as neighbor
//Made by Simon Buk-Mortensen(S204497) og Niels Raunkjær Holm (s204503)
public abstract class  BlankFieldSolver {
    public static void recursiveSolve(int col, int row, GameController gameController) {
        if (gameController.getAdjacentMines(col,row) == 0) {
            boolean onLeftSide = col < gameController.getRadius(); // on left side of current index
            boolean onRightSide = col > gameController.getRadius(); // on right side of current index
            int[][] adjacentFields = {
                    {col, row-1},
                    {col+1, row},
                    {col, row+1},
                    {col-1, row},
                    {col-1, onRightSide ? row+1 : row-1},
                    {col+1, onLeftSide ? row+1 : row-1}
            };
            for (int[] field : adjacentFields) {
                int curX = field[0];
                int curY = field[1];
                if (gameController.isInsideBounds(curX, curY)) {
                    if (!gameController.isMine(curX,curY) &&
                         gameController.isUnflagged(curX,curY)) {
                        gameController.pressField(curX,curY);
                        gameController.updateTile(curX, curY);
                        recursiveSolve(curX, curY, gameController);
                    }
                }
            }
        }
    }
}
