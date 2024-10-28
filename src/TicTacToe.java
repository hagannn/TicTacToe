public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) {
        clearBoard();
        display();

    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        String boardText = "      1     2     3\n" +
                     "   ┌─────┬─────┬─────┐\n" +
                     "1  │ {0} │ {1} │ {2} │\n" +
                     "   ├─────┼─────┼─────┤\n" +
                     "2  │ {3} │ {4} │ {5} │\n" +
                     "   ├─────┼─────┼─────┤\n" +
                     "3  │ {6} │ {7} │ {8} │\n" +
                     "   └─────┴─────┴─────┘";
        int i = 0;
        for (String[] row : board) {
            for (String col : row) {
                boardText = boardText.replace("{"+i+"}", " " + col + " ");
                i += 1;
            }
        }
        System.out.println(boardText);
    }
    /*
    private static boolean isValidMove(int row, int col) {

    }

    private static boolean isWin(String player) {

    }

    private static boolean isColWin(String player) {

    }

    private static boolean isRowWin(String player) {

    }

    private static boolean isDiagnalWin(String player) {

    }

    private static boolean isTie() {

    }
     */
}