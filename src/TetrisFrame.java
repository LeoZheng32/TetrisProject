import javax.swing.*;
import java.awt.*;

public class TetrisFrame {
    JFrame frame;
    public TetrisFrame() {
        frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        Display panel = new Display();
        frame.add(panel);

        panel.setLayout(new GridLayout(2, 2));
        frame.add(new JButton("1"));
        frame.add(new JButton("2"));

        frame.setVisible(true);
    }
}
