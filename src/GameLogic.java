import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLogic implements ActionListener {
    Shape[][] boardArr;
    //Shape[][] currentFallingBlock;
    Shape currentFallingBlock;
    private MakeShape shapeGenerator;
    int indexOfStartingRow;
    int indexOfStartingCol;
    ArrayList<Coordinate> cords;
    Shape[] shapeArr;
    private Timer timer;
    private boolean paused = false;
    TetrisFrame frame;

    public GameLogic() {
        boardArr = new Shape[20][10];
        cords = new ArrayList<>();
        shapeGenerator = new MakeShape();
        generateGridBox();
        generateBlock();
        timer = new Timer(1000, this);
        timer.start();
    }

    public Shape[][] getBoardArr() {
        return boardArr;
    }

    public void setFrame(TetrisFrame frame) {
        this.frame = frame;
    }

    // Generate a block onto the boardArr
    public void generateBlock() {
        currentFallingBlock = createObject(shapeGenerator.randomSelectedShape());
        addFallingBLock();
    }

    public Shape createObject(Shape[][] selectedShape) {
        if (selectedShape[1][1] instanceof IBlock) {
            return new IBlock(selectedShape);
        } else if (selectedShape[1][1] instanceof JBlock) {;
            return new JBlock(selectedShape);
        } else if (selectedShape[1][1] instanceof LBlock) {
            return new LBlock(selectedShape);
        } else if (selectedShape[1][1] instanceof OBlock) {
            return new OBlock(selectedShape);
        } else if (selectedShape[1][1] instanceof SBlock) {
            return new SBlock(selectedShape);
        } else if (selectedShape[1][1] instanceof TBlock) {
            return new TBlock(selectedShape);
        } else {
            return new ZBlock(selectedShape);
        }
    }

    public void removeFallingBlock() {
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (int col = 0; col < shapes.length; col++) {
                if (shapes[col] != null) {
                    boardArr[shapes[col].getRowPos()][shapes[col].getColPos()] = null;
                }
            }
        }
    }

    public void addFallingBLock() {
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (int col = 0; col < shapes.length; col++) {
                if (shapes[col] != null) {
                    boardArr[shapes[col].getRowPos()][shapes[col].getColPos()] = shapes[col];
                }
            }
        }
    }

    public void updateFallingBlock(String direction) {
        removeFallingBlock();

        // Update each block positions and re-update them on the boardArr
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (int col = 0; col < shapes.length; col++) {
                if (shapes[col] != null) {
                    if (direction.equals("down")) {
                        shapes[col].incrementRowPos();
                    } else if (direction.equals("left")) {
                        shapes[col].changeColPos(-1);
                    } else if (direction.equals("right")) {
                        shapes[col].changeColPos(1);
                    }
                    boardArr[shapes[col].getRowPos()][shapes[col].getColPos()] = shapes[col];
                }
            }
        }
    }

    // Checks if it can move to the left
    public boolean canMoveLeft() {
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (int col = 0; col < shapes.length; col++) {
                if (shapes[col] != null) {
                    if (shapes[col].getColPos() != 0 && boardArr[shapes[col].getRowPos()][shapes[col].getColPos() - 1] == null) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canMoveRight() {
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (int col = shapes.length-1; col >= 0; col--) {
                if (shapes[col] != null) {
                    if (shapes[col].getColPos() != 9 && boardArr[shapes[col].getRowPos()][shapes[col].getColPos() + 1] == null) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canMoveDown() {
        Shape[][] current = currentFallingBlock.getShapeArr();
        for (int col = 0; col < current[0].length; col++) {
            for (int row = current.length-1; row >= 0; row--) {
                if (current[row][col] != null) {
                    if (current[row][col].getColPos() != 19 &&
                            boardArr[current[row][col].getRowPos()+1][current[row][col].getColPos()] == null) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void rotateBlock() {
        removeFallingBlock();
        System.out.println(currentFallingBlock.getClass());
        currentFallingBlock.setShapeArr(currentFallingBlock.rotate());
        addFallingBLock();
    }

    public void printArr() {
        for (Shape[] shapes : boardArr) {
            for (int j = 0; j < shapes.length; j++) {
                if (shapes[j] != null)
                    System.out.print(shapes[j]);
                else
                    System.out.print("n");
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
        updateFallingBlock("down");
        frame.reRender();
        System.out.println(timer);
    }

}
