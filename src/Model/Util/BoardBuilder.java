package Model.Util;

import Model.Board;

public class BoardBuilder {
    int amountMines;
    int sideLength;

    public Board build() {
        return new Board(this.sideLength, this.amountMines);
    }

    public BoardBuilder withAmountMines(int _amountMines) {
        if (_amountMines < 0)
            throw new IllegalArgumentException("Amount of mines must be greater than or equal to 0");
        this.amountMines = _amountMines;

        return this;
    }

    public BoardBuilder withSideLength(int _sideLength) {
        if (_sideLength >= 5)
            this.sideLength = _sideLength;
        else throw new IllegalArgumentException("Side length must be greater than or equal to 5");

        return this;
    }

    public BoardBuilder() {}
}
