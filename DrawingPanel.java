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
    private double dblPointBX = 50;
    private double dblPointBY = 490;
    private double dblPointCX = 590;
    private double dblPointCY = 490;
    private double dblPointAX = 50;

    // height
    private double dblPointAY = dblPointBY - (dblPointCX - dblPointBX) * (Math.tan(Math.toRadians(dblDegrees)));

    // rounded dimensions for triangle
    private int intPointAX = (int) (dblPointAX);
    private int intPointAY = (int) (dblPointAY);
    private int intPointBX = (int) (dblPointBX);
    private int intPointBY = (int) (dblPointBY);
    private int intPointCX = (int) (dblPointCX);
    private int intPointCY = (int) (dblPointCY);

    // Properties of Square
    double dblSquareAX = 150;
    // calculate the y translation of the top left corner of the square

    // calculate top left corner of square based on triangle height, should be on
    // top of the triangle
    private double dblSquareAY = (540 - 50 - (440 * Math.tan(Math.toRadians(dblDegrees))))
            - (50 / Math.cos(Math.toRadians(dblDegrees)));
    private double dblSquareBX = dblSquareAX + 50 * (Math.cos(Math.toRadians(dblDegrees)));
    private double dblSquareBY = dblSquareAY + 50 * (Math.sin(Math.toRadians(dblDegrees)));
    private double dblSquareCX = dblSquareBX - 50 * (Math.sin(Math.toRadians(dblDegrees)));
    private double dblSquareCY = dblSquareBY + 50 * (Math.cos(Math.toRadians(dblDegrees)));
    private double dblSquareDX = dblSquareCX - 50 * (Math.cos(Math.toRadians(dblDegrees)));
    private double dblSquareDY = dblSquareCY - 50 * (Math.sin(Math.toRadians(dblDegrees)));

    // rounded dimensions square
    private int intRoundAX = (int) dblSquareAX;
    private int intRoundAY = (int) dblSquareAY;
    private int intRoundBX = (int) dblSquareBX;
    private int intRoundBY = (int) dblSquareBY;
    private int intRoundCX = (int) dblSquareCX;
    private int intRoundCY = (int) dblSquareCY;
    private int intRoundDX = (int) dblSquareDX;
    private int intRoundDY = (int) dblSquareDY;


    // physics properties
    public double dblMass = 0;
    public double dblVelX = 0;
    public double dblSeconds = 0;
    public double dblAccelerationX = 0;
    public double dblStaticFriction = 0;
    public double dblKineticFriction = 0;
    public static double dblGravity = 9.8;

    /**
     * Paints the triangle and square to the screen
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

    /**
     * Updates the triangle and square based on the degrees and square x position
     */
    public void update() {
        // Update triangle
        dblPointAY = dblPointBY - (dblPointCX - dblPointBX) * (Math.tan(Math.toRadians(dblDegrees)));
        intPointAY = (int) (dblPointAY);

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

    /**
     * Returns the x velocity of the square based on the time passed
     * @param dblTime Time passed
     * @return double x velocity
     */
    public double physicsCalculations(double dblTime){
        // if static friction is too high, return 0
        if ((dblStaticFriction*dblMass * dblGravity) / Math.cos(Math.toRadians(dblDegrees)) > (dblMass * dblGravity * Math.sin(Math.toRadians(dblDegrees)))){
            return 0;
        }
        dblAccelerationX = ((Math.sin(Math.toRadians(dblDegrees))) - (dblKineticFriction * Math.cos(Math.toRadians(dblDegrees)))) * dblGravity * Math.cos(Math.toRadians(dblDegrees));
        System.out.println(dblAccelerationX);
        dblVelX = ((dblAccelerationX/12)*5) * dblTime;
        if (dblVelX < 0){
            dblVelX = 0;
        }
        return dblVelX;
    }
}
