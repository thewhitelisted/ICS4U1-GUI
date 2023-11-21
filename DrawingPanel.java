import javax.swing.JPanel;
import java.awt.Graphics;

public class DrawingPanel extends JPanel {
    int intPointAX = 50;
    int intPointBX = 50;
    int intPointCX = 590;

    int intPointAY = 300;
    int intPointBY = 490;
    int intPointCY = 490;

    public void paintComponent(Graphics g){
        g.fillPolygon(new int[] {intPointAX, intPointBX, intPointCX}, new int[] {intPointAY, intPointBY, intPointCY}, 3);
    }
}
