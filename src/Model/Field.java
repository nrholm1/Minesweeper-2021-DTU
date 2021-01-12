package Model;

public class Field {
    private boolean isMine;
    private State state;

    private int adjacentMines;

    private int x;
    private int y;

    public Field(int x, int y) {
        this.state = State.UNFLAGGED;
        this.isMine = false;
    }


    public void setState(State state) {
        this.state = state;
    }

    public void setAdjacentMines(int _adjacentMines) {
        this.adjacentMines = _adjacentMines;
    }

    public void toggleIsMine() {
        this.isMine = !this.isMine;
    }

    public boolean isMine() {
        return this.isMine;
    }

    public State getState() {
        return this.state;
    }

    public int getAdjacentMines() {
        return this.adjacentMines;
    }


    public void incrementAdjacentMines() {
        this.adjacentMines++;
    }

    public void press() {
        if (this.state != State.FLAGGED)
            this.state = State.PRESSED;
    }

    public void flag() {
        if (this.state != State.PRESSED)
            this.state = State.FLAGGED;
    }

    public enum State {
        PRESSED,
        FLAGGED,
        UNFLAGGED
    }
}
