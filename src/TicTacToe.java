import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;

    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String player;
        boolean playAgain;

        do {
            // Pseudocode for the tic-tac-toe class
            // 1. Create a 3x3 board (2D array) to represent the game state.
            // 2. Implement a method to display the current state of the board
            // 3. Implement a method to check for a win condition (three in a row, column, or diagonal).
            // 4. Implement a method to check for a draw condition
            // 5. Implement a method to allow players to make moves (input row and column).
            // 6. Implement a method to switch between players (X and O).
            // 7. Implement a method to start the game loop, alternating turns between players until a win or draw condition is met.
            // 8. Implement a method to reset the game board for a new game.
            // 9. Implement a method to display the winner or indicate a draw at the end of the game.
            // 10. Implement a method to handle invalid moves (e.g., trying to place a mark in an already occupied cell).
            // 11. Implement a method to allow players to choose their symbols (X or O) at the start of the game.
            // 12. Implement a method to keep track of the score for each player across multiple games.
            // 13. Implement a method to allow players to quit the game at any time.
            // 14. Implement a method to display the current score after each game.

            clearBoard();

            player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {

                display();

                System.out.println("Player " + player + "'s turn.");

                int rowMove = SafeInput.getRangedInt(input, "Enter a row (1-3)", 1, ROWS);
                int colMove = SafeInput.getRangedInt(input, "Enter a column (1-3)", 1, COLS);

                int row = rowMove - 1;
                int col = colMove - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("Space already occupied. Try again.");
                    rowMove = SafeInput.getRangedInt(input, "Enter a row (1-3)", 1, ROWS);
                    colMove = SafeInput.getRangedInt(input, "Enter a column (1-3)", 1, COLS);
                    row = rowMove - 1;
                    col = colMove - 1;
                }

                board[row][col] = player;
                moveCount++;

                if (isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (isTie()) {
                    display();
                    System.out.println("The game is a tie!");
                    gameOver = true;
                } else {
                    player = (player.equals("X")) ? "O" : "X";
                }
            }

            playAgain = SafeInput.getYNConfirm(input, "Do you want to play again?");
        } while (playAgain);

        System.out.println("Thanks for playing!");

        input.close();
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {

        System.out.println();

        for (int row = 0; row < ROWS; row++) {

            System.out.print(" ");

            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);

                if (col < COLS - 1) {
                    System.out.print(" | ");
                }
            }

            System.out.println();

            if (row < ROWS - 1) {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {

        for (int row = 0; row < ROWS; row++) {

            boolean win = true;

            for (int col = 0; col < COLS; col++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }

            if (win) {
                return true;
            }
        }

        return false;
    }

    private static boolean isColWin(String player) {

        for (int col = 0; col < COLS; col++) {

            boolean win = true;

            for (int row = 0; row < ROWS; row++) {

                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {

        boolean diagonal1 = true;

        for (int i = 0; i < ROWS; i++) {
            if (!board[i][i].equals(player)) {
                diagonal1 = false;
            }
        }

        boolean diagonal2 = true;

        for (int i = 0; i < COLS; i++) {
            if (!board[i][COLS - 1 - i].equals(player)) {
            }
            diagonal2 = false;
        }

        return diagonal1 || diagonal2;

    }

    private static boolean canStillWin(String player,
                                       int r1, int c1,
                                       int r2, int c2,
                                       int r3, int c3) {
        String opponent;

        if (player.equals("X")) {
            opponent = "O";
        } else {
            opponent = "X";
        }

        return !board[r1][c1].equals(opponent) &&
                !board[r2][c2].equals(opponent) &&
                !board[r3][c3].equals(opponent);
    }

    private static boolean isTie() {

        for (int row = 0; row < ROWS; row++) {
            if (canStillWin("X", row, 0, row, 1, row, 2) ||
                    canStillWin("O", row, 0, row, 1, row, 2)) {
                return false;
            }
        }

        for (int col = 0; col < COLS; col++) {
            if (canStillWin("X", 0, col, 1, col, 2, col) ||
                    canStillWin("O", 0, col, 1, col, 2, col)) {
                return false;
            }
        }

        if (canStillWin("X", 0, 0, 1, 1, 2, 2) ||
                canStillWin("O", 0, 0, 1, 1, 2, 2)) {
            return false;
        }

        if (canStillWin("X", 0, 2, 1, 1, 2, 0) ||
                canStillWin("O", 0, 2, 1, 1, 2, 0)) {
            return false;
        }

        return true;
    }
}