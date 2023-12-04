import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
    JLabel Help_Label1 = new JLabel("You can change the Angle of the ramp by using the slider.");
    JLabel Help_Label2 = new JLabel("Enter number values only for the Mass and Friction Coefficients.");
    JLabel Help_Label3 = new JLabel("Once you have entered values for all of the variables for the simulation, press 'load settings into simulation' button to load them in.");
    JLabel Help_Label4 = new JLabel("When you have loaded in your values, you will be able to see your simulation stats on your screen.");
    JLabel Help_Label5 = new JLabel("To run your simulation, select the 'Simulation' menu and the 'Run Simulation' menu item.");
    JLabel Help_Label6 = new JLabel("Also under the 'Simulation' menu, you can select 'Reset Simulation' to move the square back to its original position. ");
    JLabel Help_Label7 = new JLabel("Select 'Clear Simulation' to reset all of your variables.");
    JLabel Help_Label8 = new JLabel("Under the 'File' menu, you can click 'Save as CSV' to save all of your settings to a CSV file.");
    JLabel Help_Label9 = new JLabel("Click 'load CSV' any time to automatically import all of your saved variables.");
    JLabel Help_Label10 = new JLabel("The 'Home' menu will bring you back to the simulation screen.");
    JLabel Help_Label11 = new JLabel("The 'Quiz' menu will give your questions related to our topic");
    public HelpPanel(){


        this.add(Help_Label1);
        this.add(Help_Label2);
        this.add(Help_Label3);
        this.add(Help_Label4);
        this.add(Help_Label5);
        this.add(Help_Label6);
        this.add(Help_Label7);
        this.add(Help_Label8);
        this.add(Help_Label9);
        this.add(Help_Label10);
        this.add(Help_Label11);
    }
}
