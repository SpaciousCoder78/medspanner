//importing modules
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//******************************************************************************************************
//menu class
public class menu {
    //creating components for our menu
    //Defining jframe
    private JFrame frame;
    //defining title
    private JLabel title;
    //defining panel
    private JPanel panel;
    //button for tracking observations
    private JButton trackobs;
    //button for viewing observations
    private JButton viewobs;
    //button for adding meds
    private JButton addmeds;
    //button for viewing existing meds
    private JButton viewmeds;
    //setup button
    private JButton setup;
    //info button
    private JButton info;

    //***********************************************************************************************************

    //function for setting up the database
    public static void setitup() {
        Connection conn = null;

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Address of the database
            String url = "jdbc:sqlite:medspannerdata.db";
            // Connecting to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection Established");

            // Create a statement
            Statement statement = conn.createStatement();

            // SQL statement to create a table for observations
            String createtableforobs = "CREATE TABLE IF NOT EXISTS observations (id INTEGER PRIMARY KEY, " +
                    "DATE VARCHAR, OBSERVATION VARCHAR)";



            // Execute the SQL statement
            statement.executeUpdate(createtableforobs);

            //creating table for meds
            String createtableformeds = "CREATE TABLE IF NOT EXISTS meds (id INTEGER PRIMARY KEY, "+
                    "MEDNAME VARCHAR, DOSE VARCHAR, EXPIRY VARCHAR, NOTES VARCHAR)";
            statement.executeUpdate(createtableformeds);

            System.out.println("Table created successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    public menu() {
        //#############################Creating Components###################################
        frame = new JFrame("medspanner");

        // Create and configure the components
        panel = new JPanel();
        //Jlabel medspanner
        title = new JLabel("Welcome to medspanner");
        //setup button
        setup = new JButton("Setup");
        // track health observations button
        trackobs = new JButton("Track Health Observations");
        // view recorded observations button
        viewobs = new JButton("View Recorded Observations");
        // add meds button
        addmeds = new JButton("Add Medicines");
        //view meds button
        viewmeds = new JButton("View Medicines");
        //info button
        info = new JButton("Info");
//##################################Adding components to frame###########################################
        // Add the components to the frame
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(Box.createVerticalGlue());
        //title
        frame.add(title);
        frame.add(Box.createVerticalStrut(10));
        //setup
        frame.add(setup);
        frame.add(Box.createVerticalStrut(10));
        //track health obs
        frame.add(trackobs);
        frame.add(Box.createVerticalStrut(10));
        //view observations
        frame.add(viewobs);
        frame.add(Box.createVerticalStrut(10));
        //add meds
        frame.add(addmeds);
        frame.add(Box.createVerticalStrut(10));
        //view meds
        frame.add(viewmeds);
        frame.add(Box.createVerticalStrut(10));
        //info button

        frame.add(info);

        frame.add(Box.createVerticalGlue());

        //action listeners for buttons

        //action listener for new obsessions
        ActionListener newob = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trackobservations trackobs = new trackobservations();
            }
        };

        trackobs.addActionListener(newob);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300,300);
    }
    //******************************************************************************************************
    //main function to run the menu
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new menu();
            }
        });
    }

}


