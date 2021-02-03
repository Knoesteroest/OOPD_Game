import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JComponent {

    public static void main(String args[]) {
        JFrame mainFrame = new JFrame("Hello World");
        mainFrame.getContentPane().add(new Main());
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth("Hello World");
        int h = fm.getAscent();
        g.drawString("Hello World", getWidth() /2 - (w /2), (getHeight() / 2) + (h / 2));

    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

}
