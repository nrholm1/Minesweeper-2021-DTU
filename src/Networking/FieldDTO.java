package Networking;

import Model.Field;

import java.util.Arrays;

public class FieldDTO {
    int x; // row index
    int y; // col index
    Field.State action; // change state to
    String tileText;

    public FieldDTO(Field alteredField) {
        new FieldDTO(alteredField.getX(),
                alteredField.getY(),
                alteredField.getState(), tileText);
    }

    public FieldDTO(int _x, int _y, Field.State _action, String _tileText) {
        x = _x;
        y = _y;
        action = _action;
        tileText = _tileText;
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
                this.tileText;
    }

    public String toString() {
        return ""+  action + " (" + x + ", " + y + ") " + tileText;
    }

    public static void testDataProcess(FieldDTO fieldDTO) {
        String formattedData = fieldDTO.toParsableString();
        byte[] serializedData = fieldDTO.toBytes();
        FieldDTO deserializedData = Parser.readRequestInput(serializedData);

        System.out.println(fieldDTO +
                    "\n format -> " + formattedData +
                    "\n serialize -> " + Arrays.toString(serializedData) +
                    "\n deserialize -> " + deserializedData +
                    "\n");
    }

    // TEST
    public static void main(String[] args) {
        FieldDTO fieldDTO1 = new FieldDTO(12,24, Field.State.FLAGGED, "X");
        FieldDTO fieldDTO2 = new FieldDTO(37,1, Field.State.PRESSED, "3");
        FieldDTO fieldDTO3 = new FieldDTO(420,69, Field.State.UNFLAGGED, "2");

        testDataProcess(fieldDTO1);
        testDataProcess(fieldDTO2);
        testDataProcess(fieldDTO3);
    }
}
