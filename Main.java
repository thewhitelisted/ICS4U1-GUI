import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main implements ChangeListener {
    // Properties
    JFrame main_frame = new JFrame();
    JPanel container_panel = new JPanel();
    JPanel main_panel = new JPanel();
    DrawingPanel drawing_panel = new DrawingPanel();

    JMenuBar main_menubar = new JMenuBar();

    JSlider angle_slider = new JSlider(0, 40);

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

        // Angle Slider
        angle_slider.setPaintTicks(true);
        angle_slider.setMajorTickSpacing(40);
        angle_slider.setPaintLabels(true);

        angle_slider.addChangeListener(this);
        this.main_panel.add(angle_slider);

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
        this.drawing_panel.setPreferredSize(new Dimension(640, 540));
        this.drawing_panel.setBorder(BorderFactory.createLineBorder(Color.black));

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

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println(angle_slider.getValue());
        drawing_panel.dblDegrees = angle_slider.getValue();
        drawing_panel.repaint();
    }
}