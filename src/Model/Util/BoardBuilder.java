package Model.Util;
import Model.Board;
//This Class builds the board.
//By Niels Raunkjær Holm (s204503).

public class BoardBuilder {
    int amountMines;
    double difficulty;
    int radius;
    boolean setWithDifficulty;

    public Board build() {
        if (setWithDifficulty)
            return new Board(this.radius, this.difficulty);
        else
            return new Board(this.radius, this.amountMines);
    }

    public BoardBuilder withAmountMines(int _amountMines) {
        setWithDifficulty = false;
        if (_amountMines < 0)
            throw new IllegalArgumentException("Amount of mines must be greater than or equal to 0");
        this.amountMines = _amountMines;

        return this;
    }

    public BoardBuilder withSize(int _size) {
        if (_size >= 4)
            this.radius = _size;
        else throw new IllegalArgumentException("Side length must be greater than or equal to 4");

        return this;
    }

    public BoardBuilder withDifficulty(int _difficulty) {
        setWithDifficulty = true;
        this.difficulty = _difficulty;
        return this;
    }
}
