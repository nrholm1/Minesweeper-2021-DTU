package View.Util;

import View.SingleplayerView;

public class SingleplayerViewBuilder {
    int[] stageDims;
    int inset;
    int size;

    public SingleplayerView build() {
        if (inset > 0 && size > 0)
            return new SingleplayerView(stageDims, inset, size);
        else
            throw new IllegalArgumentException("Please enter a positive value for all params");
    }

    public SingleplayerViewBuilder withStageDims(int[] _stageDims) {
        stageDims = _stageDims;
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
