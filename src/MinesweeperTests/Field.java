package MinesweeperTests;

public class Field {
    private State state;
    private boolean isMine;
    private int adjacentMines;

    public Field(State _state, boolean _isMine) {
        this.state = _state;
        this.isMine = _isMine;
        this.adjacentMines = 0;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State _state) {
        this.state = _state;
    }

    public boolean isMine() {
        return this.isMine;
    }

    public void toggleIsMine() {
        this.isMine = !this.isMine;
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

    // used for visualizing with console
    public String toString() {
        String retval = "";

        // nested ternary :O
        retval += this.isMine ?
                "X" :
                (this.adjacentMines > 0 ?
                        this.adjacentMines : " ");

        return retval;
    }

    public static enum State {
        FLAGGED,
        UNFLAGGED,
        PRESSED
    }
}
