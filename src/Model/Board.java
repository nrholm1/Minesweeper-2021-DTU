package Model;

public class Board {
    private Field[][] minefield;
    private int amountMines;
    private int height; // Height is height of first column
    private int width; // width is width of entire hexagon

    public Board(int height, int _amountMines) {
        this.amountMines = _amountMines;
        this.height = height;
        this.width = 2*height -1;

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

        /*
        System.out.println(minefield.length);
        System.out.println("---");
        for(int i = 0; i < minefield.length; i++) {
            for(int o = 0; o < width-minefield[i].length; o++) {
                System.out.print(" ");
            }
            for(int o = 0; o < minefield[i].length; o++) {
                System.out.print("X ");
            }
            System.out.println( minefield[i].length);
        } */
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
                minefield[x][y].setAdjacentMines(getSideMines(x, y));
            }
        }
    }

    private int getSideMines(int x, int y) {
        int mines = 0;
        int[][] fields = {/*{x-1, y-1},*/ {x-1, y}, {x-1, y+1}, {x, y-1}, {x, y+1}, {x+1, y-1}, {x+1, y}/*, {x+1, y+1}*/};
        for(int i = 0; i < fields.length; i++) {
            int tempx = fields[i][0];
            int tempy = fields[i][1];
            if(tempx >= 0 && tempx < minefield.length && tempy >= 0 && tempy < minefield[tempx].length && minefield[tempx][tempy].isMine()) {
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

    public void clickField(int x, int y) {
        minefield[x][y].click();
        //MainController.updateField(x, y);
    }

    public void flagField(int x, int y)  {
        minefield[x][y].flag();
        //MainController.updateField(x, y);
    }
}
