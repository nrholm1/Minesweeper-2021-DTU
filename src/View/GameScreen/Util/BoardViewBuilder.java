package View.GameScreen.Util;

import View.GameScreen.BoardView;

public class BoardViewBuilder {
    int width;
    int inset;
    int size;

    public BoardView build() {
        if (width > 0 && inset > 0 && size > 0)
            return new BoardView(width, inset, size);
        else
            throw new IllegalArgumentException("Please enter a positive value for all params");
    }

    public BoardViewBuilder withWidth(int _width) {
        width = _width;
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
