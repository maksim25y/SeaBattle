import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player();
        player1.generateBoard();
        System.out.println();
        Player player2 = new Player();
        player2.generateBoard();
        boolean haveWinner = false;
        while (!haveWinner){
            boolean shotPlayer1 = true;
            while (shotPlayer1){
                System.out.print("Игрок 1 введите координаты: ");
                String powerOfPlayer1 = scanner.nextLine();
                shotPlayer1 = player2.getAttack(powerOfPlayer1);
            }
            boolean shotPlayer2 = true;
            while (shotPlayer2){
                System.out.print("Игрок 2 введите координаты: ");
                String powerOfPlayer2 = scanner.nextLine();
                shotPlayer2 = player1.getAttack(powerOfPlayer2);
            }
        }
    }
}