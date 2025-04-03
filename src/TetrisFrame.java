import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TetrisFrame {
    JPanel panel;
    JFrame frame;
    GameLogic logic;
    ArrayList<JPanel> panels;
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
        panels = new ArrayList<>();
        frame.setLayout(new GridLayout(20, 10, 1, 1));
        for (int i = 0; i < 200; i++) {
            JPanel panel = new JPanel();
            frame.add(panel);
            panels.add(panel);
        }
    }

    public void recolorBoard() {
        Shape[][] boardArr = logic.getBoardArr();
        for (int i = 0; i < panels.size(); i++) {
            Shape cellVal = boardArr[i/10][i%10];
            if (cellVal == null) {
                panels.get(i).setBackground(Color.BLACK);
            } else if (cellVal instanceof OBlock) {
                panels.get(i).setBackground(Color.YELLOW);
            } else if (cellVal instanceof IBlock) {
                panels.get(i).setBackground(Color.CYAN);
            } else if (cellVal instanceof SBlock) {
                panels.get(i).setBackground(Color.GREEN);
            } else if (cellVal instanceof ZBlock) {
                panels.get(i).setBackground(Color.RED);
            } else if (cellVal instanceof LBlock) {
                panels.get(i).setBackground(Color.ORANGE);
            } else if (cellVal instanceof JBlock) {
                panels.get(i).setBackground(Color.MAGENTA);
            } else if (cellVal instanceof TBlock) {
                panels.get(i).setBackground(Color.getHSBColor(153, 0, 153));
            }
        }
    }

    public void reRender() {
        recolorBoard();
        frame.revalidate();
        frame.repaint();
    }
}
