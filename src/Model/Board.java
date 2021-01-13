package Model;

public class Board {
    private Field[][] minefield;
    private final int amountMines;
    // TODO refactor height and width
    private final int height; // Height is height of first column
    private final int width; // width is width of entire hexagon

    private final int diameter;

    public Board(int height, int _amountMines) {
        this.amountMines = _amountMines;
        this.height = height;
        this.width = 2*height -1;

        this.diameter = 2*height + 1; // Massimo's version

        makeMinefieldWithDimensions();
        setMines();
        setAdjacentMineCounters();
    }

    public void makeMinefieldWithDimensions() {
        minefield = new Field[width][];
        minefield[height-1] = new Field[width]; // Middle column is created
        for(int x = 0; x <= height-2; x++) { // Other columns are created
            minefield[x] = new Field[height+x];
            minefield[width - 1 - x] = new Field[height+x];
        }
        for(int x = 0; x < minefield.length; x++) { // Initiate each field
            for(int y = 0; y < minefield[x].length; y++) {
                minefield[x][y] = new Field(x, y);
            }
        }
    }

    private void setMines() {
        int curMines = 0;
        while(curMines < this.amountMines) {
            int x = (int)(Math.random() * width);
            int y = (int)(Math.random() * minefield[x].length);

            if (!minefield[x][y].isMine()) {
                minefield[x][y].toggleIsMine();
                curMines++;
            }
        }
    }

    private void setAdjacentMineCounters() {
        for(int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                minefield[x][y].setAdjacentMines(getAdjacentMines(x, y));
            }
        }
    }

    public void setFieldState(int x, int y, Field.State state) {
        minefield[x][y].setState(state);
    }

    public int getAdjacentMines(int col, int row){
        int mines = 0;
        int[][] fields = {
                {col, row-1},
                {col+1, row},
                {col, row+1},
                {col-1, row},
                {col-1, col > 5 ? row+1 : row-1},
                {col+1, col < 5 ? row-1 : row+1}
        };
        for (int[] field : fields) {
            int tempx = field[0];
            int tempy = field[1];
            if (tempx >= 0 && tempx < minefield.length && tempy >= 0 && tempy < minefield[tempx].length && minefield[tempx][tempy].isMine()) {
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

    public int getBoardWidth() {
        return width;
    }

    public int getBoardHeight() {
        return height;
    }

    public void pressField(int x, int y) {
        minefield[x][y].press();
    }

    public void flagField(int x, int y)  {
        minefield[x][y].toggleFlag();
    }
}
