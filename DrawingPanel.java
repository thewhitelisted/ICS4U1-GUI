import javax.swing.JPanel;
import java.awt.Graphics;
import java.lang.Math;

public class DrawingPanel extends JPanel {
    //Properties of triangle
    int intPointAX = 50;
    int intPointAY = 300;

    int intPointBX = 50;
    int intPointBY = 490;

    int intPointCX = 590;
    int intPointCY = 490;
    
    double dblAdj = intPointCX - intPointBX;
    double dblOpp = intPointBY - intPointAY;
    double dblHyp = Math.sqrt(Math.pow(dblAdj, 2) + Math.pow(dblOpp, 2));

    double dblDegrees = Math.toDegrees(Math.atan(dblOpp / dblAdj));

    double dblSquareAX = 100;
    double dblSquareAY = 50;

    double dblSquareBX = dblSquareAX + 100*(Math.cos(dblDegrees));
    double dblSquareBY = dblSquareAY + 100*(Math.sin(dblDegrees));
    
    double dblSquareCX = dblSquareBX - 100*(Math.sin(dblDegrees));
    double dblSquareCY = dblSquareBY + 100*(Math.cos(dblDegrees));
    
    double dblSquareDX = dblSquareCX - 100*(Math.cos(dblDegrees));
    double dblSquareDY = dblSquareCY - 100*(Math.sin(dblDegrees));

    int intRoundAX = (int)dblSquareAX;
    int intRoundAY = (int)dblSquareAY;
    int intRoundBX = (int)dblSquareBX;
    int intRoundBY = (int)dblSquareBY;
    int intRoundCX = (int)dblSquareCX;
    int intRoundCY = (int)dblSquareCY;
    int intRoundDX = (int)dblSquareDX;
    int intRoundDY = (int)dblSquareDY;

    public void paintComponent(Graphics g){
        g.fillPolygon(new int[] {intPointAX, intPointBX, intPointCX}, new int[] {intPointAY, intPointBY, intPointCY}, 3);
        g.fillPolygon(new int[] {intRoundAX, intRoundBX, intRoundCX, intRoundDX}, new int[] {intRoundAY, intRoundBY, intRoundCY, intRoundDY}, 4);
        System.out.println(dblDegrees);
    }
}
