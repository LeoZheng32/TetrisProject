import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Line2D;

public class Display extends JPanel {
    public Display() {}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.LIGHT_GRAY);
        // Draw Columns
        for (int i = 0; i < 330; i+= 30) {
            g2.draw(new Line2D.Float(i, 0, i, 600));
        }
        // Draw Rows
        for (int i = 0; i < 630; i+= 30) {
            g2.draw(new Line2D.Float(0, i, 300, i));
        }
    }

}
