import javax.swing.*;
import java.awt.*;

public class TetrisFrame {
    JFrame frame;
    public TetrisFrame() {
        frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 645);
        frame.setLocationRelativeTo(null);

        Display panel = new Display();
        frame.add(panel);

        frame.setVisible(true);
    }
}
