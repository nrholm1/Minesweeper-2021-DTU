import MinesweeperTests.Board;
import MinesweeperTests.BoardBuilder;

public class MinesweeperTest {
    public static void testFieldRepresentation() {
        int rowLength = 25;
        int colLength = 20;
        double probability = 0.15;
        Board board = new BoardBuilder().withRowLength(rowLength)
                                        .withColLength(colLength)
                                        .withProbability(probability)
                                        .build();
        board.makeMinefieldWithDimensions();
        board.printMinefield();
    }

    public static void main(String[] args) {
        testFieldRepresentation();
    }
}
