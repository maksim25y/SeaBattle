import java.util.Arrays;

public class Player {
    private final char[][]battlefield;
    private final ShipsGenerator shipsGenerator = new ShipsGenerator();
    public Player() {
        battlefield=new char[10][10];
    }
    public void generateBoard(){
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
        printBoard();
    }
    public boolean getAttack(String power){
            int powerX = Integer.parseInt(power.charAt(1)+"");
            int powerY = Util.mapOfNumbers.get(power.charAt(0));
            return battlefield[powerX][powerY]=='s';
    }
    private void printBoard(){
        System.out.print(" |");
        for(Character letter: Util.listOfLetters){
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
