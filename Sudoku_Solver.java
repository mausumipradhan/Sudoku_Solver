import java.util.Scanner;

public class SudokuSolver {

    private static int GRID_SIZE;
    private static int BOX_SIZE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input grid size
        while (true) {
            System.out.print("Enter the grid size (must be a perfect square, e.g. 4, 9, 16): ");
            GRID_SIZE = scanner.nextInt();
            BOX_SIZE = (int) Math.sqrt(GRID_SIZE);
            if (BOX_SIZE * BOX_SIZE == GRID_SIZE) {
                break;
            } else {
                System.out.println("❌ Grid size must be a perfect square.");
            }
        }

        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        scanner.nextLine(); // consume leftover newline

        System.out.println("\nEnter the Sudoku puzzle row by row (use 0 for empty cells):");

        for (int row = 0; row < GRID_SIZE; row++) {
            while (true) {
                System.out.print("Row " + (row + 1) + ": ");
                String line = scanner.nextLine();
                String[] tokens = line.trim().split("\\s+");

                if (tokens.length != GRID_SIZE) {
                    System.out.println("⚠  Please enter exactly " + GRID_SIZE + " numbers separated by spaces.");
                    continue;
                }

                boolean validRow = true;
                for (int col = 0; col < GRID_SIZE; col++) {
                    try {
                        board[row][col] = Integer.parseInt(tokens[col]);
                        if (board[row][col] < 0 || board[row][col] > GRID_SIZE) {
                            validRow = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        validRow = false;
                        break;
                    }
                }

                if (!validRow) {
                    System.out.println("⚠  Please enter only numbers between 0 and " + GRID_SIZE);
                } else {
                    break;
                }
            }
        }

        scanner.close();

        System.out.println("\nOriginal Sudoku:");
        printBoard(board);

        if (solveBoard(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("\nUnsolvable Sudoku!");
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % BOX_SIZE;
        int localBoxCol = column - column % BOX_SIZE;

        for (int i = localBoxRow; i < localBoxRow + BOX_SIZE; i++) {
            for (int j = localBoxCol; j < localBoxCol + BOX_SIZE; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
               !isNumberInColumn(board, number, column) &&
               !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false; // backtrack
                }
            }
        }
        return true; // board solved
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % BOX_SIZE == 0 && row != 0) {
                System.out.println("-".repeat(GRID_SIZE * 3 + BOX_SIZE - 1));
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % BOX_SIZE == 0 && col != 0) {
                    System.out.print(" |");
                }
                System.out.printf(" %s", (board[row][col] == 0 ? "." : board[row][col]));
            }
            System.out.println();
        }
    }
}