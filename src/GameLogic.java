import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLogic implements ActionListener {
    int[][] boardArr = new int[20][10];
    int indexOfStartingRow;
    int indexOfStartingCol;
    ArrayList<Coordinate> currentFallingBlock;
    ArrayList<Coordinate> cords;
    Shape[] shapeArr;
    private Timer timer;
    private boolean paused = false;


    public GameLogic() {
        shapeArr = new Shape[]{new ITetromino(), new OTetromino(), new TTetromino(), new STetromino(), new ZTetromino(), new JTetromino(), new LTetromino()};
        cords = new ArrayList<>();
        currentFallingBlock = new ArrayList<>();
        indexOfStartingRow = 0;
        indexOfStartingCol = 3;
        boardArr[5][4] = 9;
        //boardArr[6][8] = 9;
        printArr();
        System.out.println("kgKEFFFFFFek\n");
        generateGridBox();
        generateBlock();
        timer = new Timer(1000, this);
        timer.start();
    }

    public void generateBlock() {
        int idx = (int) (Math.random() * 7);
        int[][] shape = shapeArr[idx].getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                boardArr[indexOfStartingRow+row][indexOfStartingCol+col] = shape[row][col];
                if (boardArr[indexOfStartingRow+row][indexOfStartingCol+col] != 0) {
                    currentFallingBlock.add(new Coordinate(indexOfStartingRow+row, indexOfStartingCol+col));
                }
            }
        }
    }



    public void updateFallingBlock(String direction) {
        int blockNum = boardArr[currentFallingBlock.getFirst().getRowIdx()][currentFallingBlock.getFirst().getColIdx()];
        for (Coordinate cord : currentFallingBlock) {
            boardArr[cord.getRowIdx()][cord.getColIdx()] = 0;
        }
        for (Coordinate cord : currentFallingBlock) {
            int row = cord.getRowIdx();
            int col = cord.getColIdx();
            if (direction.equals("down")) {
                boardArr[row + 1][col] = blockNum;
                cord.incrementRowIdx();
            } else if (direction.equals("left")) {
                boardArr[row][col-1] = blockNum;
                cord.setColIdx(-1);
            } else if (direction.equals("right")) {
                boardArr[row][col+1] = blockNum;
                cord.setColIdx(1);
            }
        }
    }

    public boolean canMoveLeft() {
        for (Coordinate cord : currentFallingBlock) {
            if (cord.getColIdx() == 0) {
                return false;
            }
            if (boardArr[cord.getRowIdx()][cord.getColIdx()-1] != 0) {
                boolean inCurrentFallingBlock = false;
                for (Coordinate coordinate : currentFallingBlock) {
                    if (coordinate.compareRowCol(cord.getRowIdx(), cord.getColIdx()-1)) {
                        inCurrentFallingBlock = true;
                    }
                }
                if (!inCurrentFallingBlock) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean canMoveRight() {
        for (Coordinate cord : currentFallingBlock) {
            if (cord.getColIdx() == 9) {
                return false;
            }
            if (boardArr[cord.getRowIdx()][cord.getColIdx()+1] != 0) {
                boolean inCurrentFallingBlock = false;
                for (Coordinate coordinate : currentFallingBlock) {
                    if (coordinate.compareRowCol(cord.getRowIdx(), cord.getColIdx()+1)) {
                        inCurrentFallingBlock = true;
                    }
                }
                if (!inCurrentFallingBlock) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canMoveDown() {
        for (Coordinate cord : currentFallingBlock) {
            if (cord.getRowIdx() == 19) {
                return false;
            }
            if (boardArr[cord.getRowIdx()+1][cord.getColIdx()] != 0) {
                boolean inCurrentFallingBlock = false;
                for (Coordinate coordinate : currentFallingBlock) {
                    if (coordinate.compareRowCol(cord.getRowIdx()+1, cord.getColIdx())) {
                        inCurrentFallingBlock = true;
                    }
                }
                if (!inCurrentFallingBlock) {
                    return false;
                }
            }
        }
        return true;
    }


    public void printArr() {
        for (int i = 0; i < boardArr.length; i++) {
            for (int j = 0; j < boardArr[0].length; j++) {
                System.out.print(boardArr[i][j]);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        paused = !paused;
        //System.out.println(time);
    }
}
