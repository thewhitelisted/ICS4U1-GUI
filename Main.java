import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;

public class Main implements ActionListener, ChangeListener {
    // Properties
    JFrame main_frame = new JFrame("ICS4U1 GUI Assignment");
    JPanel container_panel = new JPanel();
    JPanel main_panel = new JPanel();
    DrawingPanel drawing_panel = new DrawingPanel();

    JMenuBar main_menubar = new JMenuBar();

    JSlider angle_slider = new JSlider(5, 40);

    JMenu file_menu = new JMenu("File");
    JMenuItem save_option = new JMenuItem("Save as CSV");
    JMenuItem open_option = new JMenuItem("Open CSV File");

    JMenu simulation_menu = new JMenu("Simulation");
    JMenuItem run_option = new JMenuItem("Run Simulation");
    JMenuItem reset_option = new JMenuItem("Reset Simulation");
    JMenuItem clear_option = new JMenuItem("Clear Simulation");

    JLabel title_label = new JLabel("Ramp Dynamics Simulator");
    JLabel angle_label = new JLabel("Angle:");
    JLabel mass_label = new JLabel("Mass of Object:");
    JTextField mass_field = new JTextField();
    JLabel static_friction_label = new JLabel("Coefficient of Static Friction:");
    JTextField static_friction_field = new JTextField();
    JLabel kinetic_friction_label = new JLabel("Coefficient of Kinetic Friction:");
    JTextField kinetic_friction_field = new JTextField();
    JButton load_settings_button = new JButton("Load Settings in Simulation");

    // Methods
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == this.load_settings_button) {
            this.loadSimulation();
        } else if (e.getSource() == this.save_option) {
            this.saveSettings();
        } else if (e.getSource() == this.open_option) {
            this.loadSettings();
        } else if (e.getSource() == this.run_option) {
            System.out.println("Run Simulation");
        } else if (e.getSource() == this.reset_option) {
            System.out.println("Reset Simulation");
        } else if (e.getSource() == this.clear_option) {
            System.out.println("Clear Simulation");
        } 
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // System.out.println(angle_slider.getValue());
        drawing_panel.dblDegrees = angle_slider.getValue();
        drawing_panel.repaint();
    }

    private void saveSettings() {
        // save settings to a CSV file
        // format: angle, mass, static friction, kinetic friction, force applied
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("settings.csv"));
            pw.println(this.angle_slider.getValue() + "," + this.mass_field.getText() + ","
                    + this.static_friction_field.getText() + "," + this.kinetic_friction_field.getText());
            pw.close();
        } catch (IOException e) {
        }
    }

    private void loadSettings() {
        // load settings from a CSV file
        // load angle, mass, static friction, kinetic friction, force applied in that
        // order
        try {
            BufferedReader br = new BufferedReader(new FileReader("settings.csv"));
            String strSettings = br.readLine();
            String[] settings = strSettings.split(",");
            this.angle_slider.setValue(Integer.parseInt(settings[0]));
            this.mass_field.setText(settings[1]);
            this.static_friction_field.setText(settings[2]);
            this.kinetic_friction_field.setText(settings[3]);
            br.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private void loadSimulation() {
        // take all values from the text fields and set them to the values in the
        // simulation
        if (this.mass_field.getText().equals("")) {
            JOptionPane.showMessageDialog(this.main_frame, "Please enter a mass.");
            return;
        } else if (this.static_friction_field.getText().equals("")) {
            JOptionPane.showMessageDialog(this.main_frame, "Please enter a static friction coefficient.");
            return;
        } else if (this.kinetic_friction_field.getText().equals("")) {
            JOptionPane.showMessageDialog(this.main_frame, "Please enter a kinetic friction coefficient.");
            return;
        }
        // check if the values contain only numbers
        try {
            drawing_panel.dblMass = Double.parseDouble(this.mass_field.getText());
            drawing_panel.dblStaticFriction = Double.parseDouble(this.static_friction_field.getText());
            drawing_panel.dblKineticFriction = Double.parseDouble(this.kinetic_friction_field.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.main_frame, "Please enter only numbers.");
            return;
        }

        // display a message dialog to the user to let them know that the simulation
        // settings have been loaded
        JOptionPane.showMessageDialog(this.main_frame, "Settings loaded.");
    }

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

        // Angle Slider
        angle_slider.setPaintTicks(true);
        angle_slider.setMajorTickSpacing(5);
        angle_slider.setPaintLabels(true);

        angle_slider.addChangeListener(this);

        // Add action listeners to the various options, see GUIListener for more
        // information
        this.save_option.addActionListener(this);
        this.open_option.addActionListener(this);

        this.run_option.addActionListener(this);
        this.reset_option.addActionListener(this);
        this.clear_option.addActionListener(this);

        this.load_settings_button.addActionListener(this);

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
        this.title_label.setBounds(10, 5, 210, 25);
        this.main_panel.add(this.title_label);
        this.angle_label.setBounds(10, 40, 100, 25);
        this.main_panel.add(this.angle_label);
        this.angle_slider.setBounds(100, 40, 200, 45);
        this.main_panel.add(this.angle_slider);
        this.mass_label.setBounds(10, 85, 115, 25);
        this.main_panel.add(this.mass_label);
        this.mass_field.setBounds(115, 85, 185, 25);
        this.main_panel.add(this.mass_field);
        this.static_friction_label.setBounds(10, 120, 200, 25);
        this.main_panel.add(this.static_friction_label);
        this.static_friction_field.setBounds(200, 120, 100, 25);
        this.main_panel.add(this.static_friction_field);
        this.kinetic_friction_label.setBounds(10, 155, 200, 25);
        this.main_panel.add(this.kinetic_friction_label);
        this.kinetic_friction_field.setBounds(200, 155, 100, 25);
        this.main_panel.add(this.kinetic_friction_field);
        this.load_settings_button.setBounds(10, 190, 290, 25);
        this.main_panel.add(this.load_settings_button);

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