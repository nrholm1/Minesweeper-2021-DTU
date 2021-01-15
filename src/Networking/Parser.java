package Networking;

import Model.Field;

import java.nio.charset.StandardCharsets;

public class Parser {
    public static FieldDTO readRequestInput(byte[] dataBytes) {
        return parseFieldDTOString(deserializeFieldDTO(dataBytes));
    }

    public static FieldDTO parseFieldDTOString(String dataString) {
        String[] dataParams = getParamArray(dataString);

        Field.State action = switch (dataParams[0]) {
            case "P" -> Field.State.PRESSED;
            case "F" -> Field.State.FLAGGED;
            case "U" -> Field.State.UNFLAGGED;
            default -> throw new IllegalArgumentException("bruh");
        };
        int r = Integer.parseInt(dataParams[1]);
        int c = Integer.parseInt(dataParams[2]);
        String tileText = dataParams[3];


        return new FieldDTO(r, c, action, tileText);
    }

    public static String[] getParamArray(String s) {
        String action = s.substring(0,1);
        StringBuilder r = new StringBuilder();
        StringBuilder c = new StringBuilder();
        StringBuilder tileText = new StringBuilder();

        int counter = 0;
        for(char ch : s.toCharArray()) {
            if (ch == '|')
                counter++;
            if (counter == 1 && ch != '|')
                r.append(ch);
            if (counter == 2 && ch != '|')
                c.append(ch);
            if (counter == 3 && ch != '|')
                tileText.append(ch);
        }

        return new String[] {action,
                             r.toString(),
                             c.toString(),
                             tileText.toString()};
    }

    public static String deserializeFieldDTO(byte[] dataBytes) {
        return new String(dataBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        FieldDTO data = new FieldDTO(13, 21, Field.State.FLAGGED, "X");

        String s = data.toParsableString();
        byte[] bytes = data.toBytes();
        FieldDTO parsedDto = readRequestInput(bytes);

        System.out.println(s);
        System.out.println(parsedDto);
    }
}
