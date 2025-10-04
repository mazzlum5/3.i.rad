import java.util.Scanner;


public class Main {

    Player playerX;
    Player player0;
    Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        initializePlayers();
    }

    public void initializePlayers() {
        System.out.print("Skriv namnet för Player X:");
        String nameX = scanner.nextLine();
        playerX = new Player(nameX, 'X');
    }

    }