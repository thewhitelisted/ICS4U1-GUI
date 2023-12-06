import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuizPanel extends JPanel implements ActionListener{
    // quiz title label
    JLabel title_label = new JLabel("Quiz");

    // question labels
    JLabel question1 = new JLabel("Does mass affect the overall acceleration of an object on a ramp?");
    String[] options1 = {"Yes", "No"};
    JComboBox<String> answer1 = new JComboBox<>(options1);
    JLabel question2 = new JLabel("If the angle of the incline increases, accerlation increases");
    JComboBox<String> answer2 = new JComboBox<>(options1);
    JLabel question3 = new JLabel("What value closest represents acceleration due to gravity");
    String[] options2 = {"10.1", "9.8", "11.1", "9.1"};
    JComboBox<String> answer3 = new JComboBox<>(options2);
    JButton submit = new JButton("Submit");

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            int score = 0;
            System.out.println("Submit button pressed");
            if (answer1.getSelectedItem().equals("No")){
                System.out.println("Correct");
                score++;
            }
            if (answer2.getSelectedItem().equals("Yes")){
                System.out.println("Correct");
                score++;
            }
            if (answer3.getSelectedItem().equals("9.8")){
                System.out.println("Correct");
                score++;
            }

            JOptionPane.showMessageDialog(null, "You got " + score + " out of 3 correct");
        }
    }

    // constructor
    QuizPanel() {
        // set layout
        setLayout(null);
        setPreferredSize(new Dimension(960, 540));

        // set size of title label font
        title_label.setFont(getFont().deriveFont(35.0f));

        // set bounds
        title_label.setBounds(10, 10, 100, 25);
        question1.setBounds(10, 50, 500, 25);
        answer1.setBounds(10, 75, 100, 25);
        question2.setBounds(10, 125, 500, 25);
        answer2.setBounds(10, 150, 100, 25);
        question3.setBounds(10, 200, 500, 25);
        answer3.setBounds(10, 225, 100, 25);
        submit.setBounds(10, 275, 100, 25);

        // action listener
        submit.addActionListener(this);

        // add components
        add(title_label);
        add(question1);
        add(answer1);
        add(question2);
        add(answer2);
        add(question3);
        add(answer3);
        add(submit);
    }

    
}
