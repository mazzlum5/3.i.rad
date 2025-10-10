import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random = new Random();

    public ComputerPlayer(char symbol) {
        super("Computer", symbol);
    }

    public int chooseMove(Board board) {
        int move;
        do {
            move = random.nextInt(9) + 1;
        } while (!board.isCellEmpty(move));

        return move;
    }
}
