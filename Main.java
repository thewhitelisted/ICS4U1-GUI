import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
    // Properties
    JFrame main_frame = new JFrame();
    JPanel container_panel = new JPanel();
    JPanel main_panel = new JPanel();
    JPanel drawing_panel = new JPanel();

    JMenuBar main_menubar = new JMenuBar();

    JMenu file_menu = new JMenu("File");
    JMenuItem save_option = new JMenuItem("Save as CSV");
    JMenuItem open_option = new JMenuItem("Open CSV File");

    JMenu simulation_menu = new JMenu("Simulation");
    JMenuItem run_option = new JMenuItem("Run Simulation");
    JMenuItem reset_option = new JMenuItem("Reset Simulation");
    JMenuItem clear_option = new JMenuItem("Clear Simulation");

    JLabel title_label = new JLabel("Ramp Dynamics Simulator");
    JLabel angle_label = new JLabel("Angle:");
    JTextField angle_field = new JTextField();
    JLabel mass_label = new JLabel("Mass of Object:");
    JTextField mass_field = new JTextField();
    JLabel static_friction_label = new JLabel("Coefficient of Static Friction:");
    JTextField static_friction_field = new JTextField();
    JLabel kinetic_friction_label = new JLabel("Coefficient of Kinetic Friction:");
    JTextField kinetic_friction_field = new JTextField();
    JLabel force_applied_label = new JLabel("Force Applied:");
    JTextField force_applied_field = new JTextField();

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
        this.container_panel.setLayout(new BoxLayout(this.container_panel, BoxLayout.X_AXIS));
        this.container_panel.add(this.main_panel);
        this.container_panel.add(this.drawing_panel);
        this.container_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.main_panel.setPreferredSize(new Dimension(320, 540));
        this.main_panel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.main_panel.setLayout(null);
        this.drawing_panel.setPreferredSize(new Dimension(640, 540));
        this.drawing_panel.setBorder(BorderFactory.createLineBorder(Color.black));

        // Add labels and textfields to the main panel
        this.title_label.setBounds(10, 5, 200, 25);
        this.main_panel.add(this.title_label);
        this.angle_label.setBounds(10, 40, 50, 25);
        this.main_panel.add(this.angle_label, JLabel.LEFT_ALIGNMENT);
        this.angle_field.setBounds(60, 40, 230, 25);
        this.main_panel.add(this.angle_field);
        this.mass_label.setBounds(10, 75, 115, 25);
        this.main_panel.add(this.mass_label);
        this.mass_field.setBounds(115, 75, 175, 25);
        this.main_panel.add(this.mass_field);
        this.static_friction_label.setBounds(10, 110, 200, 25);
        this.main_panel.add(this.static_friction_label);
        this.static_friction_field.setBounds(200, 110, 90, 25);
        this.main_panel.add(this.static_friction_field);
        this.kinetic_friction_label.setBounds(10, 145, 200, 25);
        this.main_panel.add(this.kinetic_friction_label);
        this.kinetic_friction_field.setBounds(200, 145, 90, 25);
        this.main_panel.add(this.kinetic_friction_field);
        this.force_applied_label.setBounds(10, 180, 100, 25);
        this.main_panel.add(this.force_applied_label);
        this.force_applied_field.setBounds(100, 180, 190, 25);
        this.main_panel.add(this.force_applied_field);

        this.main_frame.setContentPane(container_panel);
        this.main_frame.pack();
        this.main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.main_frame.setResizable(false);
        this.main_frame.setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        new Main();
    }
}