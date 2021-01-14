package Model;

import Controller.GameController;

public class Board {
    private Field[][] minefield;
    private final int amountMines;

    private final int radius;

    private final int diameter;
private boolean FirstClick = true;
    public Board(int radius, int _amountMines) {
        this.amountMines = _amountMines;
        this.radius = radius;
        this.diameter = 2* radius + 1;
    }

    public void initializeMinefield() {
        makeMinefieldWithDimensions();
    }

    public void makeMinefieldWithDimensions() {
        minefield = new Field[diameter][];
        for (int col = 0; col < diameter; col++) {
            minefield[col] = new Field[diameter - Math.abs(col - radius)];

            for(int row = 0; row < minefield[col].length; row++)
                minefield[col][row] = new Field(col, row);
        }
    }

    private void setMines(int x, int y) {
        for (int curMines = 0; curMines < this.amountMines; curMines++) {
            int c = 0;
            int r = 0;
            do {
                c = (int)(Math.random()*radius);
                r = (int)(Math.random()*diameter);
            }while (c == x && r == y);
            if (!minefield[r][c].isMine()) {
                minefield[r][c].toggleIsMine();
            }
        }
    }

    private void setAdjacentMineCounters() {
        for(int col = 0; col < minefield.length; col++)
            for(int row = 0; row < minefield[col].length; row++)
                if(minefield[col][row].isMine())
                    incrementAdjacentMineCounters(col,row);
    }
    public void Blankfield(int x, int y) {
        if (getField(x, y).getAdjacentMines() == 0) {
            int[][] fields = {/*{x-1, y-1},*/ {x-1, y}, {x-1, y+1}, {x, y-1}, {x, y+1}, {x+1, y-1}, {x+1, y}/*, {x+1, y+1}*/};
            for (int[] field : fields) {
                int tempx = field[0];
                int tempy = field[1];
                if (tempx >= 0 && tempx < minefield.length && tempy >= 0 && tempy < minefield[tempx].length && !minefield[tempx][tempy].isMine()) {
                    if (!minefield[tempx][tempy].isMine() && !(minefield[tempx][tempy].getState() == Field.State.PRESSED)){
                        minefield[tempx][tempy].press();
                        GameController.updateTile(tempx,tempy);
                        Blankfield(tempx, tempy);
                    }
                }
            }
        }
    }
    public void setFieldState(int x, int y, Field.State state) {
        minefield[x][y].setState(state);
    }

    public void incrementAdjacentMineCounters(int col, int row){
        boolean onLeftSide = col < radius; // on left side of current index
        boolean onRightSide = col > radius; // on right side of current index
        int[][] adjacentFields = {
                {col, row-1},
                {col+1, row},
                {col, row+1},
                {col-1, row},
                {col-1, onRightSide ? row+1 : row-1},
                {col+1, onLeftSide ? row+1 : row-1}
        };

        for (int[] coord : adjacentFields) {
            boolean isInsideLowerBoundary = coord[0] >= 0 && coord[1] >= 0;

            if (isInsideLowerBoundary) { // otherwise it will throw exception in isInsideUpperBoundary evaluation
                boolean isInsideUpperBoundary = coord[0] < diameter &&
                                                coord[1] < minefield[coord[0]].length;

                if (isInsideUpperBoundary)
                    minefield[coord[0]][coord[1]].incrementAdjacentMines();
            }
        }
    }
    public void firstClick(int x, int y) {
        if (minefield[x][y].getState() == Field.State.PRESSED) {
            if (FirstClick) {
                FirstClick = false;
                setMines(x,y);
                setAdjacentMineCounters();
                GameController.updateTile(x,y);
            }
        }
    }

    public Field[][] getBoard() {
        return minefield;
    }

    public Field getField(int x, int y) {
        return minefield[x][y];
    }

    public void pressField(int x, int y) {
        minefield[x][y].press();
    }

    public void flagField(int x, int y)  {
        minefield[x][y].toggleFlag();
    }
}
