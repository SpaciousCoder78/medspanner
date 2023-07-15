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

import static javax.swing.BoxLayout.PAGE_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

public class addmeds {
    //declearing swing components
    private JFrame frame;
    private JPanel panel;
    private JLabel medlabel;
    private JTextField medlabeltext;
    private JLabel doselabel;
    private JTextField dosagetext;
    private JLabel expirylabel;
    private JTextField expirydate;
    private JLabel noteslabel;
    private JTextArea notesarea;
    private JButton submit1;

    private void insertDataToTable(String MEDNAME, String DOSE, String EXPIRY, String NOTES) {
        //defining the db url
        String url = "jdbc:sqlite:medspannerdata.db";
        //selecting the highest id from table
        String selectMaxIdSql = "SELECT MAX(id) FROM meds";
        //inserting data into table
        String insertSql = "INSERT INTO meds (id, MEDNAME,DOSE,EXPIRY,NOTES) VALUES (?,?, ?, ?, ?)";
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
            insertStmt.setString(2, MEDNAME);
            insertStmt.setString(3, DOSE);
            insertStmt.setString(4,EXPIRY);
            insertStmt.setString(5,NOTES);
            insertStmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Record Saved");

        } catch (SQLException e) {
            //just in case if the db doesnt work
            JOptionPane.showMessageDialog(null, "Error occurred while inserting data: " + e.getMessage());
        }
    }

    public addmeds(){
        // Creating components
        //frame for adding meds
        frame = new JFrame("Add Meds");
        //panel with borderlayout
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Set spacing between components

        //medlabel
        medlabel = new JLabel("Med Name:");
        //textfield for med name
        medlabeltext = new JTextField(20);
        medlabeltext.setFont(medlabeltext.getFont().deriveFont(14f));
        //label for dosage
        doselabel = new JLabel("Dosage: ");
        //textfield for dosage
        dosagetext = new JTextField(20);
        //label for expiry
        expirylabel = new JLabel("Expiry:");
        //textfield for expiry
        expirydate= new JTextField(20);
        //label for notes
        noteslabel= new JLabel("Notes: ");
        //textarea for notes
        notesarea= new JTextArea(5,10);
        // Create a scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(notesarea);
        submit1 = new JButton("Submit");

        // Adding components to the panel using GridBagLayout constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(medlabel, constraints);

        constraints.gridx = 1;
        panel.add(medlabeltext, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(doselabel, constraints);

        constraints.gridx = 1;
        panel.add(dosagetext, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(expirylabel, constraints);

        constraints.gridx = 1;
        panel.add(expirydate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(noteslabel, constraints);

        constraints.gridx = 1;
        panel.add(notesarea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(submit1, constraints);


        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);

        //action listener for submit button
        ActionListener subm = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the text from the text fields and text area
                String MEDNAME = medlabeltext.getText();
                String DOSE = dosagetext.getText();
                String EXPIRY = expirydate.getText();
                String NOTES = notesarea.getText();

                // Insert the data into the SQLite table
                insertDataToTable( MEDNAME,DOSE,EXPIRY,NOTES);

                // Clear the text fields and text area

                medlabeltext.setText("");
                dosagetext.setText("");
                expirydate.setText("");
                notesarea.setText("");
            }
        };
        submit1.addActionListener(subm);
    }


}

