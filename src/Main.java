import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Character>listOfLetters = List.of(
            'A','B','C','D','E','F','G','H','I','J'
    );
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player();
        player1.generateBoard();
        System.out.println();
        Player player2 = new Player();
        player2.generateBoard();
        boolean haveWinner = false;
        while (!haveWinner){
            System.out.print("Игрок 1 введите координаты: ");
            String powerOfPlayer1 = scanner.nextLine();
            //Реализовать атаку и обработку попаданий
            System.out.print("Игрок 2 введите координаты: ");
            String powerOfPlayer2 = scanner.nextLine();
            //Реализовать атаку и обработку попаданий
        }
    }
}