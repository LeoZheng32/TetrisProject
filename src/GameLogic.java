import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class GameLogic implements ActionListener, KeyListener {
    private int points;
    private Shape[][] boardArr;
    private Shape currentFallingBlock;
    private MakeShape shapeGenerator;
    private Timer timer;
    private boolean paused;
    private TetrisFrame frame;
    private boolean canMove;
    private boolean gameStatus;

    public GameLogic() {
        gameStatus = false;
        points = 0;
        canMove = true;
        paused = false;
        Scanner scan = new Scanner(System.in);
        System.out.println(
                "    ███        ▄████████     ███        ▄████████  ▄█     ▄████████\n" +
                "▀█████████▄   ███    ███ ▀█████████▄   ███    ███ ███    ███    ███\n" +
                "   ▀███▀▀██   ███    █▀     ▀███▀▀██   ███    ███ ███▌   ███    █▀\n" +
                "    ███   ▀  ▄███▄▄▄         ███   ▀  ▄███▄▄▄▄██▀ ███▌   ███\n" +
                "    ███     ▀▀███▀▀▀         ███     ▀▀███▀▀▀▀▀   ███▌ ▀███████████\n" +
                "    ███       ███    █▄      ███     ▀███████████ ███           ███\n" +
                "    ███       ███    ███     ███       ███    ███ ███     ▄█    ███\n" +
                "   ▄████▀     ██████████    ▄████▀     ███    ███ █▀    ▄████████▀\n" +
                "                                       ███    ███");

                System.out.print("Would you like to a game of Tetris? (y/n) ");
        String response = scan.nextLine();

        if (response.equalsIgnoreCase("y")) {
            shapeGenerator = new MakeShape();
            boardArr = new Shape[20][10];
            generateBlock();
            frame = new TetrisFrame(this);
            setFrame();
            timer = new Timer(1250, this);
            timer.start();
        } else {
            System.out.println("goodbye");
        }
    }

    public Shape[][] getBoardArr() {
        return boardArr;
    }

    public void setFrame() {
        frame.reRender();
        frame.getFrame().addKeyListener(this);
        frame.getFrame().setFocusable(true);
    }

    public boolean generateBlock() {
        currentFallingBlock = createObject(shapeGenerator.randomSelectedShape());
        if (checkOverLap(currentFallingBlock.getShapeArr())) {
            gameOver();
            return false;
        }
        addFallingBLock();
        if (paused) {
            paused = false;
        }

        canMove = true;
        if (points >= 50000) {
            timer.stop();
            timer = new Timer(500, this);
            timer.start();
        } else if (points >= 20000) {
            timer.stop();
            timer = new Timer(1000, this);
            timer.start();
        }
        return true;
    }

    public boolean belongToCurrentBlock(int row, int col) {
        for (Shape[] blocks : currentFallingBlock.getShapeArr()) {
            for (Shape block : blocks) {
                if (block != null && block.getRowPos() == row && block.getColPos() == col) {
                    return true;
                }
            }
        }
        return false;
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

    public void rotateBlock() {
        removeFallingBlock();

        Shape[][] rotatedShape = currentFallingBlock.rotate();

        if (!checkOverLap(rotatedShape)) {
            removeFallingBlock();
            currentFallingBlock.setShapeArr(rotatedShape);
        } else {
            currentFallingBlock.setRotation(currentFallingBlock.getRotation()-1);
        }
        addFallingBLock();
    }

    public boolean canMoveLeft() {
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (Shape block : shapes) {
                if (block != null) {
                    int row = block.getRowPos();
                    int col = block.getColPos();
                    if (col == 0) {
                        return false;
                    }
                    if (boardArr[row][col - 1] != null && !belongToCurrentBlock(row, col - 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canMoveRight() {
        for (Shape[] shapes : currentFallingBlock.getShapeArr()) {
            for (Shape block : shapes) {
                if (block != null) {
                    int row = block.getRowPos();
                    int col = block.getColPos();
                    if (col == 9) {
                        return false;
                    }
                    if (boardArr[row][col + 1] != null && !belongToCurrentBlock(row, col + 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canMoveDown() {
        Shape[][] current = currentFallingBlock.getShapeArr();
        for (Shape[] blocks : current) {
            for (Shape block : blocks) {
                if (block != null) {
                    int nextRow = block.getRowPos() + 1;
                    int col = block.getColPos();

                    if (nextRow >= 20) {
                        return false;
                    }

                    if (boardArr[nextRow][col] != null && !belongToCurrentBlock(nextRow, col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkOverLap(Shape[][] shape) {
        boolean overlap = false;
        for (Shape[] blocks : shape) {
            for (int col = 0; col < blocks.length; col++) {
                if (blocks[col] != null) {
                    if (blocks[col].getColPos() < 0 || blocks[col].getColPos() > 9 ||
                            blocks[col].getRowPos() < 0 || blocks[col].getRowPos() > 19) {
                        return true;
                    }
                    if (boardArr[blocks[col].getRowPos()][blocks[col].getColPos()] != null) {
                        overlap = true;
                    }
                }
            }
        }
        return overlap;
    }

    public void gameOver() {
        System.out.println("\n--------------------------------------");
        System.out.println(
                        " ▄▄ •  ▄▄▄· • ▌ ▄ ·. ▄▄▄ .       ▌ ▐·▄▄▄ .▄▄▄  \n" +
                        "▐█ ▀ ▪▐█ ▀█ ·██ ▐███▪▀▄.▀·▪     ▪█·█▌▀▄.▀·▀▄ █·\n" +
                        "▄█ ▀█▄▄█▀▀█ ▐█ ▌▐▌▐█·▐▀▀▪▄ ▄█▀▄ ▐█▐█•▐▀▀▪▄▐▀▀▄ \n" +
                        "▐█▄▪▐█▐█ ▪▐▌██ ██▌▐█▌▐█▄▄▌▐█▌.▐▌ ███ ▐█▄▄▌▐█•█▌\n" +
                        "·▀▀▀▀  ▀  ▀ ▀▀  █▪▀▀▀ ▀▀▀  ▀█▄▀▪. ▀   ▀▀▀ .▀  ▀");
        System.out.println("Score: " + points);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (canMove) {
            int key = e.getKeyCode();
            if (key == 38) {
                rotateBlock();
                paused = false;
            } else if (key == 40 && canMoveDown()) {
                updateFallingBlock("down");
            } else if (key == 37 && canMoveLeft()) {
                updateFallingBlock("left");
            } else if (key == 39 && canMoveRight()) {
                updateFallingBlock("right");
            }
            frame.reRender();
        }
    }

    public void removedFinishedRows() {
        for (int row = boardArr.length-1; row >= 0; row--) {
            boolean filled = true;
            for (int col = 0; col < 10; col++) {
                if (boardArr[row][col] == null) {
                    filled = false;
                }
            }
            if (filled) {
                points+=1000;
                for (int currentRow = row; currentRow > 0; currentRow--) {
                    boardArr[currentRow] = boardArr[currentRow - 1];
                }
                boardArr[0] = new Shape[10];
                row++;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus) {
            timer.stop();
            return;
        }

        if (paused) {return;}

        if (canMoveDown()) {
            updateFallingBlock("down");
        } else {
            canMove = false;
            currentFallingBlock = null;
            removedFinishedRows();
            if (!generateBlock()) {
                gameStatus = true;
            }
        }
        frame.reRender();
    }
}