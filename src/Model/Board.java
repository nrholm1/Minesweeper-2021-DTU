package Model;

public class Board {
    private Field[][] minefield;
    private final int amountMines;

    private final int radius;

    private final int diameter;

    public Board(int radius, int _amountMines) {
        this.amountMines = _amountMines;
        this.radius = radius;
        this.diameter = 2* radius + 1;

        makeMinefieldWithDimensions();
        setMines();
        setAdjacentMineCounters();
    }

    public void makeMinefieldWithDimensions() {
        minefield = new Field[diameter][];
        for (int col = 0; col < diameter; col++) {
            minefield[col] = new Field[diameter - Math.abs(col - radius)];

            for(int row = 0; row < minefield[col].length; row++)
                minefield[col][row] = new Field(col, row);
        }
    }

    private void setMines() {
        int curMines = 0;
        while(curMines < this.amountMines) {
            int x = (int)(Math.random() * diameter);
            int y = (int)(Math.random() * minefield[x].length);

            if (!minefield[x][y].isMine()) {
                minefield[x][y].toggleIsMine();
                curMines++;
            }
        }
    }

    private void setAdjacentMineCounters() {
        for(int col = 0; col < minefield.length; col++)
            for(int row = 0; row < minefield[col].length; row++)
                if(minefield[col][row].isMine())
                    incrementAdjacentMineCounters(col,row);
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
