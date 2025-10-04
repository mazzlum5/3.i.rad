// TicTacToeGame.java
import java.util.Scanner;

public class main {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Scanner scanner;

    public main() {
        scanner = new Scanner(System.in);
        initializePlayers();
        startGameLoop();
    }

    private void initializePlayers() {
        System.out.print("Enter name for Player 1: ");
        String nameX = scanner.nextLine();
        playerX = new Player(nameX, 'X');

        System.out.print("Enter name for Player 2: ");
        String nameO = scanner.nextLine();
        playerO = new Player(nameO, 'O');
    }

    private void startGameLoop() {
        while (true) {
            board = new Board();
            Player currentPlayer = playerX;
            boolean gameWon = false;

            while (!board.isFull()) {
                board.display();
                System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), choose a cell (1-9):");

                int move = getValidMove();
                if (!board.placeSymbol(move, currentPlayer.getSymbol())) {
                    System.out.println("That cell is already taken. Try again.");
                    continue;
                }

                if (board.checkWin(currentPlayer.getSymbol())) {
                    board.display();
                    System.out.println(currentPlayer.getName() + " wins!");
                    currentPlayer.incrementWins();
                    gameWon = true;
                    break;
                }

                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }

            if (!gameWon) {
                board.display();
                System.out.println("It's a draw!");
            }

            displayScore();
            System.out.println("\nStarting a new game...\n");
        }
    }

    private int getValidMove() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int move = Integer.parseInt(input);
                if (move >= 1 && move <= 9) {
                    return move;
                } else {
                    System.out.println("Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number between 1 and 9.");
            }
        }
    }

    private void displayScore() {
        System.out.println("\nCurrent Score:");
        System.out.println(playerX.getName() + " (X): " + playerX.getWins());
        System.out.println(playerO.getName() + " (O): " + playerO.getWins());
    }

    public static void main(String[] args) {
        new main();
    }
}
