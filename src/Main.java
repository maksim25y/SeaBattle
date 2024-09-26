import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ShipsGenerator shipsGenerator = new ShipsGenerator();
        char[][]battlefield = new char[10][10];
        for (char[] chars : battlefield) {
            Arrays.fill(chars, ' ');
        }
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(battlefield[i][j]+"|");
            }
            System.out.println();
        }
        System.out.println();
        shipsGenerator.arrangeShips4(battlefield);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(battlefield[i][j]+"|");
            }
            System.out.println();
        }
    }
}