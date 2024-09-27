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
                if (!findPath(battlefield,powerX, powerY)) {
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
                break;
            case ' ':
                System.out.println("\033[35mМимо!\033[0m");
                battlefield[powerX][powerY]='x';
                break;
            default:
                System.out.println("\033[34mВы уже стреляли в данную клетку!\033[0m");
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (battlefield[i][j] == 'k') {
                    markAround(i, j);
                }
            }
        }
        return true;
    }
    public boolean findPath(char[][] matrix, int startX, int startY) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Проверка, что начальные координаты в пределах матрицы
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
                printElement(battlefield[i][j]);
            }
            System.out.println();
        }
    }
    private void printElement(char element){
        if(element=='s'){
            System.out.print("\033[33ms\033[0m"+"|");
        }
        if(element==' '){
            System.out.print(element+"|");
        }
        if(element=='x'){
            System.out.print("\033[36mx\033[0m"+"|");
        }
        if(element=='k'){
            System.out.print("\033[31mk\033[0m"+"|");
        }
    }
}