package Networking;

import java.nio.charset.StandardCharsets;

public class Parser {
    public static FieldDTO readRequestInput(byte[] dataBytes) {
        return parseFieldDTOString(deserializeFieldDTO(dataBytes));
    }

    public static FieldDTO parseFieldDTOString(String dataString) {
        String[] dataParams = getParamArray(dataString);

        FieldDTO.State action = switch (dataParams[0]) {
            case "P" -> FieldDTO.State.PRESSED;
            case "F" -> FieldDTO.State.FLAGGED;
            case "U" -> FieldDTO.State.UNFLAGGED;
            default -> throw new IllegalArgumentException("bruh");
        };
        int r = Integer.parseInt(dataParams[1]);
        int c = Integer.parseInt(dataParams[2]);

        return new FieldDTO(r, c, action);
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

    public static String deserializeFieldDTO(byte[] dataBytes) {
        return new String(dataBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        FieldDTO data = new FieldDTO(1,2, FieldDTO.State.FLAGGED);
        byte[] bytes = data.toBytes();

        FieldDTO reqFieldDTO = readRequestInput(bytes);

        System.out.println(reqFieldDTO);
    }
}
