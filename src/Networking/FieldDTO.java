package Networking;

import Model.Field;

public class FieldDTO {
    private final int x; // row index
    private final int y; // col index
    private final Field.State action; // change state to
    private final String tileText;
    private final String gameState;

    public FieldDTO(int _x, int _y, Field.State _action, String _tileText, String _gameState) {
        x = _x;
        y = _y;
        action = _action;
        tileText = _tileText;
        gameState = _gameState;
    }

    public Field.State getAction() {
        return action;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTileText() {
        return tileText;
    }

    public String getGameState() {
        return gameState;
    }

    public byte[] toBytes() {
        return this.toParsableString()
                   .getBytes();
    }

    public String toParsableString() {
        String actionString = switch (this.action) {
            case PRESSED -> "P";
            case FLAGGED -> "F";
            case UNFLAGGED -> "U";
        };

        return  actionString +
                "|" +
                this.x +
                "|" +
                this.y +
                "|" +
                this.tileText+
                "|" +
                this.gameState;
    }

    public String toString() {
        return ""+  action + " (" + x + ", " + y + ") " + tileText + " " + gameState;
    }
}
