package Networking;

import Model.Field;

import java.nio.charset.StandardCharsets;

public abstract class Parser {
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
        String gameState = dataParams[4];

        System.out.println(new FieldDTO(r,c,action,tileText,gameState));

        return new FieldDTO(r, c, action, tileText, gameState);
    }

    public static String[] getParamArray(String s) {
        String action = s.substring(0,1);
        StringBuilder r = new StringBuilder();
        StringBuilder c = new StringBuilder();
        StringBuilder tileText = new StringBuilder();
        StringBuilder gameState = new StringBuilder();

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
            if (counter == 4 && ch != '|')
                gameState.append(ch);
        }

        return new String[] {action,
                             r.toString(),
                             c.toString(),
                             tileText.toString(),
                             gameState.toString()};
    }

    public static String deserializeFieldDTO(byte[] dataBytes) {
        return new String(dataBytes, StandardCharsets.UTF_8);
    }

}
