import javax.swing.*;
import java.awt.*;

public class TetrisFrame {
    JPanel panel;
    JFrame frame;
    GameLogic logic;
    public TetrisFrame(GameLogic logic) {
        this.logic = logic;
        frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        generateBoard();

//        Display panel = new Display();
//        frame.add(panel);

        frame.setVisible(true);
    }

    // For changing the color you have to make a new gridlayout and edit each color
    public void generateBoard() {
        frame.setLayout(new GridLayout(20, 10, 1, 1));
        Shape[][] boardArr = logic.getBoardArr();
        for (int i = 0; i < 200; i++) {
            JPanel panel = new JPanel();
            Shape cellVal = boardArr[i/10][i%10];
            if (cellVal == null) {
                panel.setBackground(Color.BLACK);
            } else if (cellVal instanceof OBlock) {
                panel.setBackground(Color.YELLOW);
            } else if (cellVal instanceof IBlock) {
                panel.setBackground(Color.CYAN);
            } else if (cellVal instanceof SBlock) {
                panel.setBackground(Color.GREEN);
            } else if (cellVal instanceof ZBlock) {
                panel.setBackground(Color.RED);
            } else if (cellVal instanceof LBlock) {
                panel.setBackground(Color.ORANGE);
            } else if (cellVal instanceof JBlock) {
                panel.setBackground(Color.MAGENTA);
            } else if (cellVal instanceof TBlock) {
                panel.setBackground(Color.getHSBColor(153, 0, 153));
            }
            frame.add(panel);
        }
    }

    public void reRender() {
        frame.repaint();
    }
}
