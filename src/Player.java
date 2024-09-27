import java.util.Arrays;

public class Player {
    private final int LENGTH = 10;
    private final char[][]battlefield;
    private final char[][]battlefieldForShow;
    private int countOfShips = 10;
    private final String name;
    private final ShipsGenerator shipsGenerator = new ShipsGenerator();
    public Player(String name) {
        this.battlefieldForShow=new char[LENGTH][LENGTH];
        battlefield=new char[LENGTH][LENGTH];
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void generateBoard(){
        for (char[] chars : battlefield) {
            Arrays.fill(chars, ' ');
        }
        for (char[] chars : battlefieldForShow) {
            Arrays.fill(chars, ' ');
        }
        generateShipByCountAndSize(1,4);
        generateShipByCountAndSize(2,3);
        generateShipByCountAndSize(3,2);
        generateShipByCountAndSize(4,1);
        addValuesFromBattlefieldToBattlefieldForShow();
    }
    private void addValuesFromBattlefieldToBattlefieldForShow(){
        for(int i=0;i<LENGTH;i++){
            for(int j=0;j<LENGTH;j++){
                if(battlefield[i][j]!='s') {
                    battlefieldForShow[i][j] = battlefield[i][j];
                }
            }
        }
    }
    private void generateShipByCountAndSize(int size,int count){
        for(int i=0;i<count;i++){
            shipsGenerator.arrangeShips(battlefield,size);
        }
    }
    public boolean getAttack(String power){
        if(!isValidPower(power)){
            System.out.println("\033[34mНекорректный формат ввода координат!\033[0m");
            return false;
        }
        int powerX = Integer.parseInt(power.substring(1, 2));
        int powerY = Util.mapOfNumbers.get(power.charAt(0));
        char el = battlefield[powerX][powerY];
        switch (el){
            case 's':
                System.out.println("\033[31mПопадание!\033[0m");
                battlefield[powerX][powerY]='k';
                if (!findPath(powerX, powerY)) {
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
    private boolean isValidPower(String power){
        try{
            Integer.parseInt(power.substring(1, 2));
            if(!Util.mapOfNumbers.containsKey(power.charAt(0))){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    private void markShipAsDestroyed(){
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
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
    public boolean findPath(int startX, int startY) {
        if (!isValidCoordinate(startX,startY)) {
            return false;
        }
        boolean[][] visited = new boolean[LENGTH][LENGTH];
        return dfs(visited, startX, startY);
    }

    private boolean dfs(boolean[][] visited, int row, int col) {
        if (row < 0 || row >= LENGTH || col < 0 || col >= LENGTH || battlefield[row][col] == ' '||battlefield[row][col]=='x') {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        if (battlefield[row][col] == 's') {
            return true;
        }
        for (int[] dir : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
            if (dfs(visited, row + dir[0], col + dir[1])) {
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
        return x >= 0 && x < LENGTH  && y >= 0 && y < LENGTH;
    }
    public void printBoard(){
        addValuesFromBattlefieldToBattlefieldForShow();
        System.out.print(" |");
        for(Character letter: Util.listOfLetters){
            System.out.print(letter+"|");
        }
        System.out.println();
        for(int i=0;i<LENGTH;i++){
            System.out.print(i+" ");
            for(int j=0;j<LENGTH;j++){
                printElement(battlefieldForShow[i][j]);
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