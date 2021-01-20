package Model.Util;

import Model.Board;

public class BoardBuilder {
    int amountMines;
    int radius;

    public Board build() {
        return new Board(this.radius, this.amountMines);
    }

    public BoardBuilder withAmountMines(int _amountMines) {
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

    // has to be called after size has been set
    //difficulty goes from 1 - 10. min is 4% mines, max is 20% mines
    public BoardBuilder withDifficulty(int diff) {
        // Calculates amount of fields in the board
        int fieldCount = 6 * ((radius + 1) * (radius/2) +
                (radius % 2 == 0 ? 0 : (radius + 1)/2)) + 1;

        //Calculates percentage. Expression has been made using the "to-punktformel". This is because we want the percentage to gradually increase as the difficulty increases.
        double percentage =  ((16.0/9.0 * (double)diff + (20.0/9.0)) / 100.0);

        this.amountMines = (int)(fieldCount * percentage);
        return this;
    }

    public BoardBuilder() {}
}
