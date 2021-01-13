package View.GameScreen.Util;

import View.GameScreen.SingleplayerView;

public class SingleplayerViewBuilder {
    int width;
    int inset;
    int size;

    public SingleplayerView build() {
        if (width > 0 && inset > 0 && size > 0)
            return new SingleplayerView(width, inset, size);
        else
            throw new IllegalArgumentException("Please enter a positive value for all params");
    }

    public SingleplayerViewBuilder withWidth(int _width) {
        width = _width;
        return this;
    }

    public SingleplayerViewBuilder withInsetSize(int _inset) {
        inset = _inset;
        return this;
    }

    public SingleplayerViewBuilder withSize(int _size) {
        size = _size;
        return this;
    }
}
