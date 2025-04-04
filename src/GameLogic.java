import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class GameLogic implements ActionListener, KeyListener {
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
        frame.getFrame().addKeyListener(this);
        frame.getFrame().setFocusable(true);
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
        if (direction.equals("down") && canMoveDown()) {
            for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
                for (int col = 0; col < shapes.length; col++) {
                    if (shapes[col] != null) {
                        shapes[col].incrementRowPos();
                        boardArr[shapes[col].getRowPos()][shapes[col].getColPos()] = shapes[col];
                    }
                }
            }
        }

        if (direction.equals("left") && canMoveLeft()) {
            for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
                for (int col = 0; col < shapes.length; col++) {
                    if (shapes[col] != null) {
                        shapes[col].changeColPos(-1);
                        boardArr[shapes[col].getRowPos()][shapes[col].getColPos()] = shapes[col];
                    }
                }
            }
        }

        if (direction.equals("right") && canMoveRight()) {
            for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
                for (int col = 0; col < shapes.length; col++) {
                    if (shapes[col] != null) {
                        shapes[col].changeColPos(1);
                        boardArr[shapes[col].getRowPos()][shapes[col].getColPos()] = shapes[col];
                    }
                }
            }
        }
    }

    // Checks if it can move to the left
    public boolean canMoveLeft() {
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (int col = 0; col < shapes.length; col++) {
                if (shapes[col] != null) {
                    if (shapes[col].getColPos() == 0) {
                        return false;
                    }
                    if (boardArr[shapes[col].getRowPos()][shapes[col].getColPos()-1] == null) {
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
                    if (shapes[col].getColPos() == 9) {
                        return false;
                    }
                    if (boardArr[shapes[col].getRowPos()][shapes[col].getColPos() + 1] == null) {
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
                    if (current[row][col].getRowPos() == 19) {
                        return false;
                    }
                    if (boardArr[current[row][col].getRowPos()+1][current[row][col].getColPos()] == null) {
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
        Shape[][] rotatedShape = currentFallingBlock.rotate();

        if (!checkRotateOverLap(rotatedShape)) {
            removeFallingBlock();
            currentFallingBlock.setShapeArr(rotatedShape);
        } else {
            currentFallingBlock.setRotation(currentFallingBlock.getRotation()-1);
        }
        addFallingBLock();
    }

    // Return true if it overlaps or rotate out of the board
    public boolean checkRotateOverLap(Shape[][] rotatedShape) {
        boolean overlap = false;
        for (Shape[] rotatedBlock : rotatedShape) {
            for (int col = 0; col < rotatedBlock.length; col++) {
                if (rotatedBlock[col] != null) {
                    if (rotatedBlock[col].getRowPos() < 0 || rotatedBlock[col].getRowPos() > 19 ||
                            rotatedBlock[col].getRowPos() < 0 || rotatedBlock[col].getRowPos() > 9) {
                        return true;
                    }
                    if (boardArr[rotatedBlock[col].getRowPos()][rotatedBlock[col].getColPos()] != null) {
                        overlap = true;
                    }
                }
            }
        }
        System.out.println("d");
        return overlap;
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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("hello");
        if (key == 38) {
            rotateBlock();
        } else if (key == 40 && canMoveDown()) {
            updateFallingBlock("down");
        } else if (key == 37 && canMoveLeft()) {
            updateFallingBlock("left");
        } else if (key == 39 && canMoveRight()) {
            updateFallingBlock("right");
        }
        printArr();
        frame.reRender();
    }

    @Override
    public void keyReleased(KeyEvent e) {}


    @Override
    public void actionPerformed(ActionEvent e) {
        paused = !paused;

//        if (canMoveDown()) {
//            updateFallingBlock("down");
//        }
        //printArr();
        System.out.println(timer);
    }

}
