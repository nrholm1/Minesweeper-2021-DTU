package Model.Util;

import Model.Board;

public class BoardBuilder {
    double probability;
    int rowLength;
    int colLength;

    public Board build() {
        return new Board(this.probability, this.rowLength, this.colLength);
    }

    public BoardBuilder withProbability(double _probability) {
        if (probability > 1 || probability < 0)
            throw new IllegalArgumentException("Value outside of valid range (0 - 1)");
        this.probability = _probability;

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
