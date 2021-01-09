package Model;

public class Board {
    private Field[][] minefield;
    private final int amountMines;
    private final int rowLength;
    private final int colLength;

    public Board(int _rowLength, int _colLength, int _amountMines) {
        this.amountMines = _amountMines;
        this.rowLength = _rowLength;
        this.colLength = _colLength;

        makeMinefieldWithDimensions();
        setMines();
        setAdjacentMineCounters();
    }

    public void makeMinefieldWithDimensions() {
        minefield = new Field[this.colLength][this.rowLength];

        for(int col = 0; col < colLength; col++) {
            for (int row = 0; row < rowLength; row++) {
                minefield[col][row] = new Field(col, row);
            }
        }
    }


    private void setMines() {
        int curMines = 0;
        while(curMines < this.amountMines) {
            int c = (int)(Math.random() * colLength);
            int r = (int)(Math.random() * rowLength);

            if (!minefield[c][r].isMine()) {
                minefield[c][r].toggleIsMine();
                curMines++;
            }
        }
    }

    private void setAdjacentMineCounters() {
        for(int c = 0; c < this.colLength; c++) {
            for (int r = 0; r < this.rowLength; r++) {
                minefield[c][r].setAdjacentMines(getSideMines(c, r));
            }
        }
    }

    private int getSideMines(int x, int y) {
        int mines = 0;
        int[][] fields = {{x-1, y-1}, {x-1, y}, {x-1, y+1}, {x, y-1}, {x, y+1}, {x+1, y-1}, {x+1, y}, {x+1, y+1}};
        for(int i = 0; i < fields.length; i++) {
            int tempx = fields[i][0];
            int tempy = fields[i][1];
            if(tempx >= 0 && tempx < rowLength && tempy >= 0 && tempy < colLength && minefield[tempx][tempy].isMine()) {
                mines++;
            }
        }
        return mines;
    }

    public Field[][] getBoard() {
        return minefield;
    }

    public Field getField(int x, int y) {
        return minefield[x][y];
    }

    public int getBoardLength() {
        return rowLength;
    }

    public int getBoardHeight() {
        return colLength;
    }

    public void clickField(int x, int y) {
        minefield[x][y].click();
        //MainController.updateField(x, y);
    }

    public void flagField(int x, int y)  {
        minefield[x][y].flag();
        //MainController.updateField(x, y);
    }
}
