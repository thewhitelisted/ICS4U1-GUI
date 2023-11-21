import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

public class GUIListener implements ActionListener {
    JMenuItem component;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.component.getText()) {
            case "Save as CSV":
                System.out.println("Save as CSV");
                break;
            case "Open CSV File":
                System.out.println("Open CSV File");
                break;
            case "Run Simulation":
                System.out.println("Run Simulation");
                break;
            case "Reset Simulation":
                System.out.println("Reset Simulation");
                break;
            case "Clear Simulation":
                System.out.println("Clear Simulation");
                break;
        }
    }

    GUIListener(JMenuItem component) {
        this.component = component;
    }
}
