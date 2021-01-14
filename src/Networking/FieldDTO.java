package Networking;

import java.util.Arrays;

public class FieldDTO {
    int r; // row index
    int c; // col index
    State action; // change state to


    enum State {
        PRESSED,
        FLAGGED,
        UNFLAGGED
    }

    public FieldDTO(int _r, int _c, State _action) {
        r = _r;
        c = _c;
        action = _action;
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
                this.r +
                "|" +
                this.c;
    }

    public String toString() {
        return "(" + r + ", " + c + ") " + action;
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
        FieldDTO fieldDTO1 = new FieldDTO(12,24, State.FLAGGED);
        FieldDTO fieldDTO2 = new FieldDTO(37,1, State.PRESSED);
        FieldDTO fieldDTO3 = new FieldDTO(420,69, State.UNFLAGGED);

        testDataProcess(fieldDTO1);
        testDataProcess(fieldDTO2);
        testDataProcess(fieldDTO3);
    }
}
