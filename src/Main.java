import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player("Максим");
        player1.generateBoard();
        System.out.println();
        Player player2 = new Player("Иван");
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