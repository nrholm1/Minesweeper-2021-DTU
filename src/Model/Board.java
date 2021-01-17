package Model;

public class Board {
    private Field[][] minefield;
    private final int amountMines;

    private final int radius;

    private final int diameter;
    private boolean isFirstClick;

    private int flaggedMines;
    private int flaggedNonMines;
    private int openedFields;
    private int amountFields;

    public Board(int radius, int _amountMines) {
        this.amountMines = _amountMines;
        this.radius = radius;
        this.diameter = 2* radius + 1;
        this.amountFields = getAmountFields();
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

            boolean rInVicinityOfX = r >= x - 1 && r <= x + 1;
            boolean cInVicinityOfY = c >= y - 1 && c <= y + 1;

            if ((rInVicinityOfX && cInVicinityOfY)
                    ||
                    minefield[r][c].isMine())
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

    public boolean isInsideBounds(int x, int y) {
        boolean isInLowerBound = x >= 0 && y >= 0;
        if (!isInLowerBound)
            return false;

        boolean isInUpperBound = x < minefield.length &&
                y < minefield[x].length;
        return isInUpperBound;
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

    public int getRadius() {
        return this.radius;
    }

    public void pressField(int x, int y) {
        Field field = minefield[x][y];

        if(!field.isMine()){ openedFields++; }
        else {
            //LOSE
        }

        field.press();
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
        if((flaggedMines == amountMines && flaggedNonMines == 0) || (amountFields == openedFields)){
            return true;
        }
        return false;
    }
}