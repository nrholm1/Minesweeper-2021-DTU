package Model.Util;

import Model.Board;

public class BoardBuilder {
    int amountMines;
    int rowLength;
    int colLength;

    public Board build() {
        return new Board(this.amountMines, this.rowLength, this.colLength);
    }

    public BoardBuilder withAmountMines(int _amountMines) {
        if (_amountMines > 1 || _amountMines < 0)
            throw new IllegalArgumentException("Value outside of valid range (0 - 1)");
        this.amountMines = _amountMines;

        return this;
    }

    public BoardBuilder withDimensions(int _rowLength, int _colLength) {
        this.rowLength = _rowLength;
        this.colLength = _colLength;

        return this;
    }

    public BoardBuilder withRowLength(int _rowLength) {
        this.rowLength = _rowLength;

        return this;
    }

    public BoardBuilder withColLength(int _colLength) {
        this.colLength = _colLength;

        return this;
    }

    public BoardBuilder() {}
}
