/* DRAWING PANEL
 * 
 * USED FOR DRAWING THE TRIANGLE AND SQUARE TO THE SCREEN
 * THIS CLASS CAN BE CREATED AS A JFRAME AND ADDED TO THE MAIN JFRAME
 * 
 */

// imports
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

/**
 * DrawingPanel
 * 
 * This class is used for drawing the triangle and square to the screen. This
 * class can be created as a JFrame and added to the main JFrame.
 * @author Christopher Lee
 * @author Nicholas Poon
 */
public class DrawingPanel extends JPanel {
    // Properties of triangle
    public double dblDegrees = 20.0; // Between 0 - 40. Anything more will break
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

    // side lengths triangle
    double dblAdj = dblPointCX - dblPointBX;
    double dblOpp = dblPointBY - dblPointAY;
    // find slope of hypotenuse
    double slope = dblOpp / dblAdj;
    // find mx + b of hypotenuse
    double b = dblPointBY - slope * dblPointBX;
    double dblHyp = Math.sqrt(Math.pow(dblAdj, 2) + Math.pow(dblOpp, 2));

    // Properties of Square
    double dblSquareAX = 150;
    // calculate the y translation of the top left corner of the square

    // calculate top left corner of square based on triangle height, should be on
    // top of the triangle
    double dblSquareAY = (540 - 50 - (440 * Math.tan(Math.toRadians(dblDegrees))))
            - (50 / Math.cos(Math.toRadians(dblDegrees)));
    double dblSquareBX = dblSquareAX + 50 * (Math.cos(Math.toRadians(dblDegrees)));
    double dblSquareBY = dblSquareAY + 50 * (Math.sin(Math.toRadians(dblDegrees)));
    double dblSquareCX = dblSquareBX - 50 * (Math.sin(Math.toRadians(dblDegrees)));
    double dblSquareCY = dblSquareBY + 50 * (Math.cos(Math.toRadians(dblDegrees)));
    double dblSquareDX = dblSquareCX - 50 * (Math.cos(Math.toRadians(dblDegrees)));
    double dblSquareDY = dblSquareCY - 50 * (Math.sin(Math.toRadians(dblDegrees)));

    // rounded dimensions square
    int intRoundAX = (int) dblSquareAX;
    int intRoundAY = (int) dblSquareAY;
    int intRoundBX = (int) dblSquareBX;
    int intRoundBY = (int) dblSquareBY;
    int intRoundCX = (int) dblSquareCX;
    int intRoundCY = (int) dblSquareCY;
    int intRoundDX = (int) dblSquareDX;
    int intRoundDY = (int) dblSquareDY;


    // physics properties
    double dblMass = 0;
    double dblVelX = 0;
    double dblSeconds = 0;
    double dblAccelerationX = 0;
    double dblStaticFriction = 0;
    double dblKineticFriction = 0;
    static double dblGravity = 9.8;

    /**
     * Paint Component method
     * @param g Graphics
     */
    @Override
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
    }

    // update method, updates the dimensions
    public void update() {
        // Update triangle
        dblPointAY = dblPointBY - (dblPointCX - dblPointBX) * (Math.tan(Math.toRadians(dblDegrees)));
        intPointAY = (int) (dblPointAY);
        dblAdj = dblPointCX - dblPointBX;
        dblOpp = dblPointBY - dblPointAY;
        dblHyp = Math.sqrt(Math.pow(dblAdj, 2) + Math.pow(dblOpp, 2));
        slope = dblOpp / dblAdj;
        b = dblPointBY - slope * dblPointBX;

        // Update square
        dblSquareAY = (540 - 50 - ((590 - dblSquareAX) * Math.tan(Math.toRadians(dblDegrees))) - (50 / Math.sin(Math.toRadians(90 - dblDegrees))));
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

    // physics calculations
    public double physicsCalculations(double dblTime){
        // if static friction is too high, return 0
        if ((dblStaticFriction*dblMass * dblGravity) / Math.cos(Math.toRadians(dblDegrees)) > (dblMass * dblGravity * Math.sin(Math.toRadians(dblDegrees)))){
            return 0;
        }
        dblAccelerationX = ((Math.sin(Math.toRadians(dblDegrees))) - (dblKineticFriction * Math.cos(Math.toRadians(dblDegrees)))) * dblGravity * Math.cos(Math.toRadians(dblDegrees));
        System.out.println(dblAccelerationX);
        dblVelX = (dblAccelerationX/48) * dblTime;
        if (dblVelX < 0){
            dblVelX = 0;
        }
        return dblVelX;
    }
}
