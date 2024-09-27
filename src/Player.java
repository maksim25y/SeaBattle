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
        generateShipByCountAndSize(1,4);
        generateShipByCountAndSize(2,3);
        generateShipByCountAndSize(3,2);
        generateShipByCountAndSize(4,1);
        printBoard();
    }
    private void generateShipByCountAndSize(int size,int count){
        for(int i=0;i<count;i++){
            shipsGenerator.arrangeShips(battlefield,size);
        }
    }
    public boolean getAttack(String power){
        int powerX = Integer.parseInt(power.substring(1, 2));;
        int powerY = Util.mapOfNumbers.get(power.charAt(0));
        char el = battlefield[powerX][powerY];
        switch (el){
            case 's':
                System.out.println("\033[31mПопадание!\033[0m");
                battlefield[powerX][powerY]='k';
                if (!findPath(battlefield,powerX, powerY)) {
                    markShipAsDestroyed();
                }
                return true;
            case ' ':
                System.out.println("\033[35mМимо!\033[0m");
                battlefield[powerX][powerY]='x';
                return false;
            default:
                System.out.println("\033[34mВы уже стреляли в данную клетку!\033[0m");
                return true;
        }
    }
    private void markShipAsDestroyed(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (battlefield[i][j] == 'k') {
                    markAround(i, j);
                }
            }
        }
        countOfShips--;
        System.out.println("Осталось кораблей: "+countOfShips);
        System.out.println("\033[31mКорабль уничтожен!\033[0m");
    }
    public boolean isLost(){
        return countOfShips==0;
    }
    public boolean findPath(char[][] matrix, int startX, int startY) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (startX < 0 || startX >= rows || startY < 0 || startY >= cols) {
            return false;
        }
        boolean[][] visited = new boolean[rows][cols];
        return dfs(matrix, visited, startX, startY);
    }

    private boolean dfs(char[][] matrix, boolean[][] visited, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] == ' '||matrix[row][col]=='x') {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        if (matrix[row][col] == 's') {
            return true;
        }
        for (int[] dir : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
            if (dfs(matrix, visited, row + dir[0], col + dir[1])) {
                return true;
            }
        }
        return false;
    }
    private void markAround(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isValidCoordinate(i, j) && battlefield[i][j] == ' ') {
                    battlefield[i][j] = 'x';
                }
            }
        }
    }
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
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
                printElement(battlefield[i][j]);
            }
            System.out.println();
        }
    }
    private void printElement(char element){
        if(element!=' '){
            System.out.print("\033["+Util.COLOR_CODES.get(element)+"m"+element+"\033[0m"+"|");
        }else{
            System.out.print(element+"|");
        }
    }
}