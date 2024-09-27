import java.util.Scanner;

public class Game {
    public boolean isHaveWinner(Scanner scanner, Player player1, Player player2, boolean haveWinner) {
        boolean shotPlayer2 = true;
        while (shotPlayer2){
            if(!player2.isLost()){
                System.out.print(player2.getName()+" введите координаты: ");
                String powerOfPlayer2 = scanner.nextLine();
                shotPlayer2 = player1.getAttack(powerOfPlayer2);
                System.out.println("Доска игрока "+player1.getName());
                player1.printBoard();
                boolean isLost = player1.isLost();
                if(isLost){
                    System.out.println(player2.getName()+" победил :)");
                    haveWinner=true;
                    break;
                }
            }else {
                break;
            }
        }
        return haveWinner;
    }
}
