import java.util.Random;

public class ShipsGenerator {
    private final Random random = new Random();
    private final Integer LENGTH = 10;
    public void arrangeShips4(char[][]battlefield){
        boolean placed = false;
        while(!placed){
            boolean horizontal = random.nextBoolean();
            int startX = random.nextInt(LENGTH);
            int startY = random.nextInt(LENGTH);
            if(horizontal){
                if(startX+3<LENGTH){
                    if(isPlacementValid(battlefield,startX,startY,4, true)){
                        placeHorizontally(battlefield,startX,startY);
                        placed=true;
                    }
                }
            }else{
                if(startY+3<LENGTH) {
                    if (isPlacementValid(battlefield, startX, startY, 4, false)) {
                        placeVertically(battlefield,startX,startY);
                        placed = true;
                    }
                }
            }
        }
    }
    private void placeHorizontally(char[][]battlefield,int startX,int startY){
        for(int i=0;i<4;i++){
            battlefield[startX+i][startY]='4';
        }
    }
    private void placeVertically(char[][]battlefield,int startX,int startY){
        for (int i = 0; i < 4; i++) {
            battlefield[startX][startY + i] = '4';
        }
    }
    private boolean isPlacementValid(char[][] battlefield, int startX, int startY, int lengthOfShip, boolean horizontal) {
        if (horizontal) {
            if (startX + lengthOfShip > LENGTH) {
                return false;
            }
            return checkAdjacentCells(battlefield, startX, startY, 1, lengthOfShip);
        } else {
            if (startY + lengthOfShip > LENGTH) {
                return false;
            }
            return checkAdjacentCells(battlefield, startX, startY, lengthOfShip, 1);
        }
    }

    private boolean checkAdjacentCells(char[][] battlefield, int startX, int startY, int length, int lengthOfJ) {
        for (int i = -1; i <= lengthOfJ; i++) {
            for (int j = -1; j <= length; j++) {
                if (startX + i >= 0 && startX + i < LENGTH && startY + j >= 0 && startY + j < LENGTH) {
                    if (battlefield[startX + i][startY + j] != ' ') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
