import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutPanel extends JPanel{
    // properties
    JLabel title_label = new JLabel("About");
    
    // created by labels
    JLabel created_by_label = new JLabel("Created by:");
    JLabel author_label = new JLabel("Christopher Lee and Nicholas Poon");

    AboutPanel() {
        // add labels to the panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(960, 540));
        this.title_label.setBounds(50,50,100,100);
        this.add(this.title_label);
    }
}
