package Model;

public class Field {
    private boolean isMine;
    private State state;

    private int adjacentMines;

    private final int x;
    private final int y;

    public Field(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        this.state = State.UNFLAGGED;
        this.isMine = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void toggleFlag() {
        if (this.state != State.PRESSED)
            this.state = this.state == State.UNFLAGGED ?
                        State.FLAGGED : State.UNFLAGGED;
    }

    public String toString() {
        return "(" + x + ", " + y + ") " + state
                + (isMine ? " X" : " " + adjacentMines);
    }

    public byte[] toBytes() {
        return this.toParsableString()
                .getBytes();
    }

    public String toParsableString() {
        String actionString = switch (this.state) {
            case PRESSED -> "P";
            case FLAGGED -> "F";
            case UNFLAGGED -> "U";
        };

        return  actionString +
                "|" +
                this.x +
                "|" +
                this.y;
    }

    public enum State {
        PRESSED,
        FLAGGED,
        UNFLAGGED
    }
}
