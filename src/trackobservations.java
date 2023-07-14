//importing modules
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


public class trackobservations {
    // Defining components
    private JFrame frame;
    private JPanel panel;
    private JLabel datelabel;
    private JTextField date;
    private JLabel obslabel;
    private JTextArea obsarea;
    private JButton submit;
    //function for inserting data into table
    private void insertDataToTable(String DATE, String OBSERVATION) {
        //defining the db url
        String url = "jdbc:sqlite:medspannerdata.db";
        //selecting the highest id from table
        String selectMaxIdSql = "SELECT MAX(id) FROM observations";
        //inserting data into table
        String insertSql = "INSERT INTO observations (id, DATE,OBSERVATION) VALUES ( ?, ?, ?)";
        //try catch block to attempt connection
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement selectMaxIdStmt = conn.prepareStatement(selectMaxIdSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            // Get the maximum id from the table
            int maxId = 0;
            try (ResultSet rs = selectMaxIdStmt.executeQuery()) {
                if (rs.next()) {
                    maxId = rs.getInt(1);
                }
            }

            // Increment the id value by 1
            int id = maxId + 1;

            // Insert the data into the table
            insertStmt.setInt(1, id);
            insertStmt.setString(2, DATE);
            insertStmt.setString(3, OBSERVATION);
            insertStmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Record Saved");

        } catch (SQLException e) {
            //just in case if the db doesnt work
            JOptionPane.showMessageDialog(null, "Error occurred while inserting data: " + e.getMessage());
        }
    }

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

        //action listener for submit button
        ActionListener subm = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the text from the text fields and text area
                String DATE = date.getText();
                String OBSERVATION = obsarea.getText();

                // Insert the data into the SQLite table
                insertDataToTable( DATE,OBSERVATION);

                // Clear the text fields and text area

                date.setText("");
                obsarea.setText("");
            }
        };
        submit.addActionListener(subm);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new trackobservations();
            }
        });
    }

}