import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class DrawingPanel extends JPanel {
    // Properties of triangle
    public double dblDegrees = 30.0; // Between 0 - 40. Anything more will break
    double dblPointBX = 50;
    double dblPointBY = 490;
    double dblPointCX = 590;
    double dblPointCY = 490;
    double dblPointAX = 50;

    // height
    double dblPointAY = dblPointBY - (dblPointCX - dblPointBX) * (Math.tan(Math.toRadians(dblDegrees)));

    // rounded dimensions for triangle
    int intPointAX = (int) (dblPointAX);
    int intPointAY = (int) (dblPointAY);
    int intPointBX = (int) (dblPointBX);
    int intPointBY = (int) (dblPointBY);
    int intPointCX = (int) (dblPointCX);
    int intPointCY = (int) (dblPointCY);

    // side lengths
    double dblAdj = dblPointCX - dblPointBX;
    double dblOpp = dblPointBY - dblPointAY;
    double dblHyp = Math.sqrt(Math.pow(dblAdj, 2) + Math.pow(dblOpp, 2));

    // Properties of Square
    double dblSquareAX = 100;
    double dblSquareAY = 5;
    double dblSquareBX = dblSquareAX + 50 * (Math.cos(Math.toRadians(dblDegrees)));
    double dblSquareBY = dblSquareAY + 50 * (Math.sin(Math.toRadians(dblDegrees)));
    double dblSquareCX = dblSquareBX - 50 * (Math.sin(Math.toRadians(dblDegrees)));
    double dblSquareCY = dblSquareBY + 50 * (Math.cos(Math.toRadians(dblDegrees)));
    double dblSquareDX = dblSquareCX - 50 * (Math.cos(Math.toRadians(dblDegrees)));
    double dblSquareDY = dblSquareCY - 50 * (Math.sin(Math.toRadians(dblDegrees)));

    // rounded dimensions
    int intRoundAX = (int) dblSquareAX;
    int intRoundAY = (int) dblSquareAY;
    int intRoundBX = (int) dblSquareBX;
    int intRoundBY = (int) dblSquareBY;
    int intRoundCX = (int) dblSquareCX;
    int intRoundCY = (int) dblSquareCY;
    int intRoundDX = (int) dblSquareDX;
    int intRoundDY = (int) dblSquareDY;

    public void paintComponent(Graphics g) {
        this.update();
        g.setColor(Color.white);
        g.fillRect(0, 0, 640, 540);
        g.setColor(Color.black);
        g.fillPolygon(new int[] { intPointAX, intPointBX, intPointCX },
                new int[] { intPointAY, intPointBY, intPointCY }, 3);
        g.setColor(Color.red);
        g.fillPolygon(new int[] { intRoundAX, intRoundBX, intRoundCX, intRoundDX },
                new int[] { intRoundAY, intRoundBY, intRoundCY, intRoundDY }, 4);
        this.repaint();
    }

    public void update() {
        // Update triangle
        dblPointAY = dblPointBY - (dblPointCX - dblPointBX) * (Math.tan(Math.toRadians(dblDegrees)));
        intPointAY = (int) (dblPointAY);
        dblAdj = dblPointCX - dblPointBX;
        dblOpp = dblPointBY - dblPointAY;
        dblHyp = Math.sqrt(Math.pow(dblAdj, 2) + Math.pow(dblOpp, 2));

        // Update square
        dblSquareBX = dblSquareAX + 50 * (Math.cos(Math.toRadians(dblDegrees)));
        dblSquareBY = dblSquareAY + 50 * (Math.sin(Math.toRadians(dblDegrees)));
        dblSquareCX = dblSquareBX - 50 * (Math.sin(Math.toRadians(dblDegrees)));
        dblSquareCY = dblSquareBY + 50 * (Math.cos(Math.toRadians(dblDegrees)));
        dblSquareDX = dblSquareCX - 50 * (Math.cos(Math.toRadians(dblDegrees)));
        dblSquareDY = dblSquareCY - 50 * (Math.sin(Math.toRadians(dblDegrees)));

        intRoundAX = (int) dblSquareAX;
        intRoundAY = (int) dblSquareAY;
        intRoundBX = (int) dblSquareBX;
        intRoundBY = (int) dblSquareBY;
        intRoundCX = (int) dblSquareCX;
        intRoundCY = (int) dblSquareCY;
        intRoundDX = (int) dblSquareDX;
        intRoundDY = (int) dblSquareDY;
    }
}
