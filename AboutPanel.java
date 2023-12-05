import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutPanel extends JPanel{
    // properties
    JLabel title_label = new JLabel("About: ");
    
    // created by labels
    JLabel created_by_label = new JLabel("Created by:");
    JLabel author_label = new JLabel("Christopher Lee and Nicholas Poon");
    JLabel taught_label = new JLabel("Taught by: ");
    JLabel teacher_label = new JLabel("Mr Cadawas in the Tech Department");
    JLabel school_label = new JLabel("At St. Augustine CHS");
    JLabel poon_label = new JLabel("Poon");

    AboutPanel() {
        // add labels to the panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(960, 540));
        
        title_label.setFont(title_label.getFont().deriveFont(35.0f));
        created_by_label.setFont(created_by_label.getFont().deriveFont(14.0f));
        author_label.setFont(author_label.getFont().deriveFont(14.0f));
        taught_label.setFont(taught_label.getFont().deriveFont(14.0f));
        teacher_label.setFont(teacher_label.getFont().deriveFont(14.0f));
        school_label.setFont(school_label.getFont().deriveFont(14.0f));
        poon_label.setFont(poon_label.getFont().deriveFont(28.0f));

        this.title_label.setBounds(10,10,500,40);
        this.created_by_label.setBounds(10, 50, 500, 25);
        this.author_label.setBounds( 10, 75, 500, 25);
        this.taught_label.setBounds(10, 125, 500, 25);
        this.teacher_label.setBounds(10, 150, 500, 25);
        this.school_label.setBounds(10, 175, 500, 25);

        this.add(this.title_label);
        this.add(this.created_by_label);
        this.add(this.author_label);
        this.add(this.taught_label);
        this.add(this.teacher_label);
        this.add(this.school_label);
    }
}
