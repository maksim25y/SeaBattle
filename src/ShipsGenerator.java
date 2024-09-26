import java.util.Random;

public class ShipsGenerator {
    private final Random random = new Random();
    private final Integer LENGTH = 10;
    public void arrangeShips4(char[][]battlefield, int lengthOfShip){
        boolean placed = false;
        while(!placed){
            boolean horizontal = random.nextBoolean();
            int startX = random.nextInt(LENGTH);
            int startY = random.nextInt(LENGTH);
            if(horizontal){
                if(startX+lengthOfShip-1<LENGTH){
                    if(isPlacementValid(battlefield,startX,startY,lengthOfShip, true)){
                        placeHorizontally(battlefield,startX,startY,lengthOfShip);
                        placed=true;
                    }
                }
            }else{
                if(startY+lengthOfShip-1<LENGTH) {
                    if (isPlacementValid(battlefield, startX, startY, lengthOfShip, false)) {
                        placeVertically(battlefield,startX,startY,lengthOfShip);
                        placed = true;
                    }
                }
            }
        }
    }
    private void placeHorizontally(char[][]battlefield,int startX,int startY,int lengthOfShip){
        for(int i=0;i<lengthOfShip;i++){
            battlefield[startX+i][startY]='s';
        }
    }
    private void placeVertically(char[][]battlefield,int startX,int startY,int lengthOfShip){
        for (int i = 0; i < lengthOfShip; i++) {
            battlefield[startX][startY + i] = 's';
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
