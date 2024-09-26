import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Character>listOfLetters = List.of(
            'A','B','C','D','E','F','G','H','I','J'
    );
    public static void main(String[] args) {
        ShipsGenerator shipsGenerator = new ShipsGenerator();
        char[][]battlefield = new char[10][10];
        for (char[] chars : battlefield) {
            Arrays.fill(chars, ' ');
        }
        for(int i=0;i<4;i++){
            shipsGenerator.arrangeShips(battlefield,1);
        }
        for(int i=0;i<3;i++){
            shipsGenerator.arrangeShips(battlefield,2);
        }
        for(int i=0;i<2;i++){
            shipsGenerator.arrangeShips(battlefield,3);
        }
        shipsGenerator.arrangeShips(battlefield,4);
        System.out.print(" |");
        for(Character letter:listOfLetters){
            System.out.print(letter+"|");
        }
        System.out.println();
        for(int i=0;i<10;i++){
            System.out.print(i+" ");
            for(int j=0;j<10;j++){
                System.out.print(battlefield[i][j]+"|");
            }
            System.out.println();
        }
    }
}