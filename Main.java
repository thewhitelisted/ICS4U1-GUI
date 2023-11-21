import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main {
    // Properties
    JFrame main_frame = new JFrame();
    JPanel main_panel = new JPanel();

    JMenuBar main_menubar = new JMenuBar();

    JMenu file_menu = new JMenu("File");
    JMenuItem save_option = new JMenuItem("Save as CSV");
    JMenuItem open_option = new JMenuItem("Open CSV File");

    JMenu simulation_menu = new JMenu("Simulation");
    JMenuItem run_option = new JMenuItem("Run Simulation");
    JMenuItem reset_option = new JMenuItem("Reset Simulation");
    JMenuItem clear_option = new JMenuItem("Clear Simulation");

    // Constructor
    Main() {
        // Add JMenuBar to the main frame, and the items to the various menus.
        this.main_menubar.add(this.file_menu);
        this.file_menu.add(this.save_option);
        this.file_menu.add(this.open_option);

        this.main_menubar.add(this.simulation_menu);
        this.simulation_menu.add(this.run_option);
        this.simulation_menu.add(this.reset_option);
        this.simulation_menu.add(this.clear_option);

        this.main_frame.setJMenuBar(this.main_menubar);

        // Add action listeners to the various options, see GUIListener for more
        // information
        this.save_option.addActionListener(new GUIListener(this.save_option));
        this.open_option.addActionListener(new GUIListener(this.open_option));

        this.run_option.addActionListener(new GUIListener(this.run_option));
        this.reset_option.addActionListener(new GUIListener(this.reset_option));
        this.clear_option.addActionListener(new GUIListener(this.clear_option));

        // Set the defaults for window size, content pane, and default close operation.
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