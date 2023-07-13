import javax.swing.*;
import java.awt.*;

public class trackobservations {
    // Defining components
    private JFrame frame;
    private JPanel panel;
    private JLabel datelabel;
    private JTextField date;
    private JLabel obslabel;
    private JTextArea obsarea;
    private JButton submit;

    public trackobservations() {
        // Creating components
        frame = new JFrame("New Observation");
        panel = new JPanel(new BorderLayout());
        datelabel = new JLabel("Date:");
        date = new JTextField(20);
        obslabel = new JLabel("Observations: ");
        obsarea = new JTextArea(5, 10);
        submit = new JButton("Submit");

        // Creating nested panel for date components
        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.add(datelabel);
        datePanel.add(date);

        // Adding components to the main panel
        panel.add(datePanel, BorderLayout.NORTH);
        panel.add(obslabel, BorderLayout.WEST);
        panel.add(new JScrollPane(obsarea), BorderLayout.CENTER);
        panel.add(submit, BorderLayout.SOUTH);

        // Adding the panel to the frame
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new trackobservations();
            }
        });
    }
}