package Networking;

import Model.Field;

import java.nio.charset.StandardCharsets;

public class Parser {
    public static Field readRequestInput(byte[] dataBytes) {
        return parseFieldString(deserializeField(dataBytes));
    }

    public static Field parseFieldString(String dataString) {
        String[] dataParams = getParamArray(dataString);

        Field.State action = switch (dataParams[0]) {
            case "P" -> Field.State.PRESSED;
            case "F" -> Field.State.FLAGGED;
            case "U" -> Field.State.UNFLAGGED;
            default -> throw new IllegalArgumentException("bruh");
        };
        int r = Integer.parseInt(dataParams[1]);
        int c = Integer.parseInt(dataParams[2]);

        return new Field(r, c, action);
    }

    public static String[] getParamArray(String s) {
        String action = s.substring(0,1);
        StringBuilder r = new StringBuilder();
        StringBuilder c = new StringBuilder();

        int counter = 0;
        for(char ch : s.toCharArray()) {
            if (ch == '|')
                counter++;
            if (counter == 1 && ch != '|')
                r.append(ch);
            if (counter == 2 && ch != '|')
                c.append(ch);
        }

        return new String[] {action,
                             r.toString(),
                             c.toString()};
    }

    public static String deserializeField(byte[] dataBytes) {
        return new String(dataBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        Field data = new Field(1,2);
        byte[] bytes = data.toBytes();

        Field reqField = readRequestInput(bytes);

        System.out.println(reqField);
    }
}
