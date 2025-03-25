import java.util.ArrayList;

public class GameLogic {
    int[][] arr = new int[20][10];
    int indexOfStartingRow;
    int indexOfStartingCol;
    ArrayList<Coordinate> currentFallingBlock;
    ArrayList<Coordinate> cords;
    Shape[] shapeArr;


    public GameLogic() {
        shapeArr = new Shape[]{new ITetromino(), new OTetromino(), new TTetromino(), new STetromino(), new ZTetromino(), new JTetromino(), new LTetromino()};
        cords = new ArrayList<>();
        currentFallingBlock = new ArrayList<>();
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
                if (arr[indexOfStartingRow+row][indexOfStartingCol+col] != 0) {
                    currentFallingBlock.add(new Coordinate(indexOfStartingRow+row, indexOfStartingCol+col));
                }
            }
        }
    }



    public void updateFallingBlock(String direction) {
        int blockNum = arr[currentFallingBlock.getFirst().getRowIdx()][currentFallingBlock.getFirst().getColIdx()];
        for (Coordinate cord : currentFallingBlock) {
            arr[cord.getRowIdx()][cord.getColIdx()] = 0;
        }
        for (Coordinate cord : currentFallingBlock) {
            int row = cord.getRowIdx();
            int col = cord.getColIdx();
            if (direction.equals("down")) {
                arr[row + 1][col] = blockNum;
                cord.decrementRowIdx();
            } else if (direction.equals("left")) {
                arr[row][col-1] = blockNum;
                cord.setColIdx(-1);
            } else if (direction.equals("right")) {
                arr[row][col+1] = blockNum;
                cord.setColIdx(1);
            }
        }
    }

    public boolean canMoveLeft() {
        for (Coordinate cord : currentFallingBlock) {
            if (cord.getColIdx() == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveRight() {
        for (Coordinate cord : currentFallingBlock) {
            if (cord.getColIdx() == 19) {
                return false;
            }
        }
        return true;
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
            System.out.println(" (" + cord.getX1() + ", " + cord.getY1() + "); " + "(" + cord.getX2() + ", " + cord.getY2() + ")");
        }
        System.out.println(cords.size());
    }

    private void generateGridBox() {
        for (int col = 0; col <= 570; col += 30) {
            for (int row = 0; row <= 270; row+=30) {
                cords.add(new Coordinate(row, col, row+30, col +30));
            }
        }
    }
}
