package View.SinglePlayerGameScreen.Util;

import View.SinglePlayerGameScreen.BoardView;

public class BoardViewBuilder {
    int[] stageDims;
    int inset;
    int size;

    public BoardView build() {
        if (stageDims[1] > 0 && inset > 0 && size > 0)
            return new BoardView(stageDims, inset, size);
        else
            throw new IllegalArgumentException("Please enter a positive value for all params");
    }

    public BoardViewBuilder withStageDims(int[] _stageDims) {
        stageDims = _stageDims;
        return this;
    }

    public BoardViewBuilder withInsetSize(int _inset) {
        inset = _inset;
        return this;
    }

    public BoardViewBuilder withSize(int _size) {
        size = _size;
        return this;
    }
}
