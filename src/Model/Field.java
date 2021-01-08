package Model;

public class Field {
    private State state;
    private boolean isMine;
    private int adjacentMines;

    public Field() {
        this.state = State.UNFLAGGED;
        this.isMine = false;
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

    public enum State {
        FLAGGED,
        UNFLAGGED,
        PRESSED
    }
}
