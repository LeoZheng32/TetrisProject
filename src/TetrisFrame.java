import javax.swing.*;
import java.awt.*;

public class TetrisFrame {
    JPanel panel;
    JFrame frame;
    public TetrisFrame() {
        frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);



//        Display panel = new Display();
//        frame.add(panel);

        frame.setVisible(true);
    }

    // For changing the color you have to make a new gridlayout and edit each color
    public void generateBoard() {
        frame.setLayout(new GridLayout(20, 10, 1, 1));
        for (int i = 0; i < 200; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.BLACK);
            frame.add(panel);
        }
    }
}
