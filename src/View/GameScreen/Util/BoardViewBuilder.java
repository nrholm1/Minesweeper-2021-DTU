package View.GameScreen.Util;

import View.Components.TopMenuView;

public class BoardViewBuilder {
    private int[] stageDims;
    private int inset;
    private int size;
    private TopMenuView topMenu;
    private boolean disableTiles = false;

    public BoardView build() {
        if (stageDims[1] > 0 && inset > 0 && size > 0)
            return new BoardView(stageDims, inset, size, topMenu, disableTiles);
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

    public BoardViewBuilder withTopview(TopMenuView top) {
        topMenu = top;
        return this;
    }

    public BoardViewBuilder disableTiles() {
        disableTiles = true;
        return this;
    }

    public BoardViewBuilder withSize(int _size) {
        size = _size;
        return this;
    }
}
