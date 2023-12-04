// Ramp Dynamics Simulator
// created by Christopher Lee and Nicholas Poon
// ICS4U1

/* EXTRA FEATURES
 * 
 * - TWO ADDITIONAL JCOMPONENTS
 * - DATA FILES
 * - ABOUT PANEL (TODO)
 * - HELP PANEL (TODO)
 * - JAR FILE CONTAINS ALL DATA FILES (TODO)
 * 
 */

// imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JTextField;
import javax.swing.Timer;
/**
 * 
 * @author Christopher Lee
 * @author Nicholas Poon
 */
public class Main implements ActionListener, ChangeListener, MenuListener{
    // Frame and panel properties
    JFrame main_frame = new JFrame("ICS4U1 GUI Assignment");
    JPanel container_panel = new JPanel();
    JPanel main_panel = new JPanel();
    DrawingPanel drawing_panel = new DrawingPanel();

    // menu bar declaration
    JMenuBar main_menubar = new JMenuBar();

    // angle slider declaration
    JSlider angle_slider = new JSlider(5, 40);

    // home menu declaration
    JMenu home_menu = new JMenu("Home");
    
    // file menu declaration
    JMenu file_menu = new JMenu("File");
    JMenuItem save_option = new JMenuItem("Save as CSV");
    JMenuItem open_option = new JMenuItem("Open CSV File");

    // simulation menu declaration
    JMenu simulation_menu = new JMenu("Simulation");
    JMenuItem run_option = new JMenuItem("Run Simulation");
    JMenuItem reset_option = new JMenuItem("Reset Simulation");
    JMenuItem clear_option = new JMenuItem("Clear Simulation");

    //about menu declaration
    JMenu about_menu = new JMenu("About");

    // help menu declaration
    JMenu help_menu = new JMenu("Help");
    HelpPanel help_panel = new HelpPanel();

    // quiz menu declaration
    JMenu quiz_menu = new JMenu("Quiz");

    

    // labels and text fields
    JLabel title_label = new JLabel("Ramp Dynamics Simulator");
    JLabel angle_label = new JLabel("Angle:");
    JLabel mass_label = new JLabel("Mass of Object:");
    JTextField mass_field = new JTextField();
    JLabel static_friction_label = new JLabel("Coefficient of Static Friction:");
    JTextField static_friction_field = new JTextField();
    JLabel kinetic_friction_label = new JLabel("Coefficient of Kinetic Friction:");
    JTextField kinetic_friction_field = new JTextField();
    JButton load_settings_button = new JButton("Load Settings in Simulation");

    // simulation stats
    JLabel stats_label = new JLabel("Simulation Stats");
    JLabel normal_force_label = new JLabel("Force of the Normal: ");
    JLabel sfriction_force_label = new JLabel("Force of Static Friction: ");
    JLabel kfriction_force_label = new JLabel("Force of Kinetic Friction: ");
    JLabel parallel_force_label = new JLabel("Force of Parallel: ");
    JLabel perpendicular_force_label = new JLabel("Force of the Perpendicular: ");
    JLabel xacceleration_label = new JLabel("X Acceleration: ");
    JLabel yacceleration_label = new JLabel("Y Acceleration: " + DrawingPanel.dblGravity);

    Timer timer = new Timer(1000/48, this);

    /**
     * Action Listener method, mainly for buttons
     * @param e ActionEvent
     * @see DrawingPanel
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.load_settings_button) {
            this.loadSimulation();
            // calculate the normal force based on the angle and mass
            double dblNormalForce = drawing_panel.dblMass * DrawingPanel.dblGravity
                    * Math.cos(Math.toRadians(drawing_panel.dblDegrees));
            // calculate the static friction force based on the normal force and the
            // coefficient of static friction
            double dblStaticFrictionForce = dblNormalForce * drawing_panel.dblStaticFriction;
            // calculate the kinetic friction force based on the normal force and the
            // coefficient of kinetic friction
            double dblKineticFrictionForce = dblNormalForce * drawing_panel.dblKineticFriction;
            //display the live stats to the user
            this.normal_force_label.setText("Force of the Normal: " + dblNormalForce);
            this.sfriction_force_label.setText("Force of Static Friction: " + dblStaticFrictionForce);
            this.kfriction_force_label.setText("Force of Kinetic Friction: " + dblKineticFrictionForce);
            this.parallel_force_label.setText("Force of Parallel: " + dblNormalForce * Math.sin(Math.toRadians(drawing_panel.dblDegrees)));
            this.perpendicular_force_label.setText("Force of the Perpendicular: " + dblNormalForce * Math.cos(Math.toRadians(drawing_panel.dblDegrees)));
            this.xacceleration_label.setText("X Acceleration: " + drawing_panel.dblAccelerationX);
        } else if (e.getSource() == this.save_option) {
            this.saveSettings();
        } else if (e.getSource() == this.open_option) {
            this.loadSettings();
        } else if (e.getSource() == this.run_option) {
            // if the user has not loaded the simulation settings based on a boolean value, display an error message
            if (drawing_panel.dblMass == 0 || drawing_panel.dblStaticFriction == 0 || drawing_panel.dblKineticFriction == 0) {
                JOptionPane.showMessageDialog(this.main_frame, "The values must be greater than 0. Please load the simulation settings.");
                return;
            }
            timer.start();
        } else if (e.getSource() == this.reset_option) {
            System.out.println("Reset Simulation");
            timer.stop();
            drawing_panel.dblSeconds = 0;
            drawing_panel.dblSquareAX = 150;
            drawing_panel.update();
            drawing_panel.repaint();
        } else if (e.getSource() == this.clear_option) {
            // reset text fields
            this.mass_field.setText("");
            this.static_friction_field.setText("");
            this.kinetic_friction_field.setText("");
        } else if (e.getSource() == this.timer) {
            // calculate the new velocity based on seconds
            drawing_panel.dblSeconds += (1 / 0.48);
            System.out.println(drawing_panel.dblVelX);
            drawing_panel.dblSquareAX += drawing_panel.physicsCalculations(drawing_panel.dblSeconds);
            drawing_panel.repaint();
        }
    }

  
    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource() == help_menu){
            help_panel.setPreferredSize(new Dimension(960, 540));
            System.out.println("Help Menu");
            this.main_frame.setContentPane(help_panel);
            this.main_frame.pack();
            this.main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.main_frame.setResizable(false);
            this.main_frame.setVisible(true);
        }else if (e.getSource() == home_menu){
            System.out.println("Home Menu");
            this.main_frame.setContentPane(container_panel);
            this.main_frame.pack();
            this.main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.main_frame.setResizable(false);
            this.main_frame.setVisible(true);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        
    }
    /**
     * Change Listener method, mainly for the angle slider
     * @param e ChangeEvent
     * @see DrawingPanel
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        // System.out.println(angle_slider.getValue());
        drawing_panel.dblDegrees = angle_slider.getValue();
        drawing_panel.repaint();
    }

    /**
     * Saves settings to a CSV file in the format: angle, mass, static friction, kinetic friction
     */
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

    /**
     * Loads settings from a CSV file in the format: angle, mass, static friction, kinetic friction
     */
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

    /**
     * Loads the simulation settings from the text fields
     * @see DrawingPanel.dblMass
     * @see DrawingPanel.dblStaticFriction
     * @see DrawingPanel.dblKineticFriction
     */
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

    /**
     * Main constructor, sets up the GUI
     * @see DrawingPanel
     */
    Main() {
        // Add JMenuBar to the main frame, and the items to the various menus.
        this.main_menubar.add(this.home_menu);
        this.home_menu.addMenuListener(this);

        this.main_menubar.add(this.file_menu);
        this.file_menu.add(this.save_option);
        this.file_menu.add(this.open_option);

        this.main_menubar.add(this.simulation_menu);
        this.simulation_menu.add(this.run_option);
        this.simulation_menu.add(this.reset_option);
        this.simulation_menu.add(this.clear_option);

        this.main_menubar.add(this.help_menu);
        this.help_menu.addMenuListener(this);

        this.main_menubar.add(this.quiz_menu);
        this.quiz_menu.addMenuListener(this);

        this.main_frame.setJMenuBar(this.main_menubar);

        // Angle Slider
        this.angle_slider.setPaintTicks(true);
        this.angle_slider.setMajorTickSpacing(5);
        this.angle_slider.setPaintLabels(true);

        this.angle_slider.addChangeListener(this);

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

        // section for live stats
        this.stats_label.setBounds(10, 225, 290, 25);
        this.main_panel.add(this.stats_label);
        this.normal_force_label.setBounds(10, 260, 290, 25);
        this.main_panel.add(this.normal_force_label);
        this.sfriction_force_label.setBounds(10, 295, 290, 25);
        this.main_panel.add(this.sfriction_force_label);
        this.kfriction_force_label.setBounds(10, 330, 290, 25);
        this.main_panel.add(this.kfriction_force_label);
        this.parallel_force_label.setBounds(10, 365, 290, 25);
        this.main_panel.add(this.parallel_force_label);
        this.perpendicular_force_label.setBounds(10, 400, 290, 25);
        this.main_panel.add(this.perpendicular_force_label);
        this.xacceleration_label.setBounds(10, 435, 290, 25);
        this.main_panel.add(this.xacceleration_label);
        this.yacceleration_label.setBounds(10, 470, 290, 25);
        this.main_panel.add(this.yacceleration_label);

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