package Model;

import Controller.GameController;

public class Board {
    private Field[][] minefield;
    private final int amountMines;

    private int amountFields;
    private int totalTiles;

    private final int radius;

    private final int diameter;
    private boolean isFirstClick;

    private int flaggedMines;
    private int flaggedNonMines;
    private int openedFields;

    public Board(int radius, int _amountMines) {
        this.amountMines = _amountMines;
        this.radius = radius;
        this.diameter = 2* radius + 1;
        this.amountFields = getAmountFields();
        makeMinefieldWithDimensions();
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
        isFirstClick = true;
    }

    private void setMines(int x, int y) {
        int curMines = 0;
        while(curMines < this.amountMines) {
            int r = (int)(Math.random() * diameter);
            int c = (int)(Math.random() * minefield[r].length);

            if ((r == x && c == y) || minefield[r][c].isMine())
                continue;

            minefield[r][c].toggleIsMine();
            curMines++;
        }
    }

    private int getAmountFields(){
        int n = radius;
        int total = 0;

        //We count them in hexagonal rings, starting from the outmost ring
        while(n > 0){ //When we hit n = 0, we have reached the center point
            total += 6*n; //Every ring has 6n hexagons
            n--; //Go to next ring
        }
        total++;//We also have to count the center point
        return total - amountMines; //We have to subtract the number of mines
    }

    private void setAdjacentMineCounters() {
        for(int col = 0; col < minefield.length; col++)
            for(int row = 0; row < minefield[col].length; row++)
                if(minefield[col][row].isMine())
                    incrementAdjacentMineCounters(col,row);
    }

    // TODO refactor controller argument
    public void blankField(int col, int row, GameController gameController) {
        if (getField(col, row).getAdjacentMines() == 0) {
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
            for (int[] field : adjacentFields) {
                int tempx = field[0];
                int tempy = field[1];
                if (tempx >= 0 && tempx < minefield.length && tempy >= 0 && tempy < minefield[tempx].length && !minefield[tempx][tempy].isMine()) {
                    if (!minefield[tempx][tempy].isMine() && !(minefield[tempx][tempy].getState() == Field.State.PRESSED)){
                        pressField(tempx, tempy);
                        blankField(tempx, tempy, gameController);
                        gameController.updateTile(tempx,tempy);
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
            if (isFirstClick) {
                isFirstClick = false;
                setMines(x,y);
                setAdjacentMineCounters();
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
        openedFields++;
        minefield[x][y].press();
    }

    public void flagField(int x, int y)  {
        Field field = minefield[x][y];

        if(field.isMine()) {
            if(field.getState() == Field.State.UNFLAGGED) flaggedMines++;
            else flaggedMines--;
        }
        else{
            if(field.getState() == Field.State.UNFLAGGED) flaggedNonMines++;
            else flaggedNonMines--;
        }

        field.toggleFlag();
    }

    public Boolean gameWon(){
        if((flaggedMines == amountMines && flaggedNonMines == 0) || (openedFields == amountFields)){
            System.out.println(openedFields);
            return true;
        }
        return false;
    }

}
