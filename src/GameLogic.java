import java.util.ArrayList;

public class GameLogic {
    int[][] arr = new int[20][10];
    int indexOfStartingRow;
    int indexOfStartingCol;
    ArrayList<Coordinate> currentFallingTetromino;
    ArrayList<Coordinate> cords;
    Shape[] shapeArr;


    public GameLogic() {
        shapeArr = new Shape[]{new ITetromino(), new OTetromino(), new TTetromino(), new STetromino(), new ZTetromino(), new JTetromino(), new LTetromino()};
        cords = new ArrayList<>();
        currentFallingTetromino = new ArrayList<>();
        indexOfStartingRow = 0;
        indexOfStartingCol = 3;
        generateGridBox();
        generateBlock();
    }

    public void generateBlock() {
        int idx = (int) (Math.random() * 7);
        int[][] shape = shapeArr[idx].getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                arr[indexOfStartingRow+row][indexOfStartingCol+col] = shape[row][col];
            }
        }
    }

    public void updateFallingBlock() {

    }

    public void printArr() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public void printCords() {
        for (Coordinate cord : cords) {
            System.out.println("Index: " + cord.getIndex() + " (" + cord.getX1() + ", " + cord.getY1() + "); " + "(" + cord.getX2() + ", " + cord.getY2() + ")");
        }
        System.out.println(cords.size());
    }

    private void generateGridBox() {
        int idx = 0;
        for (int col = 0; col <= 570; col += 30) {
            for (int row = 0; row <= 270; row+=30) {
                cords.add(new Coordinate(idx, row, col, row+30, col +30));
                idx++;
            }
        }
    }
}
