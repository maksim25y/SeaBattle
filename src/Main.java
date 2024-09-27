import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя игрока 1: ");
        String player1Name = scanner.nextLine();
        Player player1 = new Player(player1Name);
        player1.generateBoard();
        System.out.print("Введите имя игрока 2: ");
        String player2Name = scanner.nextLine();
        Player player2 = new Player(player2Name);
        player2.generateBoard();
        Game game = new Game();
        boolean haveWinner = false;
        while (!haveWinner){
            haveWinner = game.isHaveWinner(scanner, player2, player1, haveWinner);
            haveWinner = game.isHaveWinner(scanner, player1, player2, haveWinner);
        }
        System.out.println("Игра окончена!");
    }
}