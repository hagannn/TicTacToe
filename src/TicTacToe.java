import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) throws IOException {
        boolean playing = true;
        String player1Symbol = "X";
        String player2Symbol = "O";
        int player1Move[];
        int player2Move[];
        int turn = 0;
        clearBoard();

        while (playing) {
            display();

            if (turn % 2 == 0) {
                player1Move = getMove(player1Symbol);
                board[player1Move[0]][player1Move[1]] = player1Symbol;

            } else {
                player2Move = getMove(player2Symbol);
                board[player2Move[0]][player2Move[1]] = player2Symbol;
            }

            // Setting turn to -1 because +1 will be added to it
            if (isWin(player1Symbol)) {
                display();
                System.out.printf("Player %s wins\n", player1Symbol);
                clearBoard();
                turn = -1;
                playing = goAgain();
            } else if (isWin(player2Symbol)) {
                display();
                System.out.printf("Player %s wins\n", player2Symbol);
                clearBoard();
                turn = -1;
                playing = goAgain();
            } else if (isTie()) {
                display();
                System.out.println("It is a tie");
                clearBoard();
                turn = -1;
                playing = goAgain();
            }

            turn += 1;

        }

    }

    private static int[] getMove(String playerSymbol) throws IOException {
        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);

        System.out.printf("Player %s enter row number: ", playerSymbol);
        String rowInput = br.readLine();

        System.out.printf("Player %s enter col number: ", playerSymbol);
        String colInput = br.readLine();

        int row = 0;
        int col = 0;

        try {
            row = Integer.parseInt(rowInput) - 1;
            col = Integer.parseInt(colInput) - 1;
        } catch (Exception e) {
            System.out.println("Invalid input, try again");
            return getMove(playerSymbol);
        }

        if (!isValidMove(row, col)) {
            System.out.println("Invalid move, try again");
            return getMove(playerSymbol);
        }

        if (board[row][col] != " ") {
            System.out.println("Already symbol at space, try again");
            return getMove(playerSymbol);
        }
        return new int[]{row, col};
    }

    private static boolean goAgain() throws IOException{
        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);

        System.out.print("Would you like to play again? (y/n): ");

        String userInput = br.readLine();
        userInput = userInput.toLowerCase();

        if (userInput.equals("y")) {
            return true;
        } else if (userInput.equals("n")) {
            return false;
        } else {
            System.out.println("Invalid option");
            return goAgain();
        }

    }

    // Clears the board and inits all spaces with " "
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Displays the board as text format
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

    // Makes sure the move is valid
    private static boolean isValidMove(int row, int col) {
        if ((row > 2 || row < 0) || (col > 2 || col < 0)) {
            return false;
        }
        return true;
    }

    // Checks all conditions of a win for a player
    private static boolean isWin(String player) {
        if (isColWin(player) | isRowWin(player) | isDiagnalWin(player)) {
            return true;
        }
        return false;
    }

    // Checks if any cols have a player win
    private static boolean isColWin(String player) {
        String falttennCols[] = new String[COLS*ROWS+3];

        int count = 0;
        for (int i = 0; i < COLS; i++) {
            for (int y = 0; y < ROWS; y++) {
                falttennCols[count] = board[y][i];
                count += 1;
            }
            falttennCols[count] = "|";
            count += 1;
        }

        int occur = 0;
        String prev = "";
        for (String move : falttennCols) {
            if (!move.equals(prev) | move.equals(" ") | prev.equals("")) {
                occur = 1;
            }
            else if (move.equals(player)) {
                occur += 1;
            }

            if (occur == 3) {
                return true;
            }
            prev = move;
        }

        return false;

    }

    // Checks all the rows for a player win
    private static boolean isRowWin(String player) {
        String falttennRows[] = new String[COLS*ROWS+3];

        int count = 0;
        for (int i = 0; i < COLS; i++) {
            for (int y = 0; y < ROWS; y++) {
                falttennRows[count] = board[i][y];
                count += 1;
            }
            falttennRows[count] = "|";
            count += 1;
        }


        int occur = 0;
        String prev = "";
        for (String move : falttennRows) {
            if (!move.equals(prev) | move.equals(" ") | prev.equals("")) {
                occur = 1;
            }
            else if (move.equals(player)){
                occur += 1;
            }

            if (occur == 3) {
                return true;
            }
            prev = move;
        }

        return false;
    }

    // Checks all diagnals for a player win
    private static boolean isDiagnalWin(String player) {
        String faltennDiagnals[] = new String[COLS+ROWS+2];

        int count = 0;
        for (int y = 0; y < 2; y++) {
            for (int i = 0; i < 3; i++) {
                if (count > 2) {
                    faltennDiagnals[count] = board[i][(count-i*2-2)];
                } else {
                    faltennDiagnals[count] = board[i][i];
                }
                count += 1;
            }

            faltennDiagnals[count] = "|";
            count += 1;
        }

        int occur = 0;
        String prev = "";
        for (String move : faltennDiagnals) {

            if (!move.equals(prev) | move.equals(" ") | prev.equals("")) {
                occur = 1;
            }
            else if (move.equals(player)) {
                occur += 1;
            }

            if (occur == 3) {
                return true;
            }
            prev = move;

        }

        return false;
    }

    // Checks to see if the board is a tie
    private static boolean isTie() {
        for (String[] row : board) {
            for (String move : row) {
                if (move.equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }

}
