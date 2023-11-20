import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    // Properties
    JFrame main_frame = new JFrame();
    JPanel main_panel = new JPanel();

    // Constructor
    Main() {
        this.main_panel.setPreferredSize(new Dimension(900, 900));
        this.main_frame.setContentPane(main_panel);
        this.main_frame.pack();
        this.main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.main_frame.setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        new Main();
    }
}