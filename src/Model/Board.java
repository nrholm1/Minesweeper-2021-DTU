package Model;

public class Board {
    private Field[][] minefield;
    private double probability;
    private int rowLength;
    private int colLength;


    public Field getField(int r, int c) {
        return minefield[r][c];
    }

    public void setFieldState(int r, int c) {
        // set field state when pressed
    }

    public int getRowLength() {
        return rowLength;
    }

    public int getColLength() {
        return colLength;
    }

    private void setBombs(int bombs) {
        for(int i = 0; i < bombs; i++) {
            minefield[(int)(Math.random() * rowLength)][(int)(Math.random() * colLength)].setMine(true);
        }
    }

    public Board(double _probability, int _rowLength, int _colLength) {
        this.probability = _probability;
        this.rowLength = _rowLength;
        this.colLength = _colLength;

        makeMinefieldWithDimensions();
    }

    public void makeMinefieldWithDimensions() {
        minefield = new Field[this.colLength][this.rowLength];

        for(int row = 0; row < minefield.length; row++)
            for(int col = 0; col < minefield[row].length; col++) {
                boolean _isMine = (Math.random() < this.probability);
                minefield[row][col] = new Field(_isMine);
            }

        setAdjacentMineCounters();
    }

    private void setAdjacentMineCounters() {
        for(int c = 0; c < this.colLength; c++)
            for(int r = 0; r < this.rowLength; r++)
                if (minefield[c][r].isMine())
                    incrementAdjacentFieldsAdjacentMineCounter(c, r);
    }

    private void incrementAdjacentFieldsAdjacentMineCounter(int col, int row) {
        for(int c = -1; c <= 1; c++)
            for(int r = -1; r <= 1; r++)
                if (col + c >= 0 && col + c < this.colLength &&
                        row + r >= 0 && row + r < this.rowLength &&
                        (c != 0 || r != 0))
                    minefield[col + c][row + r].incrementAdjacentMines();
    }

    public void printMinefield() {
        for(Field[] row : this.minefield) {
            for(Field i : row)
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
