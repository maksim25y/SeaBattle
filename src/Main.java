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
        for(int i=0;i<4;i++){
            shipsGenerator.arrangeShips4(battlefield,1);
        }
        for(int i=0;i<3;i++){
            shipsGenerator.arrangeShips4(battlefield,2);
        }
        for(int i=0;i<2;i++){
            shipsGenerator.arrangeShips4(battlefield,3);
        }
        shipsGenerator.arrangeShips4(battlefield,4);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(battlefield[i][j]+"|");
            }
            System.out.println();
        }
    }
}