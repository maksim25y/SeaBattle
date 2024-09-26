import java.util.Arrays;

public class Player {
    private final char[][]battlefield;
    private int countOfShips = 10;
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
            char el = battlefield[powerX][powerY];
            switch (el){
                case 's':
                    System.out.println("Попадание!");
                    battlefield[powerX][powerY]='k';
                    if (isShipDestroyed(powerX, powerY)) {
                        countOfShips--;
                        System.out.println("Осталось кораблей: "+countOfShips);
                        System.out.println("Корабль уничтожен!");
                    }
                    break;
                case ' ':
                    System.out.println("Мимо!");
                    battlefield[powerX][powerY]='x';
                    break;
                default:
                    System.out.println("Вы уже стреляли в данную клетку!");
                    return true;
            }
            System.out.println("Доска игрока 1:");
            return el=='s';
    }
    public boolean isLost(){
        return countOfShips==0;
    }
    private boolean isShipDestroyed(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isValidCoordinate(i, j) && battlefield[i][j] == 's') {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < battlefield.length && y >= 0 && y < battlefield[0].length;
    }
    public void printBoard(){
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
