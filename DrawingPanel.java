import javax.swing.JPanel;
import java.awt.Graphics;

public class DrawingPanel extends JPanel {
    int intPointAX = 20;
    int intPointBX = 20;
    int intPointCX = 620;

    int intPointAY = 350;
    int intPointBY = 540;
    int intPointCY = 540;

    public void paintComponent(Graphics g){
        g.fillPolygon(new int[] {intPointAX, intPointBX, intPointCX}, new int[] {intPointAY, intPointBY, intPointCY}, 3);
    }
}
