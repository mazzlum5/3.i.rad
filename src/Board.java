
public class Board {
    private char[] cells;


    public Board() {
        cells = new char[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = ' ';
        }
    }

    public boolean isCellEmpty(int position) {
        return cells[position - 1] == ' ';
    }


    public void display() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            char c = cells[i];
            String symbol;

            if (c == ' ') {
                symbol = String.valueOf(i + 1);
            } else if (c == 'X') {
                symbol = "❌";
            } else if (c == 'O') {
                symbol = "⭕️";
            } else {
                symbol = String.valueOf(c);
            }

            System.out.print(" " + symbol + " ");

            if ((i + 1) % 3 == 0) {
                if (i < 8) {
                    System.out.println("\n---+---+---");
                }
            } else {
                System.out.print("|");
            }
        }
        System.out.println("\n");
    }

    public boolean placeSymbol(int position, char symbol) {
        if (cells[position - 1] == ' ') {
            cells[position - 1] = symbol;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (char cell : cells) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWin(char symbol) {
        int[][] winPatterns = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] pattern : winPatterns) {
            if (cells[pattern[0]] == symbol &&
                    cells[pattern[1]] == symbol &&
                    cells[pattern[2]] == symbol) {
                return true;
            }
        }

        return false;
    }
}
