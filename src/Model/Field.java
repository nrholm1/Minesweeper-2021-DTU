package Model;

public class Field {
    private boolean mine;
    private boolean flagged;
    private boolean clicked;

    private int adjacentMines;

    private int x;
    private int y;

    public Field(int x, int y) {
        this.clicked = false;
        this.flagged = false;
        this.mine = false;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void toggleIsMine() {
        this.mine = !this.mine;
    }

    public int getAdjacentMines() {
        return this.adjacentMines;
    }

    public void setAdjacentMines(int _adjacentMines) {
        this.adjacentMines = _adjacentMines;
    }

    public void incrementAdjacentMines() {
        this.adjacentMines++;
    }

    public void click() {
        if(!flagged) clicked = true;
    }

    public void flag() {
        if(!clicked) flagged = !flagged;
    }
}
