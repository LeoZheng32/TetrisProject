import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Display extends JPanel {
    public Display() {}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLUE);
        g.drawString("Tetris", 50, 30);
    }

}
