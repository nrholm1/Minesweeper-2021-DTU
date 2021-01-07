import Model.Board;
import Model.Util.BoardBuilder;

public class MinesweeperTest {
    public static void testFieldRepresentation() {
        int rowLength = 25;
        int colLength = 20;
        int amountMines = 20;
        Board board = new BoardBuilder().withRowLength(rowLength)
                                        .withColLength(colLength)
                                        .withAmountMines(amountMines)
                                        .build();
        board.makeMinefieldWithDimensions();
        board.printMinefield();
    }

    public static void main(String[] args) {
        testFieldRepresentation();
    }
}
