public class Player {
    String name;
    char symbol;
    int wins;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }
    public char getSymbol() {
        return symbol;
    }

    public int getWins() {
        return wins;
    }

    public void incrementsWins() {
        wins++;
    }
}
