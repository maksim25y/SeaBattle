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
                if(!player1.isLost()){
                    System.out.print("Игрок 1 введите координаты: ");
                    String powerOfPlayer1 = scanner.nextLine();
                    shotPlayer1 = player2.getAttack(powerOfPlayer1);
                    System.out.println("Доска игрока 2:");
                    player2.printBoard();
                    boolean isLost = player2.isLost();
                    if(isLost){
                        System.out.println("Игрок 1 победил :)");
                        haveWinner=true;
                        break;
                    }
                }else {
                    break;
                }
            }
            boolean shotPlayer2 = true;
            while (shotPlayer2){
                if(!player2.isLost()){
                    System.out.print("Игрок 2 введите координаты: ");
                    String powerOfPlayer2 = scanner.nextLine();
                    shotPlayer2 = player1.getAttack(powerOfPlayer2);
                    System.out.println("Доска игрока 1:");
                    player1.printBoard();
                    boolean isLost = player1.isLost();
                    if(isLost){
                        System.out.println("Игрок 2 победил :)");
                        haveWinner=true;
                        break;
                    }
                }else {
                    break;
                }
            }
        }
        System.out.println("Игра окончена!");
    }
}