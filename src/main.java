
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
        System.out.print("Do you want to play against the computer? (y/n): ");
        String vsComputer = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter name for Player 1: ");
        String nameX = scanner.nextLine();
        playerX = new Player(nameX, 'X');

        if (vsComputer.equals("j")) {
            playerO = new ComputerPlayer('O');
        } else {
            System.out.print("Enter name for Player 2: ");
            String nameO = scanner.nextLine();
            playerO = new Player(nameO, 'O');
        }
    }


    private void startGameLoop() {
        boolean playAgain = true;

        while (playAgain) {
            board = new Board();
            Player currentPlayer = playerX;
            boolean gameWon = false;

            while (!board.isFull()) {
                board.display();

                int move;

                if (currentPlayer instanceof ComputerPlayer) {
                    System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") is thinking...");
                    try {
                        Thread.sleep(1000); // Gör så att datorn "tänker"
                    } catch (InterruptedException e) {
                        // ignorera
                    }
                    move = ((ComputerPlayer) currentPlayer).chooseMove(board);
                    System.out.println("Computer chose: " + move);
                } else {
                    System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), choose a cell (1-9):");
                    move = getValidMove();
                }

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
            System.out.print("\nPlay igen? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("j");
        }

        System.out.println("\n👋 Thank you for playing!");

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
