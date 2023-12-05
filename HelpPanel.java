import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
    JLabel help_label1 = new JLabel("You can change the Angle of the ramp by using the slider.");

    JLabel help_label2 = new JLabel("Enter number values only for the Mass and Friction Coefficients.");
    JLabel help_label3 = new JLabel(
            "Once you have entered values for all of the variables for the simulation, press 'load settings into simulation' button to load them in.");
    JLabel help_label4 = new JLabel(
            "When you have loaded in your values, you will be able to see your simulation stats on your screen.");
    JLabel help_label5 = new JLabel(
            "To run your simulation, select the 'Simulation' menu and the 'Run Simulation' menu item.");
    JLabel help_label6 = new JLabel(
            "Also under the 'Simulation' menu, you can select 'Reset Simulation' to move the square back to its original position. ");
    JLabel help_label7 = new JLabel("Select 'Clear Simulation' to reset all of your variables.");
    JLabel help_label8 = new JLabel(
            "Under the 'File' menu, you can click 'Save as CSV' to save all of your settings to a CSV file.");
    JLabel help_label9 = new JLabel("Click 'load CSV' any time to automatically import all of your saved variables.");
    JLabel help_label10 = new JLabel("The 'Home' menu will bring you back to the simulation screen.");
    JLabel help_label11 = new JLabel("The 'Quiz' menu will give your questions related to our topic");

    public HelpPanel() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(960, 540));

        // increase font size
        help_label1.setFont(help_label1.getFont().deriveFont(14.0f));
        help_label2.setFont(help_label2.getFont().deriveFont(14.0f));
        help_label3.setFont(help_label3.getFont().deriveFont(14.0f));
        help_label4.setFont(help_label4.getFont().deriveFont(14.0f));
        help_label5.setFont(help_label5.getFont().deriveFont(14.0f));
        help_label6.setFont(help_label6.getFont().deriveFont(14.0f));
        help_label7.setFont(help_label7.getFont().deriveFont(14.0f));
        help_label8.setFont(help_label8.getFont().deriveFont(14.0f));
        help_label9.setFont(help_label9.getFont().deriveFont(14.0f));
        help_label10.setFont(help_label10.getFont().deriveFont(14.0f));
        help_label11.setFont(help_label11.getFont().deriveFont(14.0f));

        help_label1.setBounds(10, 10, 1000, 20);
        this.add(help_label2);
        help_label2.setBounds(10, 40, 1000, 20);
        this.add(help_label1);
        help_label3.setBounds(10, 70, 1000, 20);
        this.add(help_label3);
        help_label4.setBounds(10, 100, 1000, 20);
        this.add(help_label4);
        help_label5.setBounds(10, 130, 1000, 20);
        this.add(help_label5);
        help_label6.setBounds(10, 160, 1000, 20);
        this.add(help_label6);
        help_label7.setBounds(10, 190, 1000, 20);
        this.add(help_label7);
        help_label8.setBounds(10, 220, 1000, 20);
        this.add(help_label8);
        help_label9.setBounds(10, 250, 1000, 20);
        this.add(help_label9);
        help_label10.setBounds(10, 280, 1000, 20);
        this.add(help_label10);
        help_label11.setBounds(10, 310, 1000, 20);
        this.add(help_label11);
    }
}
